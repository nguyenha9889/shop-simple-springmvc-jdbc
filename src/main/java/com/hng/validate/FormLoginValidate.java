package com.hng.validate;

import com.hng.dto.request.FormLogin;
import com.hng.model.User;
import com.hng.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class FormLoginValidate implements Validator {
   @Autowired
   private IUserService userService;
   @Override
   public boolean supports(Class<?> clazz) {
      return FormLogin.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      FormLogin formLogin = (FormLogin) target;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");

      if (!errors.hasFieldErrors()) {
         User user = userService.findByUsername(formLogin.getUsername());
         if (user == null){
            errors.rejectValue("username", "login.username.incorrect");
         }

         if (user != null && !BCrypt.checkpw(formLogin.getPassword(), user.getPassword())) {
            errors.rejectValue("password", "login.password.incorrect");
         }
      }
   }
}
