package com.ojt.controller;

import com.ojt.model.entity.LogDetail;
import com.ojt.model.entity.LogImport;
import com.ojt.model.entity.Store;
import com.ojt.service.LogImportService.LogImportService;
import com.ojt.service.logDetailService.LogDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileHistoryController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private LogImportService logImportService;
    @Autowired
    private LogDetailService logDetailService;
    @RequestMapping("/fileHistory")
    public String home (Model model, @Param("keyword") String keyword,
                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (httpSession.getAttribute("userLogin") == null) {
            return "redirect:/login";
        }
        Page<LogImport> logImports = logImportService.getAll(pageNo);
        if (keyword != null) {
            logImports = logImportService.searchLogImport(keyword, pageNo);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPage", logImports.getTotalPages());
        model.addAttribute("currentPage", pageNo);

        model.addAttribute("log", logImports);
        return "fileHistory/index";
    }

    @GetMapping("/logDetail/{id}")
    public String homeLogDetail (@PathVariable("id")Long id, Model model,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo ) {
        LogImport logImport = logImportService.findById(id);
        Page<LogDetail> logDetails = logDetailService.getAll(logImport.getId(), pageNo);

        model.addAttribute("totalPage", logDetails.getTotalPages());
        model.addAttribute("currentPage", pageNo);

        model.addAttribute("logImport", logImport);
        model.addAttribute("detail", logDetails);
        return "logDetail/index";

    }
}
