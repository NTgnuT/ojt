package com.ojt.service.ProductService;

import com.ojt.model.entity.Product;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    boolean saveProductData (String fileName);
    void generateExcel (HttpServletResponse response) throws IOException;
    List<Product> findAll ();
}
