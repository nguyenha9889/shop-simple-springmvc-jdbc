package com.hng.dao.impl;

import com.hng.dao.ICartDao;
import com.hng.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
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

         sql = "UPDATE cart set total=? where id=?";
         return jdbcTemplate.update(
               sql,
               cart.getTotal());
      }
   }

   @Override
   public int delete(Long id) {
      String sql = "delete from cart where id=" + id;
      return jdbcTemplate.update(sql, id);
   }
}
