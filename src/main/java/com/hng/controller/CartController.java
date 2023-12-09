package com.hng.controller;

import com.hng.model.Cart;
import com.hng.model.OrderDetail;
import com.hng.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class CartController {

   @RequestMapping("/cart")
   public String cart(HttpSession session){
      if (session.getAttribute("userLogin") == null) {
         return "redirect:/auth";
      }
      return "client/cartPage";
   }

   @PostMapping("/cart")
   public String add(HttpSession session, Model model){
      if (session.getAttribute("userLogin") == null) {
         return "redirect:/client/productPage";
      }

      User user = (User) session.getAttribute("userLogin");

       model.getAttribute("total");
      return "redirect:/client/cartPage";
   }
}
