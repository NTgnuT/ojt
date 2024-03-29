package com.ojt.service.LogImportService;

import com.ojt.model.entity.LogImport;
import com.ojt.model.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface LogImportService {
    List<LogImport> findAll ();
    LogImport findById (Long id);
    boolean getLog(Map<Integer, String> logDetailMap, String fileName);
    Page<LogImport> getAll (Integer pageNo);
    Page<LogImport> searchLogImport(String keyword, Integer pageNo);
}
