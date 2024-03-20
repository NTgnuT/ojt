package com.ojt.service.LogImportService;

import com.ojt.model.entity.LogDetail;
import com.ojt.model.entity.LogImport;
import com.ojt.model.entity.Store;
import com.ojt.responsitoty.LogDetailRepository;
import com.ojt.responsitoty.LogImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
@Service
public class LogImportServiceImpl implements LogImportService{
    @Autowired
    private LogDetailRepository logDetailRepository;
    @Autowired
    private LogImportRepository logImportRepository;

    @Override
    public List<LogImport> findAll() {
        return logImportRepository.findAll();
    }

    @Override
    public LogImport findById(Long id) {
        return logImportRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean getLog(Map<Integer, String> logDetailMap, String fileName){
        try {
            LogImport logImport = new LogImport();
            logImport.setCreateDate(getTimeNow());
            logImport.setFileName(fileName);
            LogImport logImportSaved = logImportRepository.save(logImport);
            for (Map.Entry<Integer, String> entry : logDetailMap.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                LogDetail logDetail = new LogDetail();
                logDetail.setLineError(key);
                logDetail.setNameError(value);
                logDetail.setLogImport(logImportSaved);
                logDetailRepository.save(logDetail);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Page<LogImport> getAll(Integer pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNo - 1, 5, sort);
        return logImportRepository.findAll(pageable);
    }

    @Override
    public Page<LogImport> searchLogImport(String keyword, Integer pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNo - 1, 5, sort);
        return logImportRepository.findAllByFileNameContainingIgnoreCase(keyword, pageable);
    }

    private Timestamp getTimeNow(){
        // Lấy ngày giờ hiện tại
        long currentTimeMillis = System.currentTimeMillis();

        // Tạo đối tượng Timestamp từ currentTimeMillis
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        timestamp.setNanos(0);
        // In ra kết quả
        return timestamp;
    }


}
