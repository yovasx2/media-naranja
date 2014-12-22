package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

// added
import play.i18n.Messages;
import views.html.signInIH;
import forms.SignInForm;
import models.User;
import java.util.Date;

public class SessionC extends Controller {

  public static Result signIn() {
    // already there is session
    if(session("email")!=null){
      return redirect(routes.HomeC.home());
    }
    Form<SignInForm> signInForm = Form.form(SignInForm.class);
    return ok(signInIH.render(signInForm));
  }

  public static Result doSignIn(){
    Form<SignInForm> signInForm = Form.form(SignInForm.class).bindFromRequest(); 
    if(signInForm.hasErrors())
      return badRequest(signInIH.render(signInForm));
    else{
      // credentials accepted
      User u=User.find.where().eq("email",
        signInForm.field("email").value()).findUnique();
      u.lastSignIn=new Date();
      u.isDesactive=false;
      u.save();
      session().clear();
      session("email",signInForm.field("email").value());
      // cache not store password
      response().setHeader("Cache-Control","no-store, no-cache, must-revalidate");
      response().setHeader("Pragma","no-cache");
      flash("success",new Messages().get("signIn.correct"));  
      return redirect(routes.HomeC.home());
    } 
  }

  public static Result signOut(){
    session().clear(); // Erase all
    flash("success",new Messages().get("signOut.correct"));
    return redirect(routes.HomeC.home());
  }

}
