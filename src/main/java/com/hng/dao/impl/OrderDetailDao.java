package com.hng.dao.impl;

import com.hng.dao.IOrderDetailDao;
import com.hng.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderDetailDao implements IOrderDetailDao {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public List<OrderDetail> findAll() {
      return null;
   }

   @Override
   public OrderDetail findById(Long id) {
      return null;
   }

   @Override
   public int save(OrderDetail orderDetail) {
      String sql = null;
      if (orderDetail.getId() == null) {
         sql = "Insert into order_detail (productId, productName, unitPrice, quantity) values (?,?,?,?)";
         return jdbcTemplate.update(
               sql,
               orderDetail.getProductId(),
               orderDetail.getProductName(),
               orderDetail.getUnitPrice(),
               orderDetail.getQuantity());
      } else {

         sql = "UPDATE order_detail set orderId=?, productId=?, unitPrice=?, quantity=? where id=?";
         return jdbcTemplate.update(
               sql,
               orderDetail.getOrderId(),
               orderDetail.getProductId(),
               orderDetail.getProductName(),
               orderDetail.getUnitPrice(),
               orderDetail.getQuantity(),
               orderDetail.getId());
      }
   }

   @Override
   public int delete(Long id) {
      String sql ="delete from order_detail where id=" + id;
      return jdbcTemplate.update(sql, id);
   }

   /**
    * Tìm list orderDetail theo userId
    * @param userId Id của user
    * @return List OrderDetail
    */
   @Override
   public List<OrderDetail> findOrderDetailByUserId(Long userId) {
      String sql = "select * from order_detail ";
      return null;
   }
}
