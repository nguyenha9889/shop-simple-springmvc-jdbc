package com.hng.dao.impl;

import com.hng.dao.IOrderDetailDao;
import com.hng.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderDetailDao implements IOrderDetailDao {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public List<OrderDetail> findAll() {
      String sql = "select * from order_detail";
      return jdbcTemplate.query(sql,
            (rs, row) -> {
               OrderDetail od = new OrderDetail();
               od.setId(rs.getLong("id"));
               od.setCartId(rs.getLong("cartId"));
               od.setProductId(rs.getLong("productId"));
               od.setProductName(rs.getString("productName"));
               od.setUnitPrice(rs.getDouble("unitPrice"));
               od.setQuantity(rs.getInt("quantity"));
               return od;
            });
   }

   @Override
   public OrderDetail findById(Long id) {
      return null;
   }

   @Override
   public int save(OrderDetail orderDetail) {
      String sql = null;
      if (orderDetail.getId() == null) {
         sql = "Insert into order_detail (cartId, productId, productName, unitPrice, quantity) values (?,?,?,?,?) ";
         return jdbcTemplate.update(
               sql,
               orderDetail.getCartId(),
               orderDetail.getProductId(),
               orderDetail.getProductName(),
               orderDetail.getUnitPrice(),
               orderDetail.getQuantity());
      } else {
         sql = "UPDATE order_detail set productName=?, unitPrice=?, quantity=? where productId=?";
         return jdbcTemplate.update(
               sql,
               orderDetail.getProductName(),
               orderDetail.getUnitPrice(),
               orderDetail.getQuantity(),
               orderDetail.getProductId());
      }
   }

   @Override
   public int delete(Long id) {
      String sql ="delete from order_detail where id=" + id;
      return jdbcTemplate.update(sql, id);
   }

   /**
    * Tìm list orderDetail theo productId
    * @param productId productId object
    * @return List OrderDetail
    */
   @Override
   public OrderDetail findOrderDetailByProductId(Long productId) {
      String sql = "select * from order_detail where productId=?";
      return jdbcTemplate.queryForObject(
            sql,
            new Object[]{productId},
            new BeanPropertyRowMapper<>(OrderDetail.class));
   }
}
