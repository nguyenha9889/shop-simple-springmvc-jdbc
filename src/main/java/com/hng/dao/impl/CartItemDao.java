package com.hng.dao.impl;

import com.hng.dao.ICartItemDao;
import com.hng.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Repository
public class CartItemDao implements ICartItemDao {
   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Override
   public List<CartItem> findAll(){
      String sql = "select * from cart_item ci join cart c on ci.cartId= c.id";

      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               CartItem ci = new CartItem();
               ci.setCartId(rs.getLong("cartId"));
               ci.setProductId(rs.getLong("productId"));
               ci.setQuantity(rs.getInt("quantity"));
               ci.setUnitPrice(rs.getDouble("unitPrice"));
               ci.setCreatedAt(rs.getDate("createdAt").toLocalDate());
               if (rs.getDate("updatedAt") != null) {
                  ci.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
               }
               if (rs.getDate("deletedAt") != null) {
                  ci.setDeletedAt(rs.getDate("deletedAt").toLocalDate());
               }
               return ci;
            });
   }

   @Override
   public int save(CartItem ci) {
      String sql = null;
      if (ci.getCreatedAt() == null) {
         sql = "insert into cart_item(cartId,productId,quantity,unitPrice) values(?,?,?,?)";
         return jdbcTemplate.update(
               sql,
               ci.getCartId(),
               ci.getProductId(),
               ci.getQuantity(),
               ci.getUnitPrice());
      } else {
         sql = "update cart_item set quantity=?,updatedAt=? where cartId=? and productId=?";
         return jdbcTemplate.update(
               sql,
               ci.getQuantity(),
               Date.valueOf(LocalDate.now()),
               ci.getCartId(),
               ci.getProductId());
      }
   }

   @Override
   public int delete(CartItem ci) {
      String sql = "delete from cart_item where cartId=? and productId=?";
      return jdbcTemplate.update(sql, ci.getCartId(), ci.getProductId());
   }

   @Override
   public CartItem findByCartAndProduct(Long cartId, Long productId) {
      String sql = "select * from cart_item ci join cart c on ci.cartId= ?" +
            " join product p on ci.productId = ?";

      List<CartItem> cartItems = jdbcTemplate.query(
            sql,
            new Object[]{cartId, productId},
            (rs, row) -> {
               CartItem ci = new CartItem();
               ci.setCartId(rs.getLong("cartId"));
               ci.setProductId(rs.getLong("productId"));
               ci.setQuantity(rs.getInt("quantity"));
               ci.setUnitPrice(rs.getDouble("unitPrice"));
               ci.setCreatedAt(rs.getDate("createdAt").toLocalDate());
               if (rs.getDate("updatedAt") != null) {
                  ci.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
               }
               if (rs.getDate("deletedAt") != null) {
                  ci.setDeletedAt(rs.getDate("deletedAt").toLocalDate());
               }
               return ci;
            });
      if (cartItems.isEmpty()) {
         return null;
      }
      return cartItems.get(0);
   }

   @Override
   public List<CartItem> findByCart(Long cartId) {
      String sql = "select * from cart_item ci join cart c on ci.cartId=?";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               CartItem ci = new CartItem();
               ci.setCartId(rs.getLong("cartId"));
               ci.setProductId(rs.getLong("productId"));
               ci.setQuantity(rs.getInt("quantity"));
               ci.setUnitPrice(rs.getDouble("unitPrice"));
               ci.setCreatedAt(rs.getDate("createdAt").toLocalDate());
               if (rs.getDate("updatedAt") != null) {
                  ci.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
               }
               if (rs.getDate("deletedAt") != null) {
                  ci.setDeletedAt(rs.getDate("deletedAt").toLocalDate());
               }
               return ci;
            });
   }
}
