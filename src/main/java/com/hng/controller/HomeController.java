package com.hng.controller;

import com.hng.model.Cart;
import com.hng.model.Catalog;
import com.hng.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping({"/",""})
public class HomeController {

   @Autowired
   private ICatalogService catalogService;
   @RequestMapping
   public String home(Model model, HttpSession session){

      Cart cart = (Cart) session.getAttribute("cart");
      model.addAttribute("cart", cart);

      // Chỉ lấy category có sản phẩm
      List<Catalog> menu = catalogService.getListHaveProduct().subList(0,6);
      model.addAttribute("menu", menu);
      return "index";
   }
}
