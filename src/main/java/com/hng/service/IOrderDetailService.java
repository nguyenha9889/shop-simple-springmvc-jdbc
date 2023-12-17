package com.hng.service;

import com.hng.dto.request.FormDetail;
import com.hng.model.OrderDetail;

public interface IOrderDetailService extends IGenericService<OrderDetail, Long>{
   OrderDetail create(Long cartId, FormDetail formDetail);
   OrderDetail findOrderDetailByProductId(Long productId);
}
