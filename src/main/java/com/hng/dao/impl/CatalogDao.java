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
   public List<Catalog> findAll() {
      String sql = "select * from catalog order by id desc";
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

   @Override
   public List<Catalog> findAll(int limit, int offset) {
      String sql = "select * from catalog order by id desc limit "+limit+" offset "+offset+";";
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

   @Override
   public List<Catalog> getListByName(String name) {
      String sql = "select * from catalog where name like %?%";
      return jdbcTemplate.query(
            sql,
            new Object[]{name},
            (rs, row) -> {
               Catalog ca = new Catalog();
               ca.setId(rs.getLong("id"));
               ca.setName(rs.getString("name"));
               ca.setDescription(rs.getString("description"));
               ca.setStatus(rs.getBoolean("status"));
               return ca;
            });
   }

   @Override
   public Catalog findByName(String name) {
      String sql = "select * from catalog where name=?";
      return jdbcTemplate.query(
            sql,
            new Object[]{name},
            (rs) -> {
               Catalog ca = null;
               if (rs.next()) {
                  ca = new Catalog();
                  ca.setId(rs.getLong("id"));
                  ca.setName(rs.getString("name"));
                  ca.setDescription(rs.getString("description"));
                  ca.setStatus(rs.getBoolean("status"));
               }
               return ca;
            }
      );
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
