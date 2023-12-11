package com.hng.dao.impl;

import com.hng.dao.IOrderDao;
import com.hng.model.Order;
import com.hng.model.User;
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

   @Override
   public List<Order> findOrderByUserId(User user) {
      String sql = "select * from order o join users on o.id=" + user.getId();
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Order or = new Order();
               or.setId(rs.getLong("id"));
               or.setReceivedName(rs.getString("receivedName"));
               or.setAddress(rs.getString("address"));
               or.setPhone(rs.getString("phone"));
               or.setUserId(rs.getLong("userId"));
               or.setTotal(rs.getDouble("total"));
               or.setDescription(rs.getString("description"));
               or.setOrderAt(rs.getDate("orderAt").toLocalDate());
               or.setDeliverAt(rs.getDate("deliverAt").toLocalDate());
               or.setStatus(rs.getString("status"));
               return or;
            });
   }
}
