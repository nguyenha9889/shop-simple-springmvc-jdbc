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
      List<Product> offer = productService.getListFeatured().subList(0,2);
      model.addAttribute("offer", offer);

      // Chỉ lấy category có sản phẩm
      List<Catalog> menu = catalogService.getListHaveProduct().subList(0,6);
      model.addAttribute("menu", menu);
      return "index";
   }
}
