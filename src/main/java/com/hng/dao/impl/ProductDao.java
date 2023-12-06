package com.hng.dao.impl;

import com.hng.dao.IProductDao;
import com.hng.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class ProductDao implements IProductDao {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Override
   public List<Product> findAll() {
      String sql = "select * from product order by createdAt";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Product p = new Product();
               p.setId(rs.getLong("id"));
               p.setName(rs.getString("name"));
               p.setCategoryId(rs.getLong("categoryId"));
               p.setDescription(rs.getString("description"));
               p.setImagePath(rs.getString("imagePath"));
               p.setUnitPrice(rs.getDouble("unitPrice"));
               p.setStatus(rs.getBoolean("status"));
               p.setCreatedAt(rs.getDate("createdAt").toLocalDate());
               p.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
               return p;
            });
   }

   @Override
   public List<Product> findAll(int limit, int offset) {
      String sql = "select * from product order by createdAt desc limit "+limit+" offset "+offset+";";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Product p = new Product();
               p.setId(rs.getLong("id"));
               p.setName(rs.getString("name"));
               p.setCategoryId(rs.getLong("categoryId"));
               p.setDescription(rs.getString("description"));
               p.setImagePath(rs.getString("imagePath"));
               p.setUnitPrice(rs.getDouble("unitPrice"));
               p.setStatus(rs.getBoolean("status"));
               p.setCreatedAt(rs.getDate("createdAt").toLocalDate());
               p.setCreatedAt(rs.getDate("updatedAt").toLocalDate());
               return p;
            });
   }

   @Override
   public List<Product> getListByName(String name, int limit, int offset) {
      String nameQuery = "'%" +name.toLowerCase().trim() + "%'";
      String sql = "select * from product where lower(name) like "+nameQuery+" limit "+limit+" offset "+offset+";";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Product p = new Product();
               p.setId(rs.getLong("id"));
               p.setName(rs.getString("name"));
               p.setCategoryId(rs.getLong("categoryId"));
               p.setDescription(rs.getString("description"));
               p.setImagePath(rs.getString("imagePath"));
               p.setUnitPrice(rs.getDouble("unitPrice"));
               p.setStatus(rs.getBoolean("status"));
               p.setCreatedAt(rs.getDate("createdAt").toLocalDate());
               p.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
               return p;
            });
   }

   @Override
   public Product findById(Long id) {
      String sql = "select * from product where id=?";
      return jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            new BeanPropertyRowMapper<>(Product.class)
      );
   }

   @Override
   public int save(Product product) {
      String sql = null;
      if (product.getId() == null) {
         sql = "Insert into product (name, categoryId, description, imagePath, unitPrice) values (?,?,?,?,?)";
         return jdbcTemplate.update(
               sql,
               product.getName(),
               product.getCategoryId(),
               product.getDescription(),
               product.getImagePath(),
               product.getUnitPrice());
      } else {
         // Edit - Ko cho phép sửa status
         sql = "UPDATE product set name=?, categoryId=?, description=?, imagePath=?, unitPrice=?, updatedAt=? where id=?";
         return jdbcTemplate.update(
               sql,
               product.getName(),
               product.getCategoryId(),
               product.getDescription(),
               product.getImagePath(),
               product.getUnitPrice(),
               LocalDate.now(),
               product.getId());
      }
   }

   // Ẩn product
   @Override
   public int delete(Long id) {
      String sql = "UPDATE product set status=0 where id=?";
      return jdbcTemplate.update(sql, id);
   }
}
