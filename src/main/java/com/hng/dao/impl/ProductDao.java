package com.hng.dao.impl;

import com.hng.dao.IProductDao;
import com.hng.dto.request.ProductFilter;
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
      String sql = "select * from product order by createdAt desc";
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
      String sql = "select * from product order by updatedAt desc limit "+limit+" offset "+offset+";";
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

   /**
    * Query tìm kiếm tương đối sản phẩm theo tên cho Admin dashboard có phân trang
    * @param name Tên sản phẩm cần tìm
    * @return List sản phẩm
    */
   @Override
   public List<Product> getListByName(String name, int limit, int offset) {
      String nameQuery = "'%" +name.toLowerCase().trim() + "%'";
      String sql = "select * from product where lower(name) like "+nameQuery+" limit "+limit+" offset "+offset+";";
      jdbcTemplate.queryForList(sql, name);
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
   public List<Product> getListByCateIdPaging(ProductFilter filter, int limit, int offset) {

      StringBuilder builder = new StringBuilder("select p.* from product p join catalog c on p.categoryId = c.id where c.id = ");
      builder.append(filter.getCategoryId());
      if (filter.getProductName() != null){
         builder.append(" and lower(p.name) like '%").append(filter.getProductName().toLowerCase()).append("%'");
      }

      builder.append(" order by p.unitPrice ");
      builder.append(filter.getSortByPrice());
      builder.append(" limit ").append(limit).append(" offset ").append(offset);
      String sql = builder.toString();

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
   public List<Product> getListByCateId(ProductFilter filter) {
      StringBuilder builder = new StringBuilder("select p.* from product p join catalog c on p.categoryId = c.id where c.id = ");
      builder.append(filter.getCategoryId());

      if (filter.getProductName() != null){
         builder.append(" and lower(p.name) like '%").append(filter.getProductName().toLowerCase()).append("%'");
      }

      builder.append(" order by p.unitPrice ");
      builder.append(filter.getSortByPrice());
      String sql = builder.toString();

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
