package com.ojt.service.StoreService;

import com.ojt.model.entity.Store;

import java.util.List;

public interface StoreService {
    boolean saveStoreData (String fileName);
    List<Store> findAll ();

    Store findByAddress(Store store);
}
