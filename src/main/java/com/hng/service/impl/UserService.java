package com.hng.service.impl;


import com.hng.dao.IUserDao;
import com.hng.dto.request.FormRegister;
import com.hng.model.User;
import com.hng.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class UserService implements IUserService {
   @Autowired
   private IUserDao userDao;

   @Override
   public List<User> findAll(int page, int size) {
      return userDao.findAll(page, page*size);
   }

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
   public boolean checkExistByEmail(String email) {
      return findAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
   }

   @Override
   public boolean checkExistByPhone(String phone) {
      return findAll().stream().anyMatch(u -> u.getPhone().equals(phone));
   }

   @Override
   public User findByUsername(String username) {
      return userDao.findByUsername(username);
   }

   @Override
   public void save(FormRegister formRegister) {
     User user = new User(
            formRegister.getFullName(),
            formRegister.getUsername(),
            formRegister.getPhone(),
            formRegister.getEmail(),
            BCrypt.hashpw(formRegister.getPassword(), BCrypt.gensalt(12))
      );
     userDao.save(user);
   }
   @Override
   public int delete(Long id) {
      return userDao.delete(id);
   }
}
