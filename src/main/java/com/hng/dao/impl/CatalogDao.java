package com.hng.dao.impl;

import com.hng.dao.ICatalogDao;
import com.hng.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CatalogDao implements ICatalogDao {

   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public List<Catalog> findAll(int limit, int offset) {
      String sql = "select * from catalog limit=? offset=?";
      return jdbcTemplate.query(
            sql,
            new Object[]{limit, offset},
            (rs, row) -> {
               Catalog ca = new Catalog();
               ca.setId(rs.getLong(1));
               ca.setName(rs.getString(2));
               ca.setDescription(rs.getString(3));
               ca.setStatus(rs.getBoolean(4));
               return ca;
            });
   }

   @Override
   public List<Catalog> getListByName(String name) {
      String sql = "select * from catalog where name like :name";
      Map<String, Object> params = new HashMap<>();
      params.put("name", name+"%");
      MapSqlParameterSource param = new MapSqlParameterSource(params);

      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Catalog ca = new Catalog();
               ca.setId(rs.getLong(1));
               ca.setName(rs.getString(2));
               ca.setDescription(rs.getString(3));
               ca.setStatus(rs.getBoolean(4));
               return ca;
            },
            param
            );
   }

   @Override
   public Catalog findByName(String name) {
      String sql = "select * from catalog where LOWER(name)=?";
      return jdbcTemplate.queryForObject(sql, new Object[]{name.toLowerCase()}, new BeanPropertyRowMapper<>(Catalog.class));
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
         // Edit - Ko cho phép sửa status
         sql = "UPDATE catalog set name=?, description=? where id=?";
         return jdbcTemplate.update(
               sql,
               catalog.getName(),
               catalog.getDescription(),
               catalog.getId());
      }
   }

   // Ẩn catalog
   @Override
   public int delete(Long id) {
      String sql = "UPDATE catalog set status=0 where id=?";
      return jdbcTemplate.update(sql, id);
   }
}
