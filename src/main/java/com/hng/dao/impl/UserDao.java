package com.hng.dao.impl;

import com.hng.dao.IUserDao;
import com.hng.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
      return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
   }

   @Override
   public User findById(Long id) {
      String sql = "select * from users where id=?";
      return jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            (rs, row) -> {
               User user = new User();
               user.setId(rs.getLong(1));
               user.setFullName(rs.getString(2));
               user.setUsername(rs.getString(3));
               user.setEmail(rs.getString(4));
               user.setPassword(rs.getString(5));
               user.setRole(rs.getBoolean(6));
               return user;
            });
   }

   @Override
   public int save(User user) {
      String sql = null;
      if (user.getId() == null) {
         sql = "Insert Into users(fullname, username, email, password) values (?,?,?,?)";
         return jdbcTemplate.update(sql, user.getFullName(), user.getUsername(), user.getEmail(), user.getPassword());
      } else {
         sql = "UPDATE users set fullName=?, username=?, email=?, password=? where id=?";
         return jdbcTemplate.update(
               sql,
               user.getFullName(),
               user.getUsername(),
               user.getEmail(),
               user.getPassword(),
               user.getId()
         );
      }
   }

   @Override
   public int delete(Long id) {
      String sql = "Delete from users where id=?";
      return jdbcTemplate.update(sql, id);
   }

   @Override
   public User findByUsername(String username) {
      String sql = "select * from users where username=?";
      return jdbcTemplate.queryForObject(
            sql,
            new Object[]{username},
            (rs, row) -> {
               User user = new User();
               user.setId(rs.getLong(1));
               user.setFullName(rs.getString(2));
               user.setUsername(rs.getString(3));
               user.setEmail(rs.getString(4));
               user.setPassword(rs.getString(5));
               user.setRole(rs.getBoolean(6));
               return user;
            });
   }

   @Override
   public boolean checkPhoneExist(String username) {
      return false;
   }
}
