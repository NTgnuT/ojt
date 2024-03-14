package com.ojt.service.OrderService;

import com.ojt.model.entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();
    boolean saveOrdersData (String fileName);
    Orders findById (Long id);
}
