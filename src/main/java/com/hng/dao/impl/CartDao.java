package com.hng.dao.impl;

import com.hng.dao.ICartDao;
import com.hng.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDao implements ICartDao {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public List<Cart> findAll() {
      return null;
   }

   @Override
   public Cart findById(Long id) {
      return null;
   }

   @Override
   public int save(Cart cart) {
      String sql = null;
      if (cart.getId() == null) {
         sql = "Insert into cart (userId, total) values (?,?)";
         return jdbcTemplate.update(
               sql,
               cart.getUserId(),
               cart.getTotal()
         );
      } else {
         sql = "UPDATE cart set total=? where userId=?";
         return jdbcTemplate.update(
               sql,
               cart.getTotal());
      }
   }

   @Override
   public int delete(Long userId) {
      String sql = "delete from cart where userId=" + userId;
      return jdbcTemplate.update(sql, userId);
   }

   @Override
   public Cart findCartByUserId(Long userId) {
      String sql = "select * from cart c where c.userId=" + userId;

      return jdbcTemplate.queryForObject(
            sql,
            new BeanPropertyRowMapper<>(Cart.class)
      );
   }
}
