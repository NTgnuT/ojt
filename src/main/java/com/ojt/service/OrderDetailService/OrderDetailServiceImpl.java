package com.ojt.service.OrderDetailService;

import com.ojt.model.entity.OrderDetails;
import com.ojt.model.entity.Orders;
import com.ojt.responsitoty.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetails> findAllByOrder(Orders orders) {
        return orderDetailRepository.findAllByOrders(orders);
    }
}
