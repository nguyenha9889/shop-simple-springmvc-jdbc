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
               ca.setId(rs.getLong("id"));
               ca.setName(rs.getString("name"));
               ca.setDescription(rs.getString("description"));
               ca.setImagePath(rs.getString("imagePath"));
               ca.setStatus(rs.getBoolean("status"));
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
               ca.setId(rs.getLong("id"));
               ca.setName(rs.getString("name"));
               ca.setDescription(rs.getString("description"));
               ca.setImagePath(rs.getString("imagePath"));
               ca.setStatus(rs.getBoolean("status"));
               return ca;
            });
   }

   @Override
   public List<Catalog> getListByName(String name, int limit, int offset) {
      String nameQuery = "'%" +name.toLowerCase().trim() + "%'";
      String sql = "select * from catalog where lower(name) like "+nameQuery+" limit "+limit+" offset "+offset+";";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Catalog ca = new Catalog();
               ca.setId(rs.getLong("id"));
               ca.setName(rs.getString("name"));
               ca.setDescription(rs.getString("description"));
               ca.setImagePath(rs.getString("imagePath"));
               ca.setStatus(rs.getBoolean("status"));
               return ca;
            });
   }

   @Override
   public List<Catalog> getListHaveProduct() {
      String sql = "select c.* from catalog c join kfcdb.product p on c.id = p.categoryId group by c.id";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               Catalog ca = new Catalog();
               ca.setId(rs.getLong("id"));
               ca.setName(rs.getString("name"));
               ca.setDescription(rs.getString("description"));
               ca.setImagePath(rs.getString("imagePath"));
               ca.setStatus(rs.getBoolean("status"));
               return ca;
            });
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
