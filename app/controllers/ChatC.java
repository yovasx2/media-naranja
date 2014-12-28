package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

// added
import play.i18n.Messages;
import views.html.chatIH;
import models.User;

public class ChatC extends Controller {

  public static Result chat(Long id) {
    User user=User.find.where().eq("id",id).findUnique();
    return ok(chatIH.render(user));
  }

}
