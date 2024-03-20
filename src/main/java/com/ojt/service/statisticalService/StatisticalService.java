package com.ojt.service.statisticalService;

import com.ojt.model.dto.request.ProductStatistical;
import com.ojt.model.dto.request.StoreStatistical;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface StatisticalService {
    List<StoreStatistical> findAll();
    Page<StoreStatistical> getAll(Integer pageNo);
    List<ProductStatistical> findAllProduct();
    Page<ProductStatistical> getAllProduct(Integer pageNo);
    void generateStoreStatisticalExcel(HttpServletResponse response);
    void generateProductStatisticalExcel(HttpServletResponse response);
}
