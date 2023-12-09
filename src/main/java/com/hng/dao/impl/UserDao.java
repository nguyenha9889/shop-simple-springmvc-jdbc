package com.hng.dao.impl;

import com.hng.dao.IUserDao;
import com.hng.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class UserDao implements IUserDao {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Override
   public List<User> findAll() {
      String sql = "select * from users";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               User user = new User();
               user.setId(rs.getLong("id"));
               user.setFullName(rs.getString("fullName"));
               user.setUsername(rs.getString("username"));
               user.setEmail(rs.getString("email"));
               user.setPhone(rs.getString("phone"));
               user.setPassword(rs.getString("password"));
               user.setRole(rs.getBoolean("roles"));
               user.setStatus(rs.getBoolean("status"));
               return user;
            });
   }

   @Override
   public List<User> findAll(int limit, int offset) {
      String sql = "select * from user limit "+limit+" offset "+offset+";";
      return jdbcTemplate.query(
            sql,
            (rs, row) -> {
               User user = new User();
               user.setId(rs.getLong("id"));
               user.setFullName(rs.getString("fullName"));
               user.setUsername(rs.getString("username"));
               user.setEmail(rs.getString("email"));
               user.setPhone(rs.getString("phone"));
               user.setPassword(rs.getString("password"));
               user.setRole(rs.getBoolean("roles"));
               user.setStatus(rs.getBoolean("status"));
               return user;
            });
   }

   @Override
   public User findById(Long id) {
      String sql = "select * from users where id=?";
      return jdbcTemplate.query(
            sql,
            new Object[]{id},
            (rs) -> {
               User user = null;
               if (rs.next()) {
                  user = new User();
                  user.setId(rs.getLong("id"));
                  user.setFullName(rs.getString("fullName"));
                  user.setUsername(rs.getString("username"));
                  user.setEmail(rs.getString("email"));
                  user.setPhone(rs.getString("phone"));
                  user.setPassword(rs.getString("password"));
                  user.setRole(rs.getBoolean("roles"));
                  user.setStatus(rs.getBoolean("status"));
               }
               return user;
            });
   }

   @Override
   public User findByUsername(String username) {
      String sql = "select * from users where username = ?";
      return jdbcTemplate.query(
            sql,
            new Object[]{username},
            (rs) -> {
               User user = null;
               if (rs.next()) {
                  user = new User();
                  user.setId(rs.getLong("id"));
                  user.setFullName(rs.getString("fullName"));
                  user.setUsername(rs.getString("username"));
                  user.setEmail(rs.getString("email"));
                  user.setPhone(rs.getString("phone"));
                  user.setPassword(rs.getString("password"));
                  user.setRole(rs.getBoolean("roles"));
                  user.setStatus(rs.getBoolean("status"));
               }
               return user;
            });
   }

   @Override
   public int save(User user) {
      String sql = null;
      if (user.getId() == null) {
         sql = "Insert Into users(fullname, username, phone, email, password) values (?,?,?,?,?)";
         return jdbcTemplate.update(
               sql,
               user.getFullName(),
               user.getUsername().toLowerCase(),
               user.getPhone(),
               user.getEmail().toLowerCase(),
               user.getPassword());
      } else {
         sql = "UPDATE users set fullName=?, username=?, phone=?, email=?, password=? where id=?";
         return jdbcTemplate.update(
               sql,
               user.getFullName(),
               user.getUsername().toLowerCase(),
               user.getPhone(),
               user.getEmail().toLowerCase(),
               user.getPassword(),
               user.getId()
         );
      }
   }

   @Override
   public int delete(Long id) {
      String sql = "UPDATE users set status=0 where id=?";
      return jdbcTemplate.update(sql, id);
   }
}
