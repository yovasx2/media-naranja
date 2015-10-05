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
import play.libs.Crypto; // crypt AES
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

/**
 * This class represent a user model 
 * @author Giovanni Alberto García
 * @version 1.0
 */
@Entity
@Table(name="users") //user is reserved in postgres
public class User extends Model {

  @Id
  public Long id;

  @Constraints.Required(message="validation.required")
  @Constraints.Email(message="validation.email")
  @Constraints.MaxLength(value=100,message="validation.maxLength")
  @Column(unique=true, nullable=false, length=100)
  public String email;


  @Constraints.MinLength(value=8, message="validation.minLength")
  @Constraints.MaxLength(value=40, message="validation.maxLength")
  @Column(nullable=false, length=128)
  @JsonIgnore
  public String password;

  public String photo;

  public Date birth;
  
  @JsonIgnore
  public Date lastSignIn;

  // second part of registration
  @JsonIgnore
  public boolean isDesactive;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=400,message="validation.maxLength")
  @Column(columnDefinition = "varchar(400)")
  public String description;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=30,message="validation.maxLength")
  @Column(columnDefinition = "varchar(30)")
  public String username;  

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=10,message="validation.maxLength")
  @Column(columnDefinition = "varchar(10)")
  @Constraints.Pattern(value="[0-9]{5,10}")
  public String mobile;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=100,message="validation.maxLength")
  @Column(columnDefinition = "varchar(100)")
  public String residence;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=400,message="validation.maxLength")
  @Column(columnDefinition = "varchar(400)")
  public String interests;

  @Constraints.Required(message="validation.required")
  public int preference;
  
  @Constraints.Required(message="validation.required")
  public int relationship;
  
  @Constraints.Required(message="validation.required")
  public int perversion;

  @Constraints.Required(message="validation.required")
  public int gender;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=400,message="validation.maxLength")
  @Column(columnDefinition = "varchar(400)")
  public String whishes;

  /**
  * This constructor cypher the password to store in database
  * @param email      The user's email
  * @param password   The user's password
  */
  public User(String email, String password, Date birth){
    this.email = email;
    setDecryptedPassword(password);
    this.birth=birth;
  }

  /*
  * This is used to execute queries
  */
  public static Finder<Long,User> find = new Finder<Long,User>(
    Long.class, User.class
    ); 

  /**
  * This method is used to cypher a password and assign it to user
  * @param password   The new desired password in plain text
  */
  public void setDecryptedPassword(String password){
    this.password = new Crypto().encryptAES(password);
  }

  /**
  * This method is used to obtain the current password in plain text
  * @return A string in plain text representing the user's password
  */
  @JsonIgnore
  public String getDecryptedPassword(){
    return new Crypto().decryptAES(password);
  }
  
}