package com.hng.controller;


import com.hng.dto.request.FormOrderDetail;
import com.hng.model.Cart;
import com.hng.model.Order;
import com.hng.model.OrderDetail;
import com.hng.model.User;
import com.hng.service.ICartService;
import com.hng.service.IOrderDetailService;
import com.hng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

   @RequestMapping("/cart")
   public String cart(HttpSession session){
      if (session.getAttribute("userLogin") == null) {
         return "redirect:/auth";
      }
      return "client/cartPage";
   }

   @PostMapping("/cart")
   public String add(HttpSession session, Model model,
                     @ModelAttribute("formOrderDetail") FormOrderDetail formOrderDetail) {

      User user = (User) session.getAttribute("userLogin");
      Cart cartNoneOrder = new Cart(user.getId(), 0);
      cartService.save(cartNoneOrder);

      Cart cartOrder = cartService.findCartByUserId(user.getId());
      OrderDetail orderDetail = orderDetailService.create(cartOrder.getId(), formOrderDetail);
      orderDetailService.save(orderDetail);






      return "redirect:/client/cartPage";
   }
}
