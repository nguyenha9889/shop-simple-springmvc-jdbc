package com.hng.validate;

import com.hng.dto.request.FormDetail;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FormCartValidate implements Validator {
   @Override
   public boolean supports(Class<?> clazz) {
      return FormDetail.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      FormDetail formDetail = (FormDetail) target;
      if (formDetail.getQuantity() <=0) {
         errors.rejectValue("quantity", "orderDetail.quantity");
      }
   }
}
