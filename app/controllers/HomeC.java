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
    long limit=User.find.orderBy("id desc").setMaxRows(1).findUnique().id;
    Random r=new Random();
    long random;
    User user;
    do{
      do{
        random=(r.nextLong())%limit;
      }while(random<1); // repeat if id is no valid
      user=User.find.where().eq("id",(Long)random).findUnique();
    }while(user.isDesactive==true); //repeat if user is desactive
    return ok(homeIH.render(user));
  }

}