package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

// added
import play.i18n.Messages;
import views.html.signInIH;
import models.User;
import models.Ban;
import models.Post;
import java.util.List;
import play.libs.Json; // serve

public class BanC extends Controller {

  @Security.Authenticated(SecuredUser.class)
  public static Result doBan(Long id){
    User banner = User.find.where().eq("email",session("email")).findUnique();
    User banned = User.find.byId(id);
    // if desired user(banned) does not exist
    if(banned==null){
      return Results.notFound(views.html.notFoundPage.render());
    }
    if(!banner.equals(banned)){
      Ban b=new Ban();
      b.banner=banner;
      b.banned=banned;
      b.save();

      // find banned user's posts to me
      List<Post> posts=Post.findBannedUserPosts(banner.email,banned.email);
      // delete them
      for(Post post : posts){
        post.delete();
      }
    }
    flash("success",new Messages().get("ban.correct"));
    return redirect(routes.HomeC.home());
  }

}