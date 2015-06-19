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
      User u=new User("felipe@gmail.com","password",new Date(90,1,2));
      u.lastSignIn=new Date();
      u.description="Soy una persona alivianada me gusta chambear y viajar así"+ 
      "mucho, verás que soy muy interesante";
      u.username="Felipe Carrillo";
      u.mobile="5512131415";
      u.residence="Mexico DF";
      u.interests="Música pesada, teatro, trabajar duro, deporte, danza";
      u.preference=0;
      u.relationship=0;
      u.gender=0;
      u.perversion=0;
      u.whishes="Una pareja estable que tenga mucho tiempo libre para pasear";
      Gallery g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/1/profile/1.jpg";
      u.save();

      u=new User("ana@gmail.com","password",new Date(90,3,5));
      u.lastSignIn=new Date();
      u.description="Soy una persona sumamente tierna, dedicada y muy comprometida"+
      "con todos los retos tanto personales como laborales";
      u.username="Ana Cárdenas";
      u.mobile="5582917834";
      u.residence="Cuatla, Morelos";
      u.interests="Me gusta la agricultura y todo lo que tenga que ver con ecología y animales,"+
      " me encanta nadar y andar a caballo";
      u.preference=1;
      u.relationship=2;
      u.gender=1;
      u.perversion=3;
      u.whishes="Deseo una compañera de aventuras que sea amable y me cuide";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/2/profile/2.jpg";
      u.save();

      u=new User("arely@hotmail.com","password",new Date(91,4,5));
      u.lastSignIn=new Date();
      u.description="Soy alegre y muy bailadora, me encanta salir de fiesta y también "+
      "trabajo duro por lo que quiero y me gusta";
      u.username="Arely Coruña";
      u.mobile="5518172358";
      u.residence="Guadalajara, Jalisco";
      u.interests="El campismo, el alpinismo, las tardeadas, la música clásica...";
      u.preference=2;
      u.relationship=4;
      u.gender=1;
      u.perversion=4;
      u.whishes="Soy una mujer muy abierta, en busca de alguien especial "+
      "que me haga vibrar, que me acompañe a todos lados, sea amoroso y comprensivo";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/3/profile/3.jpg";
      u.save();

      u=new User("leona@yahoo.com","password",new Date(89,3,15));
      u.lastSignIn=new Date();
      u.description="Parezco un corderito pero verás que soy toda una leona "+
      "muchas como yo no hay, aprovechame y valorame";
      u.username="Leona princesa";
      u.mobile="5510282827";
      u.residence="Zapopan, Jalisco";
      u.interests="El fútbol, las artes contemporaneas, los torneos de ajedrez";
      u.preference=0;
      u.relationship=3;
      u.gender=1;
      u.perversion=3;
      u.whishes="Deseo todo un príncipe que me salve a diario de los peligros de esta sociedad, "+
      "o una reina que sepa tratarme bien";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/4/profile/4.jpg";
      u.save();

      u=new User("chucho@gmail.com","password",new Date(88,0,5));
      u.lastSignIn=new Date();
      u.description="Un hombre joven, activo, decidido, emprendedor, disciplinado, "+
      "jamás pasarás ratos aburridos a mi lado, no te hará falta nada, serás feliz...";
      u.username="Jesús Anaya";
      u.mobile="5567985463";
      u.residence="Mexico DF";
      u.interests="Los deportes, en especial el fucho, la escuela, educarte es importante, "+
      "todos los juegos de mesa, ya verás que tendremos suerte";
      u.preference=0;
      u.relationship=0;
      u.gender=0;
      u.perversion=4;
      u.whishes="Alguien activo, coherente, persistente, motivador, ambicioso";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/5/profile/5.jpg";
      u.save();

      u=new User("francesca@hotmail.com","password",new Date(85,3,21));
      u.lastSignIn=new Date();
      u.description="Me encanta viajar a todos lados, salir en la noche y cnocer amigos o amigas";
      u.username="Francesca Herrera";
      u.mobile="5583928372";
      u.residence="Mexico DF";
      u.interests="Me gustaría terminar una maestría y seguir estudiando par apoder superarme "+
      "día a día contra las expectativas de todos los que me rodean";
      u.preference=2;
      u.relationship=2;
      u.gender=1;
      u.perversion=4;
      u.whishes="Me encantaría tener a alguien a mi lado para poder decir que: "+
      "Sólo llegas más rápido, pero acompañado llegas más lejos";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/6/profile/6.jpg";
      u.save();

      u=new User("jox@gmail.com","password",new Date(90,11,11));
      u.lastSignIn=new Date();
      u.description="Me fascinan las artesanías y los trabajos hechos a mano, soy fan del arte";
      u.username="Joana Hernández";
      u.mobile="5512231324";
      u.residence="Villa Hermosa , Tabasco";
      u.interests="Cosas tecnológicas, cosas de electronica, la modernidad, los "+
      "avances científicos, los nuevos hallazgos que revolucionarán todo.";
      u.preference=2;
      u.relationship=2;
      u.gender=1;
      u.perversion=2;
      u.whishes="Alguien apasionado como yo, capaz de abstraerse por horas y "+
      "quede cautivado con todo lo que a mí me fascina";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/7/profile/7.jpg";
      u.save();

      u=new User("mario@yahoo.com","password",new Date(93,7,8));
      u.lastSignIn=new Date();
      u.description="Un chico particular con detalles en cada rincón de su personalidad";
      u.username="Mario Jiménez";
      u.mobile="5528392737";
      u.residence="Cancún, Quintana Roo";
      u.interests="El surf, la fiesta, las bebidas, la party, ya sabes, la vida";
      u.preference=2;
      u.relationship=0;
      u.gender=0;
      u.perversion=4;
      u.whishes="Alguien aventurado y muy social, para que me acompañe a todos lados y cnozca el mundo";
      g=new Gallery();
      g.owner=u;
      g.save();
      u.photo="uploads/8/profile/8.jpg";
      u.save();
    }
  }
}