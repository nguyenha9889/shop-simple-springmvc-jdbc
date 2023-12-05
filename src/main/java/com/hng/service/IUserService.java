package com.hng.service;


import com.hng.dto.request.FormLogin;
import com.hng.dto.request.FormRegister;
import com.hng.model.User;
import java.util.List;


public interface IUserService extends IGenericService<User, Long> {
   List<User> findAll(int page, int size);
   List<User> findAll();
   User findById(Long id);
   User findByUsername(String username);
   void save(FormRegister formRegister);
   boolean checkExistByEmail(String email);
   boolean checkExistByPhone(String phone);
   int delete(Long id);
}
