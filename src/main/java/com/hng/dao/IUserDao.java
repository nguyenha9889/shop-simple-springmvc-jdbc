package com.hng.dao;

import com.hng.model.User;


public interface IUserDao extends IGenericDao<User, Long> {
   User findByUsername(String username);
   boolean checkPhoneExist(String username);
}
