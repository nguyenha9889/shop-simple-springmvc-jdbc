package com.hng.service;


import com.hng.model.User;

public interface IUserService extends IGenericService<User, Long> {
   User findByUsername(String username);
}
