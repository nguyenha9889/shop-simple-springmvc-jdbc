package com.hng.service.impl;

import com.hng.dao.IOrderDetailDao;
import com.hng.dto.request.FormDetail;
import com.hng.model.OrderDetail;
import com.hng.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OrderDetailService implements IOrderDetailService {
   @Autowired
   private IOrderDetailDao orderDetailDao;
   @Override
   public List<OrderDetail> findAll() {
      return orderDetailDao.findAll();
   }

   @Override
   public void save(OrderDetail orderDetail) {
      orderDetailDao.save(orderDetail);
   }

   /**
    * Convert Form OrderDetail sang OrderDetail object
    */
   @Override
   public OrderDetail create(Long cartId , FormDetail form) {
      OrderDetail orderDetail = new OrderDetail();
      orderDetail.setCartId(cartId);
      orderDetail.setProductId(form.getProductId());
      orderDetail.setQuantity(form.getQuantity());
      return orderDetail;
   }

   @Override
   public OrderDetail findOrderDetailByProductId(Long productId) {
      return orderDetailDao.findOrderDetailByProductId(productId);
   }
}
