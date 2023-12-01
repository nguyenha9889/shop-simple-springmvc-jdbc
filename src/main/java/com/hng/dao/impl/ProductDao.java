package com.hng.dao.impl;

import com.hng.dao.IProductDao;
import com.hng.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
      return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
   }

   @Override
   public Product findById(Long id) {
      String sql = "select * from product where id=?";
      return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
   }

   @Override
   public int save(Product product) {
      return 0;
   }

   @Override
   public int delete(Long id) {
      return 0;
   }

   @Override
   public List<Product> findAll(int limit, int offset) {
      return null;
   }

   @Override
   public List<Product> findAllByName(String name) {
      return null;
   }
}
