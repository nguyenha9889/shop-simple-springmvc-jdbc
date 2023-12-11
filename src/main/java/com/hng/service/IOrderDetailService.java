package com.hng.service;

import com.hng.dto.request.FormOrderDetail;
import com.hng.model.OrderDetail;

public interface IOrderDetailService extends IGenericService<OrderDetail, Long>{
   OrderDetail create(Long cartId, FormOrderDetail formOrderDetail);
   OrderDetail findOrderDetailByProductId(Long productId);
}
