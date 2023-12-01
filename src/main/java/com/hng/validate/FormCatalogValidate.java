package com.hng.validate;

import com.hng.dto.request.FormCatalog;
import com.hng.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


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
      if (formCatalog.getName().trim().isEmpty() || formCatalog.getDescription().trim().isEmpty()) {
         errors.rejectValue("field", "field.empty");
      } else if (catalogService.findByName(formCatalog.getName()) != null) {
         errors.rejectValue("catalogName", "catalog.name.existed");
      }
   }
}
