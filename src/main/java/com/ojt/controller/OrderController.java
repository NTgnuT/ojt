package com.ojt.controller;

import com.ojt.model.entity.OrderDetails;
import com.ojt.model.entity.Orders;
import com.ojt.service.OrderDetailService.OrderDetailService;
import com.ojt.service.OrderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @RequestMapping("/order")
    public String home (Model model) {
        model.addAttribute("check", true);
        model.addAttribute("orders", orderService.findAll());
        return "order/index";
    }
    @PostMapping("/uploadOrderFile")
    public String uploadOrderFile(@RequestParam("orderFile") MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".csv")){
            try {
                file.transferTo(new File("C:\\Module 5\\demo1\\src\\main\\resources\\uploads\\" + fileName));
                if (orderService.saveOrdersData(fileName)){
                    return "redirect:/order";
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        model.addAttribute("check", true);
        model.addAttribute("orders", orderService.findAll());
        return "order/index";
    }

    @GetMapping("/orderDetail/{id}")
    public String orderDetail(@PathVariable("id") Long id, Model model) {
        Orders orders = orderService.findById(id);
        List<OrderDetails> orderDetails = orderDetailService.findAllByOrder(orders);

        model.addAttribute("orderDetail", orderDetails);
        return "orderDetail/index";
    }
}
