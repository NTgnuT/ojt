package com.ojt.responsitoty;

import com.ojt.model.entity.Orders;
import com.ojt.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findOrdersByCreateDateAndPhoneNumberAndStore(Timestamp cTimestamp, String cString, Store store);
}
