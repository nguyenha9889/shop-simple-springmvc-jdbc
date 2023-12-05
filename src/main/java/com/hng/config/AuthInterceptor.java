package com.hng.config;

import com.hng.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      User account = (User) request.getSession().getAttribute("userLogin");
      if (account == null){
         response.sendRedirect("/auth");
         return false;
      }
      if(account.isRole()){
         return true;
      }
      response.sendRedirect("/error-403");
      return false;
   }
}
