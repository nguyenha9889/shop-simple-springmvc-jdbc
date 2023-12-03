package com.hng.validate;

import com.hng.dto.request.FormLogin;
import com.hng.model.User;
import com.hng.service.IUserService;
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

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "login.username.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.password.empty");

      User user = userService.findByUsername(formLogin.getUsername());
      if (!errors.hasFieldErrors()) {
         if (user == null){
            errors.rejectValue("username", "login.username.incorrect");
         } else if (!user.isRole()) {
            errors.rejectValue("username", "login.username.roles");
         }
      }

      if (user != null && !Objects.equals(user.getPassword(), formLogin.getPassword())) {
         errors.rejectValue("password", "login.password.incorrect");
      }
   }
}
