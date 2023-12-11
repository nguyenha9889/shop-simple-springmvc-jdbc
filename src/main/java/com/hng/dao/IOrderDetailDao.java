package com.hng.dao;

import com.hng.model.OrderDetail;


public interface IOrderDetailDao extends IGenericDao<OrderDetail, Long>{
   OrderDetail findOrderDetailByProductId(Long productId);
}
