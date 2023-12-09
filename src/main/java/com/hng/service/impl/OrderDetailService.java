package com.hng.service.impl;

import com.hng.dao.IOrderDetailDao;
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
      return null;
   }

   @Override
   public OrderDetail findById(Long id) {
      return null;
   }

   @Override
   public void save(OrderDetail orderDetail) {
      orderDetailDao.save(orderDetail);
   }

   @Override
   public int delete(Long id) {
      return 0;
   }
}
