package com.ojt.service.logDetailService;

import com.ojt.model.entity.LogDetail;
import com.ojt.model.entity.LogImport;
import org.springframework.data.domain.Page;

public interface LogDetailService {
    Page<LogDetail> getAll (Long logImportId, Integer pageNo);
}
