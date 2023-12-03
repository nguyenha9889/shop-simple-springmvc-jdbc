package com.hng.dao.impl;

import com.hng.dao.IProductDao;
import com.hng.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class ProductDao implements IProductDao {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Override
   public List<Product> findAll() {
      String sql = "select * from product";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Product p = new Product();
               p.setId(rs.getLong(1));
               p.setName(rs.getString(2));
               p.setCategoryId(rs.getLong(3));
               p.setDescription(rs.getString(4));
               p.setImagePath(rs.getString(5));
               p.setUnitPrice(rs.getDouble(6));
               p.setStock(rs.getInt(7));
               p.setStatus(rs.getBoolean(8));
               p.setCreatedAt(rs.getDate(9).toLocalDate());
               p.setUpdatedAt(rs.getDate(10).toLocalDate());
               return p;
            });
   }

   @Override
   public List<Product> findAll(int limit, int offset) {
      String sql = "select * from product limit=? offset=?";
      return jdbcTemplate.query(
            sql,
            new Object[]{limit, offset},
            (rs, row) -> {
               Product p = new Product();
               p.setId(rs.getLong(1));
               p.setName(rs.getString(2));
               p.setCategoryId(rs.getLong(3));
               p.setDescription(rs.getString(4));
               p.setImagePath(rs.getString(5));
               p.setUnitPrice(rs.getDouble(6));
               p.setStock(rs.getInt(7));
               p.setStatus(rs.getBoolean(8));
               p.setCreatedAt(rs.getDate(9).toLocalDate());
               p.setUpdatedAt(rs.getDate(10).toLocalDate());
               return p;
            });
   }

   @Override
   public List<Product> getListByName(String name) {
      String sql = "select * from where name like %?%";
      return jdbcTemplate.query(
            sql,
            new Object[]{name},
            (rs, row) -> {
               Product p = new Product();
               p.setId(rs.getLong(1));
               p.setName(rs.getString(2));
               p.setCategoryId(rs.getLong(3));
               p.setDescription(rs.getString(4));
               p.setImagePath(rs.getString(5));
               p.setUnitPrice(rs.getDouble(6));
               p.setStock(rs.getInt(7));
               p.setStatus(rs.getBoolean(8));
               p.setCreatedAt(rs.getDate(9).toLocalDate());
               p.setUpdatedAt(rs.getDate(10).toLocalDate());
               return p;
            });
   }

   @Override
   public Product findById(Long id) {
      String sql = "select * from product where id=?";
      return jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            (rs, row) -> {
               Product p = null;
               if (rs.next()) {
                  p = new Product();
                  p.setId(rs.getLong(1));
                  p.setName(rs.getString(2));
                  p.setCategoryId(rs.getLong(3));
                  p.setDescription(rs.getString(4));
                  p.setImagePath(rs.getString(5));
                  p.setUnitPrice(rs.getDouble(6));
                  p.setStock(rs.getInt(7));
                  p.setStatus(rs.getBoolean(8));
                  p.setCreatedAt(rs.getDate(9).toLocalDate());
                  p.setUpdatedAt(rs.getDate(10).toLocalDate());
               }
               return p;
            }
      );
   }

   @Override
   public int save(Product product) {
      String sql = null;
      if (product.getId() == null) {
         sql = "Insert into product (name, categoryId, description, imagePath, unitPrice, stock) values (?,?,?,?,?,?)";
         return jdbcTemplate.update(
               sql,
               product.getName(),
               product.getCategoryId(),
               product.getDescription(),
               product.getImagePath(),
               product.getUnitPrice(),
               product.getStock());
      } else {
         // Edit - Ko cho phép sửa status
         sql = "UPDATE product set name=?, categoryId=?, description=?, imagePath=?, unitPrice=?, stock=?, updatedAt=? where id=?";
         return jdbcTemplate.update(
               sql,
               product.getName(),
               product.getCategoryId(),
               product.getDescription(),
               product.getImagePath(),
               product.getUnitPrice(),
               product.getStock(),
               product.getUpdatedAt(),
               product.getId());
      }
   }

   // Ẩn product
   @Override
   public int delete(Long id) {
      String sql = "UPDATE product set status=0 where id=?";
      return jdbcTemplate.update(sql, id);
   }


   @Override
   public Product findByName(String name) {
      String sql = "select * from product where id=?";
      return jdbcTemplate.queryForObject(
            sql,
            new Object[]{name},
            (rs, row) -> {
               Product p = null;
               if (rs.next()) {
                  p = new Product();
                  p.setId(rs.getLong(1));
                  p.setName(rs.getString(2));
                  p.setCategoryId(rs.getLong(3));
                  p.setDescription(rs.getString(4));
                  p.setImagePath(rs.getString(5));
                  p.setUnitPrice(rs.getDouble(6));
                  p.setStock(rs.getInt(7));
                  p.setStatus(rs.getBoolean(8));
                  p.setCreatedAt(rs.getDate(9).toLocalDate());
                  p.setUpdatedAt(rs.getDate(10).toLocalDate());
               }
               return p;
            }
      );
   }
}
