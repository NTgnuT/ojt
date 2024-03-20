package com.ojt.responsitoty;

import com.ojt.model.entity.LogDetail;
import com.ojt.model.entity.LogImport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDetailRepository extends JpaRepository<LogDetail, Long> {
    Page<LogDetail> findAllByLogImport_Id (Long id, Pageable pageable);
}
