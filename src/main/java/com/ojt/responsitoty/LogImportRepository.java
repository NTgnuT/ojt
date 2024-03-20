package com.ojt.responsitoty;

import com.ojt.model.entity.LogImport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogImportRepository extends JpaRepository<LogImport, Long> {
    Page<LogImport> findAllByFileNameContainingIgnoreCase (String keyword, Pageable pageable);
}
