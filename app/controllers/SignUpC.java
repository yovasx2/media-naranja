package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

// added
import play.i18n.Messages;
import views.html.signUpIH;
import forms.SignUpForm;
import models.User;
import models.Gallery;
import java.util.Date;

public class SignUpC extends Controller {

  public static Result signUp() {
    // already there is session
    if(session("email")!=null){
      return redirect(routes.HomeC.home());
    }
    Form<SignUpForm> signUpForm = Form.form(SignUpForm.class);
    return ok(signUpIH.render(signUpForm));
  }

  public static Result doSignUp(){
    Form<SignUpForm> signUpForm = Form.form(SignUpForm.class).bindFromRequest(); 
    if(signUpForm.hasErrors())
      return badRequest(signUpIH.render(signUpForm));
    else{
      // Create and save user
      User u=new User(signUpForm.field("email").value(),
        signUpForm.field("password").value(),
        new Date(signUpForm.field("birth").value()));
      u.lastSignIn=new Date();
      Gallery g =new Gallery();
      g.owner=u;
      g.save();
      u.save();
      // put info for user
      flash("success",new Messages().get("success.registration"));
      return redirect(routes.HomeC.home());
    } 
  }

}
