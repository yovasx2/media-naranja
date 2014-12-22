package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class HomeC extends Controller {

    public static Result home() {
        return ok(homeIH.render("Principal"));
    }

}
