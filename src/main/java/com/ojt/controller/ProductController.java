package com.ojt.controller;

import com.ojt.service.ProductService.ProductServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @RequestMapping("/product")
    public String home (Model model) {
        model.addAttribute("check", false);
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }


    @GetMapping("/export")
    public void exportExcel (HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Product.xls";

        response.setHeader(headerKey, headerValue);
        productService.generateExcel(response);
    }
    @PostMapping("/uploadProductFile")
    public String upload(@RequestParam("productFile")MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".csv")){
            try {
                file.transferTo(new File("C:\\Module 5\\demo1\\src\\main\\resources\\uploads\\" + fileName));
                if (productService.saveProductData(fileName)){
                    return "redirect:/product";
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        model.addAttribute("check", true);
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }


}
