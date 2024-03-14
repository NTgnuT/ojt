package com.ojt.service.OrderDetailService;

import com.ojt.model.entity.OrderDetails;
import com.ojt.model.entity.Orders;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetails> findAllByOrder (Orders orders);
}
