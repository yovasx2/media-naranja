package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

// added
import play.i18n.Messages;
import views.html.statePhotoIH;
import models.User;
import models.Ban;
import models.Post;
import models.Gallery;
import play.libs.Json; // serve
import java.util.List;
//file upload
import play.mvc.Http.MultipartFormData;
import java.io.File;
import java.io.IOException;
import play.mvc.Http.MultipartFormData.FilePart;
import org.apache.commons.io.*;

public class StatePhotoC extends Controller {

  @Security.Authenticated(SecuredUser.class)
  public static Result statePhoto(Long id) {
    User banner = User.find.where().eq("email",session("email")).findUnique();
    User published = User.find.where().eq("id",id).findUnique();
    // if desired user does not exist
    if(published==null){
      return Results.notFound(views.html.notFoundPage.render());
    }
    Ban b=Ban.find.where().eq("banner_id",banner.id).eq("banned_id",published.id).findUnique();
    if(b==null){
      b=Ban.find.where().eq("banner_id",published.id).eq("banned_id",banner.id).findUnique(); 
    }
    if(b!=null){
      flash("error", new Messages().get("ban.exists"));
      return redirect(routes.HomeC.home());
    }
    
    Form<Post> postForm       = Form.form(Post.class);
    Form<Gallery> galleryForm = Form.form(Gallery.class);
    List<Post> posts          = Post.findRecentPosts(published.email);
    return ok(statePhotoIH.render(published,postForm,galleryForm,posts));
  }

  @Security.Authenticated(SecuredUser.class)
  public static Result myStatePhoto() {
    User user = User.find.where().eq("email",session("email")).findUnique();
    return redirect(routes.StatePhotoC.statePhoto(user.id));
  }

  @Security.Authenticated(SecuredUser.class)
  public static Result doState(Long id) {
    Form<Gallery> galleryForm = Form.form(Gallery.class);
    Form<Post> postForm       = Form.form(Post.class).bindFromRequest();
    User published            = User.find.byId(id);
    List<Post> posts          = Post.findRecentPosts(published.email);

    // if desired user(published) does not exist
    if(published==null){
      return Results.notFound(views.html.notFoundPage.render());
    }

    if(postForm.hasErrors()){
      flash("error", new Messages().get("validation.error"));
      return badRequest(statePhotoIH.render(published,postForm,galleryForm,posts));
    }else{
      User publisher  = User.find.where().eq("email",session("email")).findUnique(); 
      Post p          = postForm.get();
      p.publisher     = publisher;
      p.published     = published;
      p.save();
      postForm  = Form.form(Post.class);
      posts     = Post.findRecentPosts(published.email);

      flash("success", new Messages().get("state.correct"));
      return redirect(routes.StatePhotoC.statePhoto(published.id));
    }
  }

  public static Result getStates(Long id){
    User user = User.find.where().eq("id",id).findUnique();
    List<Post> posts = Post.findRecentPosts(user.email);
    return ok(Json.toJson(posts));
  }

  @Security.Authenticated(SecuredUser.class)
  public static Result doPhoto(Long id) {
    Form<Post> postForm       = Form.form(Post.class);
    Form<Gallery> galleryForm = Form.form(Gallery.class).bindFromRequest();
    User owner                = User.find.byId(id);
    List<Post> posts          = Post.findRecentPosts(owner.email);

    // if desired user(owner) does not exist
    if(owner==null){
      return Results.notFound(views.html.notFoundPage.render());
    }

    // reject if there is no photo
    MultipartFormData body = request().body().asMultipartFormData();
    FilePart photo = body.getFile("photo");
    if(photo==null){
      galleryForm.reject("photo", new Messages().get("validation.file.no"));
      flash("error", new Messages().get("validation.error"));
      return badRequest(statePhotoIH.render(owner,postForm,galleryForm,posts));
    }
    if(photo!=null){
      String contentType = photo.getContentType();
      if(!contentType.equalsIgnoreCase("image/jpeg")){
        galleryForm.reject("photo",new Messages().get("validation.file.format"));
        flash("error",new Messages().get("validation.error"));
        return badRequest(statePhotoIH.render(owner,postForm,galleryForm,posts));
      }
    }

    Gallery g = Gallery.find.byId(owner.id);
    // first time upload photo
    if (g==null){
      g = new Gallery();
      g.owner=owner;
      g.save();
    }
    // photo move file and preserve URI
    try{
      switch(g.current){
        case 0:
        g.photo0="uploads/"+owner.id+"/album/"+0+".jpeg"; 
        break;
        case 1:
        g.photo1="uploads/"+owner.id+"/album/"+1+".jpeg"; 
        break;
        case 2:
        g.photo2="uploads/"+owner.id+"/album/"+2+".jpeg"; 
        break;
        case 3:        
        g.photo3="uploads/"+owner.id+"/album/"+3+".jpeg"; 
        break;
        case 4:
        g.photo4="uploads/"+owner.id+"/album/"+4+".jpeg"; 
        break;
      }
      File origin = photo.getFile();
      File destination = new File("public/uploads/"+owner.id+"/album/"+g.current+".jpeg");
      if(destination.exists()){
        FileUtils.forceDelete(destination);
      }
      FileUtils.moveFile(origin,destination);
      g.current = (g.current+1)%5;
      g.update();
    }catch(IOException io){
      System.out.println("Something failed in file move");
      io.printStackTrace();
    }
    flash("success", new Messages().get("photo.correct"));
    return redirect(routes.StatePhotoC.statePhoto(owner.id));
  }

  public static Result getPhotos(Long id) {
    Gallery photos = Gallery.find.byId(id);
    return ok(Json.toJson(photos));
  }

  public static Result redirect(Long id) {
    return redirect(routes.StatePhotoC.statePhoto(id));
  }
}