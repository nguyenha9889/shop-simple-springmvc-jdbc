package com.hng.controller;


import com.hng.dto.request.FormOrderDetail;
import com.hng.model.Cart;
import com.hng.model.CartItem;
import com.hng.model.OrderDetail;
import com.hng.model.User;
import com.hng.service.ICartService;
import com.hng.service.IOrderDetailService;
import com.hng.service.IOrderService;
import com.hng.validate.FormCartValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/cart")
public class CartController {

   @Autowired
   private IOrderDetailService orderDetailService;

   @Autowired
   private ICartService cartService;

   @Autowired
   private IOrderService orderService;

   @Autowired
   private FormCartValidate cartValidate;

   @RequestMapping
   public String cart(HttpSession session,
                      Model model,
                      @RequestParam(name = "id") Long id){
      User userLogin = (User) session.getAttribute("userLogin");
      if (userLogin == null) {
         model.addAttribute("loginNull", "Please sign in before add to cart");
         return "client/product";
      }

      Cart cart = cartService.findCartByUserId(userLogin.getId());

      model.addAttribute("cart", cart);
      return "client/book";
   }

   @PostMapping
   public String add(HttpSession session,
                     Model model,
                     @ModelAttribute("formOrderDetail") @Validated FormOrderDetail form,
                     BindingResult bindingResult) {

      cartValidate.validate(form, bindingResult);
      if (bindingResult.hasFieldErrors()) {
         return "client/product";
      }

      User userLogin = (User) session.getAttribute("userLogin");
      if (userLogin == null) {
         model.addAttribute("user_login", "Please sign in before add product");
         return "client/product";
      }

      Cart cart = cartService.findCartByUserId(userLogin.getId());

      CartItem cartItem = new CartItem();

      model.addAttribute("cart", cart);
      return "client/product";
   }
}
