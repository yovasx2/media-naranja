/*
 * LoginForm.java
 * 
 * 1.0
 *
 * 3/12/2014
 * 
 * Copyright Diablo Systems
 */

package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;

/**
 * This class is used to render, restrict and validate a login form
 * @author Giovanni Alberto García
 * @version 1.0
 */
public class SignInForm {

  @Constraints.Required(message="validation.required")
  @Constraints.Email(message="validation.email")
  @Constraints.MaxLength(value=100,message="validation.maxLength")
  public String email;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=40,message="validation.maxLength")
  public String password;

  /**
  * This method validates a form to register a user
  * @return A list of errors by field 
  */
  public List<ValidationError> validate(){
    List<ValidationError> errors = new ArrayList<ValidationError>();
    User u = User.find.where().eq("email", email).findUnique();
    
    if (u == null) 
      // user doesn't exist
      errors.add(new ValidationError("password", "signIn.incorrect"));
    else{
      if(!password.equals(u.getDecryptedPassword()))
      // write password incorrectly
        errors.add(new ValidationError("password","signIn.incorrect")); 
    }
    return errors.isEmpty() ? null : errors;
  } 
}