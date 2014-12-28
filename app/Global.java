import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;
import static play.mvc.Results.*;

public class Global extends GlobalSettings {

  public Promise<Result> onError(RequestHeader request, Throwable t) {
    return Promise.<Result>pure(internalServerError(
      views.html.errorPage.render()
      ));
  }

  public Promise<Result> onHandlerNotFound(RequestHeader request) {
        return Promise.<Result>pure(notFound(
            views.html.notFoundPage.render()
        ));
    }

  @Override
  public void onStart(Application app) {
    Logger.info("Application has started");
    // Check if the database is empty
    if (User.find.findRowCount() == 0) {
      User u=new User("user1@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=0;
      u.relationship=0;
      u.gender=0;
      u.perversion=0;
      u.whishes="user_whishes";
      Gallery g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user2@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=1;
      u.relationship=2;
      u.gender=1;
      u.perversion=3;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user3@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=2;
      u.relationship=4;
      u.gender=0;
      u.perversion=4;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user4@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=0;
      u.relationship=3;
      u.gender=1;
      u.perversion=3;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user5@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=0;
      u.relationship=0;
      u.gender=0;
      u.perversion=4;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user6@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=2;
      u.relationship=2;
      u.gender=1;
      u.perversion=4;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user7@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=2;
      u.relationship=2;
      u.gender=0;
      u.perversion=2;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user8@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=2;
      u.relationship=0;
      u.gender=1;
      u.perversion=4;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user9@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=0;
      u.relationship=4;
      u.gender=0;
      u.perversion=0;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();

      u=new User("user10@user.com","useruser",new Date());
      u.lastSignIn=new Date();
      u.description="user_description";
      u.username="user_username";
      u.mobile="0000000000";
      u.residence="user_residence";
      u.interests="user_interests";
      u.preference=2;
      u.relationship=2;
      u.gender=1;
      u.perversion=3;
      u.whishes="user_whishes";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.save();
    }
  }
}