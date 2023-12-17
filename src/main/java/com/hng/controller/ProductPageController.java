package com.hng.controller;

import com.hng.dto.request.FormDetail;
import com.hng.model.Cart;
import com.hng.model.Product;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductPageController {
   @Autowired
   private IProductService productService;
   @RequestMapping
   public String product(Model model,
                         HttpSession session,
                         @RequestParam(name = "id") long id) {

      Cart cart = (Cart) session.getAttribute("cart");
      model.addAttribute("cart", cart);

      // Sản phẩm nổi bật
      List<Product> featuredList = productService.getListFeatured();
      if (featuredList.size() >=3) {
         List<Product> subList = featuredList.subList(0, 3);
         model.addAttribute("featured", subList);
      } else {
         model.addAttribute("featured", featuredList);
      }

      Product product = productService.findById(id);
      model.addAttribute("product", product);

      FormDetail form= new FormDetail();
      form.setProductId(product.getId());
      model.addAttribute("form", form);
      return "client/product";
   }
}
