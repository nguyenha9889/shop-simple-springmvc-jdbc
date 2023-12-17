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
   public int save(Cart cart) {
      String sql = null;
      if (cart.getId() == null){
         sql = "Insert into cart (userId) values (?)";
         return jdbcTemplate.update(
               sql,
               cart.getUserId()
         );
      } else if (cart.getTotal() == 0) {
         sql = "UPDATE cart set quantity=? where userId=?";
         return jdbcTemplate.update(
               sql,
               cart.getQuantity(),
               cart.getUserId()
         );
      } else {
         sql = "UPDATE cart set quantity=?, total=? where userId=?";
         return jdbcTemplate.update(
               sql,
               cart.getQuantity(),
               cart.getTotal(),
               cart.getUserId()
         );
      }

   }

   @Override
   public int delete(Long userId) {
      String sql = "delete from cart where userId=?";
      return jdbcTemplate.update(sql, userId);
   }

   @Override
   public Cart findCartByUserId(Long userId) {
      String sql = "select * from cart c where c.userId=?";
      List<Cart> carts = jdbcTemplate.query(
            sql,
            new Object[]{userId},
            (rs, row) -> {
               Cart cart = new Cart();
               cart.setId(rs.getLong("id"));
               cart.setUserId(rs.getLong("userId"));
               cart.setQuantity(rs.getInt("quantity"));
               cart.setTotal(rs.getDouble("total"));
               return cart;
            });
      return carts.get(0);
   }
}
