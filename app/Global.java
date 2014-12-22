import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
  @Override
  public void onStart(Application app) {
    // Check if the database is empty
    if (User.find.findRowCount() == 0) {
      User u=new User("q@q.q","qqqqqqqq",new Date());
      u.lastSignIn=new Date();
      u.save();
    }
  }
}