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
import com.fasterxml.jackson.annotation.*;

/**
 * This class represent
 * @author Giovanni Alberto García
 * @version 1.0
 */
@Entity
public class Gallery extends Model {

  @Id
  public Long id;

  public int current;

  public String photo0;

  public String photo1;
  
  public String photo2;
  
  public String photo3;
  
  public String photo4;

  @OneToOne @MapsId
  @JsonIgnore
  public User owner;
  
  /*
  * This is used to execute queries
  */
  public static Finder<Long,Gallery> find = new Finder<Long,Gallery>(
    Long.class, Gallery.class
    ); 

}