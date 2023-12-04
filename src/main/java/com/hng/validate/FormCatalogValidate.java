package com.hng.validate;

import com.hng.dto.request.FormCatalog;
import com.hng.model.Catalog;
import com.hng.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;


@Component
public class FormCatalogValidate implements Validator {
   @Autowired
   private ICatalogService catalogService;
   @Override
   public boolean supports(Class<?> clazz) {
      return FormCatalog.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      FormCatalog formCatalog = (FormCatalog) target;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.empty");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.empty");

      if (!errors.hasFieldErrors()){

         // Check tên không trùng lặp
         if (catalogService.checkNameExist(formCatalog.getId(), formCatalog.getName())) {
            errors.rejectValue("name", "catalog.name.existed");
         }
      }
   }
}
