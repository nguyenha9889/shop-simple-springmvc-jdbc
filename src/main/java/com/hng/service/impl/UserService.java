package com.hng.service.impl;

import com.hng.dao.IUserDao;
import com.hng.model.User;
import com.hng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
   @Autowired
   private IUserDao userDao;
   @Override
   public List<User> findAll() {
      return userDao.findAll();
   }

   @Override
   public User findById(Long id) {
      return userDao.findById(id);
   }

   @Override
   public void save(User user) {
      userDao.save(user);
   }

   @Override
   public void delete(Long id) {
      userDao.delete(id);
   }

   @Override
   public User findByUsername(String username) {
      return userDao.findByUsername(username);
   }
}
