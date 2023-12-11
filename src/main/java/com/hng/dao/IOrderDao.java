package com.hng.dao;

import com.hng.model.Order;
import com.hng.model.User;
import java.util.List;

public interface IOrderDao extends IGenericDao<Order, Long>{
   List<Order> findOrderByUserId(User user);
}
