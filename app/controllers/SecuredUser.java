package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.data.*;

import models.*;
import play.i18n.Messages;

public class SecuredUser extends Security.Authenticator {

  @Override
  public String getUsername(Context ctx) {
    if (ctx.session().get("email") != null) {
      return ctx.session().get("email");
    }
    return null;
  }

  @Override
  public Result onUnauthorized(Context ctx) {
    return redirect(routes.HomeC.home());
  }
}