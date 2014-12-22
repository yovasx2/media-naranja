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
public class SignUpForm {

  @Constraints.Required(message="validation.required")
  @Constraints.Email(message="validation.email")
  @Constraints.MaxLength(value=100,message="validation.maxLength")
  public String email;

  @Constraints.Required(message="validation.required")
  @Constraints.MinLength(value=8, message="validation.minLength")
  @Constraints.MaxLength(value=40,message="validation.maxLength")
  public String password;

  @Constraints.Required(message="validation.required")
  @Constraints.MaxLength(value=40,message="validation.maxLength")
  public String confirmation;

  @Constraints.Required(message="validation.required")
  @Constraints.Pattern(value="[01][0-9]/[0-3][0-9]/[12][09][0-9][0-9]")
  @Constraints.MaxLength(value=10,message="validation.maxLength")  
  public String birth;

  @Constraints.Required(message="validation.required")
  public boolean acceptance;


  /**
  * This method validates a form to register a user
  * @return A list of errors by field 
  */
  public List<ValidationError> validate(){
    List<ValidationError> errors = new ArrayList<ValidationError>();
    User u = User.find.where().eq("email", email).findUnique();
    // Already registered
    if (u != null) 
      errors.add(new ValidationError("email", "validation.email.alreadyExist"));
    // write password correctly
    if(!password.equals(confirmation))
      errors.add(new ValidationError("confirmation","validation.confirmation")); 
    // More then 18 years
    long birthSec=new Date(birth).getTime();
    long actualSec=new Date().getTime();
    long difSec=actualSec-birthSec;
    if(difSec<568024668000L)
      errors.add(new ValidationError("birth","validation.less18"));
    // Accept conditions
    if(!acceptance)
      errors.add(new ValidationError("acceptance",""));  

    return errors.isEmpty() ? null : errors;
  } 
}