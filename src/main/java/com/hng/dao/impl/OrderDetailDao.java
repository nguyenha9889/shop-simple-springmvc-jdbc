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
      if (orderDetail.getCartId() == null) {
         sql = "Insert into order_detail (cartId, productId, productName, unitPrice, quantity) values (?,?,?,?,?) ";
         return jdbcTemplate.update(
               sql,
               orderDetail.getCartId(),
               orderDetail.getProductId(),
               orderDetail.getProductName(),
               orderDetail.getUnitPrice(),
               orderDetail.getQuantity());
      } else {

         sql = "UPDATE order_detail set productName=?, unitPrice=?, quantity=? where cartId=? and productId=?";
         return jdbcTemplate.update(
               sql,
               orderDetail.getProductName(),
               orderDetail.getUnitPrice(),
               orderDetail.getQuantity(),
               orderDetail.getCartId(),
               orderDetail.getProductId());
      }
   }

   @Override
   public int delete(Long orderId) {
      String sql ="delete from order_detail where orderId=" + orderId;
      return jdbcTemplate.update(sql, orderId);
   }

   /**
    * Tìm list orderDetail theo orderId
    * @param orderId Id của user
    * @return List OrderDetail
    */
   @Override
   public List<OrderDetail> findOrderDetailByOrderId(Long userId, Long orderId) {
      String sql = "select * from order_detail join orders o on o.id=" + orderId +
            "join users u on u.id=" + userId;
      return jdbcTemplate.query(sql,
            (rs, row) -> {
               OrderDetail od = new OrderDetail();
               od.setCartId(rs.getLong("orderId"));
               od.setProductId(rs.getLong("productId"));
               od.setProductName(rs.getString("productName"));
               od.setUnitPrice(rs.getDouble("unitPrice"));
               od.setQuantity(rs.getInt("quantity"));
               return od;
            });
   }
}
