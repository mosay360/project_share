package com.adminpanel.service;

import com.adminpanel.domain.Items;

import java.util.List;

public interface ItemsService {
    Items save (Items items);
    List <Items> findAll();
    Items findOne(Long id);
    void removeOne(Long id);
}
