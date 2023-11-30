package com.hng.validate;

import com.hng.dto.request.FormLogin;
import com.hng.model.User;
import com.hng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
      User user = userService.findByUserName(formLogin.getUsername());
      if (formLogin.getUsername().trim().isEmpty()) {
         errors.reject("username", "login.username.empty");
      } else if (user == null) {
         errors.reject("username", "login.username.incorrect");
      }
      if (formLogin.getPassword().trim().isEmpty()){
         errors.reject("password", "login.password.empty");
      }else {
         assert user != null;
         if (!user.getPassword().equals(formLogin.getPassword().trim())){
            errors.reject("password", "login.password.incorrect");
         }
      }
   }
}
