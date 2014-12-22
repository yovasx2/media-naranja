package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

// added
import play.i18n.Messages;
import views.html.editDeleteIH;
import models.User;

public class EditDeleteC extends Controller {

  public static Result editDelete() {
    Form<User> userForm = Form.form(User.class);
    User u = User.find.where().eq("email",session("email")).findUnique();
    userForm=userForm.fill(u);
    return ok(editDeleteIH.render(userForm));
  }

  public static Result doEditDelete(){
    Form<User> userForm = Form.form(User.class).bindFromRequest(); 
    User user = User.find.where().eq("email",session("email")).findUnique();

    // desactive profile if desired
    if(userForm.field("isDesactive").valueOr("").equals("on")){
      user.isDesactive=true;
      user.save();
      return redirect(routes.SessionC.signOut());
    }

    // password write correctly
    if(!userForm.field("password").value().equals(userForm.field("confirmation").value())){
      userForm.reject("confirmation",new Messages().get("validation.confirmation"));
      return badRequest(editDeleteIH.render(userForm));   
    }

    User userMod = User.find.where().eq("email", userForm.field("email").valueOr("")).findUnique();
    if(!userForm.field("email").value().equals(session("email"))&&userMod!=null){
      // already registered
      userForm.reject("email",new Messages().get("validation.email.alreadyExists"));
      return badRequest(editDeleteIH.render(userForm));   
    }

    if(userForm.hasErrors())
      return badRequest(editDeleteIH.render(userForm));
    else{
      userMod=userForm.get();
      if(user.email!=userMod.email){
        user.email=userMod.email;
        session("email",userForm.field("email").value());
      }
      if(userMod.password!=null)
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
      user.save();
      flash("success",new Messages().get("success.profileEdition"));
      return redirect(routes.EditDeleteC.editDelete());
    }
  }

}
