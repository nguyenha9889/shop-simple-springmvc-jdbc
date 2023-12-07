package com.hng.controller;

import com.hng.model.Catalog;
import com.hng.model.Product;
import com.hng.service.ICatalogService;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping({"/",""})
public class HomeController {

   @Autowired
   private ICatalogService catalogService;
   @Autowired
   private IProductService productService;
   @RequestMapping
   public String home(Model model){
      List<Catalog> catalogs = catalogService.findAll();
      List<Product> products = productService.findAll();
      model.addAttribute("catalogs", catalogs);
      model.addAttribute("products", products);
      return "index";
   }

   @RequestMapping("/menu")
   public String menu(Model model){
      List<Catalog> catalogs = catalogService.findAll();
      List<Product> products = productService.findAll();
      model.addAttribute("catalogs", catalogs);
      model.addAttribute("products", products);
      return "client/menuPage";
   }
   @RequestMapping("/403")
   public String _403(){
      return "error-403";
   }
}
