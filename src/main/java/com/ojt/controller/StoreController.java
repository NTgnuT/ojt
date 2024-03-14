package com.ojt.controller;

import com.ojt.service.StoreService.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class StoreController {
    @Autowired
    private StoreService storeService;
    @RequestMapping("/store")
    public String home(Model model) {
        model.addAttribute("check", false);
        model.addAttribute("stores", storeService.findAll());
        return "store/index";
    }
    @PostMapping("/uploadStoreFile")
    public String uploadStoreFile(@RequestParam("storeFile")MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".csv")){
            try {
                file.transferTo(new File("C:\\Module 5\\demo1\\src\\main\\resources\\uploads\\" + fileName));
                if (storeService.saveStoreData(fileName)){
                    return "redirect:/store";
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        model.addAttribute("check", true);
        model.addAttribute("stores", storeService.findAll());
        return "store/index";
    }
}
