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

      OrderDetail orderDetail = orderDetailService.create(formOrderDetail);
      orderDetailService.save(orderDetail);

      User user = (User) session.getAttribute("userLogin");
      List<OrderDetail> orderDetails = orderDetailService.findAll();
      Cart cart = cartService.create(user.getId(), orderDetails);
      cartService.save(cart);

      Order order = orderService.create(user, null);
      orderService.save(order);

      model.addAttribute("sizeCart", orderDetails.size());
      return "redirect:/client/cartPage";
   }
}
