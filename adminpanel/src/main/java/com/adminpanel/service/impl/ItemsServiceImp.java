package com.adminpanel.service.impl;

import com.adminpanel.domain.Items;
import com.adminpanel.repository.repository.ItemRepository;
import com.adminpanel.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImp  implements ItemsService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Items save(Items items) {
        return itemRepository.save(items);
    }

    @Override
    public List<Items> findAll() {
        return (List<Items>) itemRepository.findAll();
    }

    @Override
    public Items findOne(Long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public void removeOne(Long id) {
        itemRepository.delete(id);
    }
}
