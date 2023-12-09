package com.hng.dao.impl;

import com.hng.dao.IOrderDao;
import com.hng.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderDao implements IOrderDao {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public List<Order> findAll() {
      return null;
   }

   @Override
   public Order findById(Long id) {
      String sql = "select * from orders where id=?";
      return jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            new BeanPropertyRowMapper<>(Order.class)
      );
   }

   @Override
   public int save(Order order) {
      String sql = null;
      if (order.getId() == null) {
         sql = "Insert into orders (receivedName, address, phone, userId, total, status) values (?,?,?,?,?,?)";
         return jdbcTemplate.update(
               sql,
               order.getReceivedName(),
               order.getAddress(),
               order.getPhone(),
               order.getUserId(),
               order.getTotal(),
               "WAITING"
               );
      } else {
         // Edit - Ko cho phép sửa status
         sql = "UPDATE orders set receivedName=?, address=?, phone=?, total=?, status=?, orderAt=? where id=?";
         return jdbcTemplate.update(
               sql,
               order.getReceivedName(),
               order.getAddress(),
               order.getPhone(),
               order.getTotal(),
               order.getStatus(),
               LocalDate.now(),
               order.getId());
      }
   }

   @Override
   public int delete(Long id) {
      String sql = "UPDATE orders set status='CANCEL' where id=?";
      return jdbcTemplate.update(sql, id);
   }
}
