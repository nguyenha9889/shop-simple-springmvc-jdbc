package com.hng.controller;

import com.hng.dto.request.FormLogin;
import com.hng.model.User;
import com.hng.service.ICatalogService;
import com.hng.service.IUserService;
import com.hng.validate.FormLoginValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

   @Autowired
   private IUserService userService;

   @Autowired
   private FormLoginValidate loginValidate;

   @Autowired
   private ICatalogService catalogService;

   // check session admin login
   public User getSessionUser(HttpSession session){
      return (User) session.getAttribute("adminLogin");
   }

   /**
    * Yêu cầu login khi request tới /admin
    * Nếu không phải admin, sẽ quay lại trang Login
    * Nếu là admin sẽ truy cập dashboard
    */
   @GetMapping
   public String dashboard(HttpSession session, Model model){
      if (getSessionUser(session) == null) {
         model.addAttribute("formLogin", new FormLogin());
         return "admin/login";
      }
      model.addAttribute("view", "dashboard");
      return "admin/index";
   }

   // Xử lý login
   @PostMapping
   public String login(HttpSession session,
                         @ModelAttribute("formLogin") @Validated FormLogin formLogin,
                         Model model, BindingResult bindingResult){
      loginValidate.validate(formLogin, bindingResult);
      if (bindingResult.hasErrors()) {
         return "admin/login";
      }
      User user = userService.findByUsername(formLogin.getUsername());
      session.setAttribute("adminLogin", user);
      model.addAttribute("view", "dashboard");
      return "admin/index";
   }

   @RequestMapping("/catalog")
   public String catalog(HttpSession session,
                         Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size){

      if (getSessionUser(session) == null) {
         return "admin/login";
      }
      model.addAttribute("catalogs",catalogService.findAll(page,size));
      model.addAttribute("current_page",page);
      model.addAttribute("size",size);
      model.addAttribute("total_page",new int[catalogService.getTotalPage(size)]);
      model.addAttribute("view","catalog");
      return "admin/index";
   }

   @RequestMapping("/product")
   public String product(HttpSession session){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }
      return "admin/index";
   }

   @RequestMapping("/user")
   public String user(HttpSession session){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }
      return "admin/index";
   }
}
