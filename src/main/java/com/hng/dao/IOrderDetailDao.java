package com.hng.dao;

import com.hng.model.OrderDetail;

import java.util.List;

public interface IOrderDetailDao extends IGenericDao<OrderDetail, Long>{
   List<OrderDetail> findOrderDetailByUserId(Long userId);
}
