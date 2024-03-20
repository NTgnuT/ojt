package com.ojt.service.logDetailService;

import com.ojt.model.entity.LogDetail;
import com.ojt.model.entity.LogImport;
import com.ojt.responsitoty.LogDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogDetailServiceImpl implements LogDetailService{
    @Autowired
    private LogDetailRepository logDetailRepository;
    @Override
    public Page<LogDetail> getAll(Long logImportId, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return logDetailRepository.findAllByLogImport_Id(logImportId,pageable);
    }
}
