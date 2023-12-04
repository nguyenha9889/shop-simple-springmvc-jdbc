package com.hng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
   @RequestMapping({"/",""})
   public String home(){
      return "index";

   }
   @RequestMapping("/403")
   public String _403(){
      return "error-403";
   }
}
