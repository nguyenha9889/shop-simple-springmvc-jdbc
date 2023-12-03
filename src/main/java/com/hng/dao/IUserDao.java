package com.hng.dao;

import com.hng.model.User;

import java.util.List;


public interface IUserDao extends IGenericDao<User, Long> {
   User findByUsername(String username);
   List<User> findAll(int limit, int offset);
}
