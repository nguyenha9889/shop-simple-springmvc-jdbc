package com.hng.controller;


import com.hng.dto.request.FormOrderDetail;
import com.hng.model.Cart;
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
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class CartController {

   @Autowired
   private IOrderDetailService orderDetailService;

   @Autowired
   private ICartService cartService;

   @Autowired
   private IOrderService orderService;

   @Autowired
   private FormCartValidate cartValidate;

   @RequestMapping("/cart")
   public String cart(HttpSession session, Model model){
      User userLogin = (User) session.getAttribute("userLogin");
      if (userLogin == null) {
         return "redirect:/auth";
      }

      Cart cart = null;
      List<OrderDetail> orderDetails = orderDetailService.findAll();
      if (orderDetails.isEmpty()) {
         cart = new Cart(userLogin.getId(), 0);
         cartService.save(cart);
      } else {
         cart = cartService.findCartByUserId(userLogin.getId());
      }

      model.addAttribute("cartSize", cart.getTotal());
      return "client/cartPage";
   }

   @PostMapping("/cart")
   public String add(HttpSession session,
                     Model model,
                     @ModelAttribute("formOrderDetail") @Validated FormOrderDetail formOrderDetail,
                     BindingResult bindingResult) {

      cartValidate.validate(formOrderDetail, bindingResult);
      if (bindingResult.hasFieldErrors()) {
         return "client/productPage";
      }

      User user = (User) session.getAttribute("userLogin");
      Cart cart = cartService.findCartByUserId(user.getId());

      OrderDetail orderDetail = null;
      if (orderDetailService.findOrderDetailByProductId(formOrderDetail.getProductId()) == null) {
         orderDetail = orderDetailService.create(cart.getId(), formOrderDetail);
      } else {
         orderDetail = orderDetailService.findOrderDetailByProductId(formOrderDetail.getProductId());
         int newQuantity = orderDetail.getQuantity() + formOrderDetail.getQuantity();
         orderDetail.setQuantity(newQuantity);
      }
      orderDetailService.save(orderDetail);

      cart.setTotal(orderDetailService.findAll().size());
      model.addAttribute("cartSize", cart.getTotal());
      return "redirect:/client/cartPage";
   }
}
