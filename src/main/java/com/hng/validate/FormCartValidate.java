package com.hng.validate;

import com.hng.dto.request.FormOrderDetail;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FormCartValidate implements Validator {
   @Override
   public boolean supports(Class<?> clazz) {
      return FormOrderDetail.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      FormOrderDetail formOrderDetail = (FormOrderDetail) target;
      if (formOrderDetail.getQuantity() <= 0) {
         errors.rejectValue("quantity", "orderDetail.quantity");
      }
   }
}
