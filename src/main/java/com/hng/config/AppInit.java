package com.hng.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;


public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
   @Override
   protected Class<?>[] getRootConfigClasses() {
      return null;
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[]{WebConfig.class};
   }

   @Override
   protected String[] getServletMappings() {
      return new String[]{"/"};
   }

   @Override
   public Filter[] getServletFilters() {
      CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
      encodingFilter.setForceEncoding(true);
      encodingFilter.setEncoding("utf-8");
      return new Filter[]{encodingFilter};
   }
}
