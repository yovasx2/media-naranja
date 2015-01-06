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
import java.util.List;

/**
 * This class 
 * @author Giovanni Alberto García
 * @version 1.0
 */
@Entity
public class Post extends Model {

  @Id
  public Long id;

  @ManyToOne
  public User publisher;

  @ManyToOne
  public User published;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=255,message="validation.maxLength")
  @Column(length=255)
  public String content;

  @Constraints.MaxLength(value=255,message="validation.maxLength")
  @Column(length=255)
  @Constraints.Pattern(value="https{0,1}://.+")
  public String link;

  /*
  * This is used to execute queries
  */
  public static Finder<Long,Post> find = new Finder<Long,Post>(
    Long.class, Post.class
    ); 

  public static List<Post> findRecentPosts(String email) {
    return find.where().eq("published.email",email).setMaxRows(200).orderBy("id desc").findList();
  }

  public static List<Post> findBannedUserPosts(String published, String publisher) {
    return find.where().eq("published.email",published).where().eq("publisher.email",publisher).findList();
  }

}