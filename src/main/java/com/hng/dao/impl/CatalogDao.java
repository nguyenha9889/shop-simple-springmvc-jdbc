package com.hng.dao.impl;

import com.hng.dao.ICatalogDao;
import com.hng.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatalogDao implements ICatalogDao {

   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public List<Catalog> findAll(int limit, int offset) {
      String sql = "select * from catalog limit " + limit+ " offset " + offset + ";";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Catalog ca = new Catalog();
               ca.setId(rs.getLong(1));
               ca.setName(rs.getString(2));
               ca.setDescription(rs.getString(3));
               ca.setStatus(rs.getBoolean(4));
               return ca;
            });
   }
   }

   @Override
   public Catalog findByName(String name) {
      String sql = "select * from catalog where name=?";
      return jdbcTemplate.queryForObject(sql, new Object[]{name}, new BeanPropertyRowMapper<>(Catalog.class));
   }

   @Override
   public List<Catalog> findAll() {
      String sql = "select * from catalog";
      return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Catalog.class));
   }

   @Override
   public Catalog findById(Long id) {
      String sql = "select * from catalog where id=?";
      return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Catalog.class));
   }

   @Override
   public int save(Catalog catalog) {
      String sql = null;
      if (catalog.getId() == null) {
         sql = "Insert into catalog (name, description) values (?,?)";
         return jdbcTemplate.update(
               sql,
               catalog.getName(),
               catalog.getDescription());
      } else {
         sql = "UPDATE catalog set name=?, description=?, status=? where id=?";
         return jdbcTemplate.update(
               sql,
               catalog.getName(),
               catalog.getDescription(),
               catalog.isStatus(),
               catalog.getId());
      }
   }

   @Override
   public int delete(Long id) {
      String sql = "Delete from catalog where id = ?";
      return jdbcTemplate.update(sql, id);
   }
}
