package com.hng.service;

import com.hng.dao.IGenericDao;
import com.hng.dto.request.FormOrder;
import com.hng.model.Order;
import com.hng.model.User;


public interface IOrderService extends IGenericDao<Order, Long> {
   Order create(User user, FormOrder formOrder);
}
