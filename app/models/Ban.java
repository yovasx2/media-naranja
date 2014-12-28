/*
 * User.java
 * 
 * 1.0
 *
 * 3/12/2014
 * 
 * Copyright
 */
package models;

// model
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

// added
import play.data.format.*; // date format
import play.data.validation.*; // constraints

/**
 * This class represent
 * @author Giovanni Alberto García
 * @version 1.0
 */
@Entity
public class Ban extends Model {

  @Id
  public Long id;

  @ManyToOne
  public User banner;

  @ManyToOne
  public User banned;

  /*
  * This is used to execute queries
  */
  public static Finder<Long,Ban> find = new Finder<Long,Ban>(
    Long.class, Ban.class
    ); 

}