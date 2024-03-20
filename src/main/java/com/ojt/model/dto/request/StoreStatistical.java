package com.ojt.model.dto.request;

import com.ojt.model.entity.OrderDetails;
import com.ojt.model.entity.Orders;
import com.ojt.model.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreStatistical {
    private Long storeId;
    private String storeAddress;
    private int orderTotal;
    private double priceTotal;
    private int productSellerTotal;

//    public StoreStatistical(Store store, List<Orders> orders) {
//        this.storeId = store.getStoreId();
//        this.storeAddress = store.getHomeNumber() + ", " + store.getStreet() + ", " + store.getDistrict() + ", " + store.getCity();
//        this.orderTotal = orders.size();
//
//        double sum = 0;
//        int count = 0;
//        for (Orders or: orders) {
//            for (OrderDetails od :or.getOrderDetails()) {
//                sum += od.getPrice()*od.getQuantity();
//                count+=od.getQuantity();
//            }
//        }
//        this.priceTotal = sum;
//        this.productSellerTotal = count;
//    }
}
