package com.hng.controller;


import com.hng.dto.request.FormDetail;
import com.hng.model.Cart;
import com.hng.model.CartItem;
import com.hng.model.Product;
import com.hng.model.User;
import com.hng.service.ICartItemService;
import com.hng.service.ICartService;
import com.hng.service.IProductService;
import com.hng.validate.FormCartValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {
   @Autowired
   private IProductService productService;
   @Autowired
   private ICartItemService cartItemService;

   @Autowired
   private ICartService cartService;

   @Autowired
   private FormCartValidate cartValidate;

   @RequestMapping
   public String cart(HttpSession session, Model model){
      User userLogin = (User) session.getAttribute("userLogin");
      if (userLogin == null) {
         return "login";
      }

      List<CartItem> cartItems = cartItemService.findAll();
      model.addAttribute("cartItems", cartItems);



      Cart cart = (Cart) session.getAttribute("cart");
      double total = cartService.getTotal(cartItems);
      cart.setTotal(total);
      cartService.save(cart);
      model.addAttribute("cart", cart);
      return "client/cart";
   }

   @PostMapping
   public ModelAndView cartItem(HttpSession session,
                                ModelMap model,
                                @ModelAttribute("form") @Validated FormDetail form,
                                BindingResult bindingResult) {

      User userLogin = (User) session.getAttribute("userLogin");
      if (userLogin == null) {
         return new ModelAndView("redirect:/auth");
      }

      Product product = productService.findById(form.getProductId());
      Cart cart = (Cart) session.getAttribute("cart");

      cartValidate.validate(form, bindingResult);

      if (!bindingResult.hasFieldErrors()) {
         CartItem cartItem = cartItemService.create(cart.getId(), product, form);
         cartItemService.save(cartItem);

         // update so luong san pham them vao cart
         cart.setQuantity(cartItemService.findAll().size());
         cartService.save(cart);
      }

      model.addAttribute("cart", cart);
      // product info
      model.addAttribute("product", product);
      // Featured product
      showFeature(model);
      model.addAttribute("form", form);
      model.addAttribute("cart", cart);
      return new ModelAndView("client/product", model);
   }

   private void showFeature(ModelMap model){
      // Sản phẩm nổi bật
      List<Product> featuredList = productService.getListFeatured();
      if (featuredList.size() >=3) {
         List<Product> subList = featuredList.subList(0, 3);
         model.addAttribute("featured", subList);
      } else {
         model.addAttribute("featured", featuredList);
      }
   }
}
