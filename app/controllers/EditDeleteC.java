package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

// added
import play.i18n.Messages;
import views.html.editDeleteIH;
import models.User;
//file upload
import play.mvc.Http.MultipartFormData;
import java.io.File;
import java.io.IOException;
import play.mvc.Http.MultipartFormData.FilePart;
import org.apache.commons.io.*;

public class EditDeleteC extends Controller {

  @Security.Authenticated(SecuredUser.class)
  public static Result editDelete() {
    Form<User> userForm = Form.form(User.class);
    User u = User.find.where().eq("email",session("email")).findUnique();
    userForm=userForm.fill(u);
    return ok(editDeleteIH.render(userForm));
  }

  @Security.Authenticated(SecuredUser.class)
  public static Result doEditDelete(){
    Form<User> userForm = Form.form(User.class).bindFromRequest();
    User user = User.find.where().eq("email",session("email")).findUnique();
    
    // desactive profile if desired
    if(userForm.field("isDesactive").valueOr("").equals("on")){
      user.isDesactive=true;
      user.save();
      return redirect(routes.SessionC.signOut());
    } 

    // reject if there is no profile photo
    MultipartFormData body = request().body().asMultipartFormData();
    FilePart photo = body.getFile("photo");
    if(photo==null&&user.photo==null){
      flash("error",new Messages().get("validation.error"));
      userForm.reject("photo",new Messages().get("validation.file.no"));
      return badRequest(editDeleteIH.render(userForm));
    }

    // bad file format
    if(photo!=null){
      String contentType = photo.getContentType();
      if(!contentType.equalsIgnoreCase("image/jpeg")){
        flash("error",new Messages().get("validation.error"));
        return badRequest(editDeleteIH.render(userForm));
      }
    }

    // password write correctly
    if(!userForm.field("password").value().equals(userForm.field("confirmation").value())){
      userForm.reject("confirmation",new Messages().get("validation.confirmation"));
      flash("error",new Messages().get("validation.error"));
      return badRequest(editDeleteIH.render(userForm));   
    }

    User userMod = User.find.where().eq("email", userForm.field("email").valueOr("")).findUnique();
    if(!userForm.field("email").value().equals(session("email"))&&userMod!=null){
      // already registered
      userForm.reject("email",new Messages().get("validation.email.alreadyExists"));
      flash("error",new Messages().get("validation.error"));
      return badRequest(editDeleteIH.render(userForm));   
    }

    if(userForm.hasErrors()){
      flash("error",new Messages().get("validation.error"));
      return badRequest(editDeleteIH.render(userForm));
    }else{
      userMod=userForm.get();
      if(user.email!=userMod.email){
        user.email=userMod.email;
        session("email",userForm.field("email").value());
      }

      if(userMod.password!=null && !userMod.password.equals(""))
        user.setDecryptedPassword(userMod.password);
      user.description=userMod.description;
      user.username=userMod.username;
      user.mobile=userMod.mobile;
      user.residence=userMod.residence;
      user.interests=userMod.interests;
      user.preference=userMod.preference;
      user.relationship=userMod.relationship;
      user.gender=userMod.gender;
      user.perversion=userMod.perversion;
      user.whishes=userMod.whishes;

      // photo move file and preserve URI
      if(photo!=null){
        try{
          if(new File("public/"+user.photo).exists()){
            FileUtils.forceDelete(new File("public/"+user.photo));
          }
          user.photo="uploads/"+user.id+"/profile/"+photo.getFilename(); 
          File origin=photo.getFile();
          File destination= new File("public/uploads/"+user.id+"/profile/"+photo.getFilename());
          FileUtils.moveFile(origin,destination);
        }catch(IOException io){
          System.out.println("Something failed in file move");
          io.printStackTrace();
        }
      }
      user.save();
      
      flash("success",new Messages().get("success.profileEdition"));
      return redirect(routes.DetailsC.details(user.id));
    }
  }

}