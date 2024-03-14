package com.ojt.responsitoty;

import com.ojt.model.entity.OrderDetails;
import com.ojt.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findAllByOrders (Orders orders);
}