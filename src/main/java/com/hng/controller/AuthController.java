package com.hng.controller;

import com.hng.dto.request.FormLogin;
import com.hng.dto.request.FormRegister;
import com.hng.model.Cart;
import com.hng.model.User;
import com.hng.service.ICartService;
import com.hng.service.IUserService;
import com.hng.validate.FormLoginValidate;
import com.hng.validate.FormRegisterValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;


@Controller
public class AuthController {
   @Autowired
   private IUserService userService;
   @Autowired
   private FormLoginValidate loginValidate;
   @Autowired
   private FormRegisterValidate registerValidate;
   @Autowired
   private ICartService cartService;

   @RequestMapping("/auth")
   public String login(Model model){
      model.addAttribute("formLogin", new FormLogin());
      return "login";
   }

   // Xử lý login
   @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
   public String doLogin(HttpSession session,
                         Model model,
                         @ModelAttribute("formLogin") FormLogin formLogin,
                         BindingResult bindingResult){
      loginValidate.validate(formLogin, bindingResult);
      if (bindingResult.hasErrors()) {
         return "login";
      }
      User userLogin = userService.findByUsername(formLogin.getUsername());
      session.setAttribute("userLogin", userLogin);

      if (userLogin.isRole()) {
         return "admin/dashboard";
      }

      // Khoi tao cart cho moi user register thanh cong
      Cart cart = cartService.findCartByUserId(userLogin.getId());
      if (cart == null) {
         cart = new Cart(userLogin.getId());
         cartService.save(cart);
      }

      return "index";
   }

   @RequestMapping("/auth/register")
   public String register(Model model){
      model.addAttribute("formRegister", new FormRegister());
      return "register";
   }

   @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
   public String doRegister(@ModelAttribute("formRegister") FormRegister formRegister,
                            BindingResult bindingResult){
      registerValidate.validate(formRegister,bindingResult);
      if (bindingResult.hasErrors()){
         return "register";
      }

      userService.save(formRegister);
      return "redirect:/auth";
   }

   @RequestMapping(value = "/auth/logout")
   public String doLogOut(HttpSession session){
      session.setAttribute("userLogin", null);
      return "index";
   }

   @RequestMapping("/error-403")
   public String err_403(){
      return "error-403";
   }
}
