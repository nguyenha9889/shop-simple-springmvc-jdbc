package com.hng.controller;

import com.hng.dto.request.FormLogin;
import com.hng.dto.request.FormRegister;
import com.hng.model.User;
import com.hng.service.IUserService;
import com.hng.validate.FormLoginValidate;
import com.hng.validate.FormRegisterValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class UserController {
   @Autowired
   private IUserService userService;
   @Autowired
   private FormLoginValidate loginValidate;
   @Autowired
   private FormRegisterValidate registerValidate;

   @RequestMapping
   public String login(@ModelAttribute("formLogin") FormLogin formLogin){
      return "login";
   }

   // Xử lý login
   @PostMapping("/login")
   public String doLogin(HttpSession session,
                           @ModelAttribute("formLogin") @Validated FormLogin formLogin,
                           BindingResult bindingResult){
      loginValidate.validate(formLogin, bindingResult);
      if (bindingResult.hasErrors()) {
         return "login";
      }
      User user = userService.findByUsername(formLogin.getUsername());
      session.setAttribute("userLogin", user);
      return "index";
   }

   @RequestMapping("/register")
   public String register(@ModelAttribute("formRegister") FormRegister formRegister){
      return "register";
   }

   @PostMapping("/register")
   public String doRegister(@ModelAttribute("formRegister") FormRegister formRegister,
                            BindingResult bindingResult){
      registerValidate.validate(formRegister,bindingResult);
      if (bindingResult.hasErrors()){
         return "register";
      }
      userService.register(formRegister);
      return "login";
   }
}
