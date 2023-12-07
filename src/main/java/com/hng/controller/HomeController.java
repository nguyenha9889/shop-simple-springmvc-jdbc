package com.hng.controller;

import com.hng.model.Catalog;
import com.hng.model.Product;
import com.hng.service.ICatalogService;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

      return "index";
   }

   @RequestMapping("/menu")
   public String menu(Model model,
                      @RequestParam(name = "id", defaultValue = "1") long cateId,
                      @RequestParam(name = "page", defaultValue = "0") int page,
                      @RequestParam(name = "size", defaultValue = "5") int size){

      List<Product> listProPaging = productService.getListByCateId(cateId, page, size);
      List<Product> listProTotal = productService.getListByCateIdWithoutPaging(cateId);
      if (page < 0) {
         page = 0;
      }
      if (page > productService.getTotalPage(listProTotal, size)) {
         page = productService.getTotalPage(listProTotal, size) -1;
      }

      List<Catalog> catalogs = catalogService.findAll();
      model.addAttribute("catalogs", catalogs);
      model.addAttribute("listProTotal", listProTotal);
      model.addAttribute("listProPaging", listProPaging);
      model.addAttribute("currentPage", page);
      model.addAttribute("size", size);
      model.addAttribute("numberPage", new int[productService.getTotalPage(listProTotal, size)]);
      return "client/menuPage";
   }
   @RequestMapping("/403")
   public String _403(){
      return "error-403";
   }
}
