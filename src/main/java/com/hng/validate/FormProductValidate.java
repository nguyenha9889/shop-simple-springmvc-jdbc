package com.hng.validate;

import com.hng.dto.request.FormProduct;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FormProductValidate implements Validator {
   @Autowired
   private IProductService productService;
   @Override
   public boolean supports(Class<?> clazz) {
      return FormProduct.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      FormProduct formProduct = (FormProduct) target;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitPrice", "field.empty");

      if (!errors.hasFieldErrors()){

         if (productService.checkNameExist(formProduct.getId(), formProduct.getName())) {
            errors.rejectValue("name", "product.name.existed");
         }

         if (formProduct.getName().length() < 6) {
            errors.rejectValue("name", "field.length");
         }

         if (formProduct.getId() == null && formProduct.getImage().getSize() == 0) {
            errors.rejectValue("image", "product.image.empty");
         }

         if (formProduct.getUnitPrice() <= 0L) {
            errors.rejectValue("unitPrice", "product.price");
         }
      }
   }
}
