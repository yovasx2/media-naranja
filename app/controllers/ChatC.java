package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

// added
import play.i18n.Messages;
import views.html.chatIH;
import models.User;
import play.mvc.WebSocket;
import play.libs.F.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

public class ChatC extends Controller {

  private static List<WebSocket.Out<String>> sockets=new ArrayList<WebSocket.Out<String>>();
  private static HashMap<WebSocket.Out<String>,String> users=new HashMap<WebSocket.Out<String>,String>();


  @Security.Authenticated(SecuredUser.class)
  public static Result chat() {
    // User buddy=User.find.where().eq("id",id).findUnique();
    User user = User.find.where().eq("email",session("email")).findUnique();

    // if(user.equals(buddy)){
    //   flash("error",new Messages().get("chat.invalidUser"));
    //   return redirect(routes.HomeC.home());
    // }

    return ok(chatIH.render(user));
  }

  public static WebSocket<String> message() {
    return new WebSocket<String>() {
      public void onReady(WebSocket.In<String> in, final WebSocket.Out<String> out) {
        sockets.add(out);
        // For each event received on the socket,
        in.onMessage(new Callback<String>() {
          public void invoke(String event) {
            for (WebSocket.Out<String> out : sockets) {
              out.write(event);
            }          
          }
        });

        in.onClose(new Callback0(){
          public void invoke(){
            sockets.remove(out);
          }
        }); 

        out.write("Bienvenido al chat de Media Naranja.<br><br>"
          +"Por favor respeta a los demás usuarios.");
      }
    };
  }

  public static WebSocket<String> users() {
    return new WebSocket<String>() {
      public void onReady(WebSocket.In<String> in, final WebSocket.Out<String> out) {
        // For each event received on the socket,
        in.onMessage(new Callback<String>() {
          public void invoke(String event) {
            users.put(out,event);
            sendNames();        
          }
        });

        in.onClose(new Callback0(){
          public void invoke(){
            users.remove(out);
            sendNames();
          }
        });

      }
    };
  }

  private static void sendNames(){
    String names="";
    Object[] usersArray=users.values().toArray();
    Arrays.sort(usersArray);
    for(Object user : usersArray){
      names+=(String)user+"\n";
    }

    for(WebSocket.Out<String> out: users.keySet()){
      out.write(names);
    }
  }

}
