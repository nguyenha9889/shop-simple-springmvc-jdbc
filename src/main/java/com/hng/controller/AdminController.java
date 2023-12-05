package com.hng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

   @RequestMapping
   public String dashboard(Model model){
      model.addAttribute("view", "dashboard");
      return "admin/dashboard";
   }
   @RequestMapping("/user")
   public String user(){
      return "admin/dashboard";
   }
}
