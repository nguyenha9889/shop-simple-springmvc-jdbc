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
      return null;
   }

   @Override
   public User findById(Long id) {
      return null;
   }

   @Override
   public void save(User user) {

   }

   @Override
   public void delete(Long id) {

   }

   @Override
   public User findByUserName(String username) {
      return null;
   }
}
