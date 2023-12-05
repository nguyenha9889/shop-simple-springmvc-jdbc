package com.hng.validate;

import com.hng.dto.request.FormRegister;
import com.hng.model.User;
import com.hng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;


@Component
public class FormRegisterValidate implements Validator {
   @Autowired
   IUserService userService;
   @Override
   public boolean supports(Class<?> clazz) {
      return FormRegister.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      FormRegister formRegister = (FormRegister) target;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");

      if (!errors.hasFieldErrors()){
         if (!formRegister.getEmail().matches("^[a-zA-Z]+[a-zA-Z0-9]*@{1}[a-zA-Z]+mail.com$")){
            errors.rejectValue("email", "register.email.invalid");
         }

         if (!formRegister.getPhone().matches("^(84|0[3|5|7|8|9])+([0-9]{8})$")){
            errors.rejectValue("phone", "register.phone.invalid");
         }

         User user = userService.findByUsername(formRegister.getUsername());
         if (user != null){
            errors.rejectValue("username", "register.username.existed");
         }

         if (user != null && user.getEmail().equalsIgnoreCase(formRegister.getEmail())) {
            errors.rejectValue("email", "register.email.existed");
         }

         if (!Objects.equals(formRegister.getPassword(), formRegister.getRePassword())) {
            errors.rejectValue("rePassword", "register.rePassword");
         }
      }
   }
}
