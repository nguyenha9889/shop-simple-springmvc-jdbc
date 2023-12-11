package com.hng.service.impl;

import com.hng.dao.IOrderDao;
import com.hng.dto.request.FormOrder;
import com.hng.model.Order;
import com.hng.model.User;
import com.hng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OrderService implements IOrderService {
   @Autowired
   private IOrderDao orderDao;
   @Override
   public List<Order> findAll() {
      return null;
   }

   @Override
   public Order findById(Long id) {
      return null;
   }

   @Override
   public int save(Order order) {
      return orderDao.save(order);
   }

   @Override
   public int delete(Long id) {
      return 0;
   }

   @Override
   public Order create(User user, FormOrder formOrder, Double total) {
      Order order = new Order();
      if (formOrder != null) {
         order.setReceivedName(formOrder.getReceivedName());
         order.setAddress(formOrder.getAddress());
         order.setPhone(formOrder.getPhone());
      } else {
         order.setReceivedName(user.getFullName());
         order.setPhone(user.getPhone());
      }
      if (total != null) {
         order.setTotal(total);
      }

      order.setUserId(user.getId());
      return order;
   }

}
