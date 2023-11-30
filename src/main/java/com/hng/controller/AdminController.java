package com.hng.controller;

import com.hng.dto.request.FormLogin;
import com.hng.model.User;
import com.hng.service.IUserService;
import com.hng.validate.FormLoginValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

   @Autowired
   private IUserService userService;

   @Autowired
   private FormLoginValidate loginValidate;

   /**
    * Các request tới /admin sẽ yêu cầu đăng nhập
    * @param model FormLogin
    * @return Login Page
    */
   @RequestMapping
   public String login(Model model){
      model.addAttribute("formLogin", new FormLogin());
      return "admin/login";
   }

   // Xử lý login
   @RequestMapping(value = "/auth", method = RequestMethod.POST)
   public String doLogin(HttpSession session,
                         @ModelAttribute("formLogin") FormLogin formLogin,
                         Model model, BindingResult bindingResult){
      loginValidate.validate(formLogin, bindingResult);
      if (bindingResult.hasErrors()) {
         return "admin/login";
      }
      User admin = userService.findByUserName(formLogin.getUsername());
      session.setAttribute("adminLogin", admin);
      return "admin/index";
   }
}
