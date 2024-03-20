package com.ojt.controller;

import com.ojt.model.dto.request.ProductStatistical;
import com.ojt.model.dto.request.StoreStatistical;
import com.ojt.service.statisticalService.StatisticalService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    StatisticalService statisticalService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo ) {
        if (httpSession.getAttribute("userLogin") == null) {
            return "redirect:/login";
        }
        Page<StoreStatistical> storeStatisticalList = statisticalService.getAll(pageNo);

        model.addAttribute("totalPage", storeStatisticalList.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("storeList", storeStatisticalList);
        return "home/index";
    }

    @GetMapping("/statisticalProduct")
    public String statistical(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo ) {
        if (httpSession.getAttribute("userLogin") == null) {
            return "redirect:/login";
        }
        Page<ProductStatistical> productStatisticalPage = statisticalService.getAllProduct(pageNo);
        model.addAttribute("totalPage", productStatisticalPage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("productList", productStatisticalPage);
        return "home/product-statical";

    }

    @GetMapping("/storeStatisticalExport")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=storeStatistical.xls";

        response.setHeader(headerKey, headerValue);
        statisticalService.generateStoreStatisticalExcel(response);

    }

    @GetMapping("/productStatisticalExport")
    public void productExportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=storeStatistical.xls";

        response.setHeader(headerKey, headerValue);
        statisticalService.generateProductStatisticalExcel(response);
    }
}
