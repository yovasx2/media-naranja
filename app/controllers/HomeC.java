package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

// added
import models.User;
import java.util.List;
import play.libs.Json; // serve
import java.util.Random;

public class HomeC extends Controller {

  public static Result home() {
    // get the greatest id in users table
    long limit=User.find.orderBy("id desc").setMaxRows(1).findUnique().id;
    Random r = new Random();
    long random;
    User user;
    do{
      do{
        // get a random number within the limit
        random=(r.nextLong())%limit;
      }while(random<1); // repeat if id is no valid
      // get a user with that id
      user=User.find.where().eq("id",(Long)random).findUnique();
      // sometimes ids in table are not continuos, so discard null users
    }while(user==null || user.isDesactive==true || user.photo==null); //repeat if user is desactive or null
    // print valid user
    return ok(homeIH.render(user));
  }

}