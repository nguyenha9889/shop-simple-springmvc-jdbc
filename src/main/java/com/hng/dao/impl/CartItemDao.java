package com.hng.dao.impl;

import com.hng.dao.ICartItemDao;
import com.hng.model.CartItem;
import com.hng.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemDao implements ICartItemDao {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public List<CartItem> findAll() {
      String sql = "select * from cart_item";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               CartItem cI = new CartItem();
               cI.setId(rs.getLong("id"));
               cI.setCartId(rs.getLong("cartId"));
               cI.setProductId(rs.getLong("productId"));
               cI.setQuantity(rs.getInt("quantity"));
               cI.setUnitPrice(rs.getDouble("price"));
               cI.setCreatedAt(rs.getDate("createdAt").toLocalDate());
               cI.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
               return cI;
            });
   }

   @Override
   public CartItem findById(Long id) {
      return null;
   }

   @Override
   public int save(CartItem cartItem) {
      return 0;
   }

   @Override
   public int delete(Long id) {
      return 0;
   }
}
