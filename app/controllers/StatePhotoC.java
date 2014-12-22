package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

// added
import play.i18n.Messages;
import views.html.statePhotoIH;
import models.User;

public class StatePhotoC extends Controller {

  public static Result statePhoto(Long id) {
    User user=User.find.where().eq("id",id).findUnique();
    return ok(statePhotoIH.render(user));
  }

}
