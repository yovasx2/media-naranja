package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

// added
import play.i18n.Messages;
import views.html.detailsIH;
import models.User;
import java.text.SimpleDateFormat;

public class DetailsC extends Controller {

  public static Result details(Long id) {
    User user=User.find.where().eq("id",id).findUnique();
    // fixme: not found user
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    String date = DATE_FORMAT.format(user.birth);
    return ok(detailsIH.render(user,date));
  }

}
