package com.hng.controller;

import com.hng.dto.request.FormOrderDetail;
import com.hng.model.Product;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductPageController {
   @Autowired
   private IProductService productService;
   @RequestMapping
   public String product(Model model,
                         @RequestParam(name = "id") long id) {

      Product product = productService.findById(id);
      FormOrderDetail formOrderDetail = new FormOrderDetail(
            product.getId(),
            product.getName(),
            product.getUnitPrice(),
            product.getImagePath(),
            product.getDescription()
      );
      model.addAttribute("formOrderDetail", formOrderDetail);
      return "client/product";
   }
}
