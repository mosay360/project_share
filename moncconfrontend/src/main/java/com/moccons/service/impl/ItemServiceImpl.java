package com.moccons.service.impl;


import com.moccons.domain.Items;


import com.moccons.repository.ItemRepository;
import com.moccons.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Items> findAll() {
		List<Items> itemList = (List<Items>) itemRepository.findAll();
		List<Items> activeItemList = new ArrayList<>();
		
		for (Items items: itemList) {
			if(items.isActive()) {
				activeItemList.add(items);
			}
		}
		
		return activeItemList;
	}
	
	public Items findOne(Long id) {
		return itemRepository.findOne(id);
	}

	public List<Items> findByCategory(String category){
		List<Items> itemList = itemRepository.findByCategory(category);
		
		List<Items> activeItemList = new ArrayList<>();
		
		for (Items items: itemList) {
			if(items.isActive()) {
				activeItemList.add(items);
			}
		}
		
		return activeItemList;
	}
	
	public List<Items> blurrySearch(String title) {
		List<Items> itemList = itemRepository.findByTitleContaining(title);
List<Items> activeItemList = new ArrayList<>();
		
		for (Items items: itemList) {
			if(items.isActive()) {
				activeItemList.add(items);
			}
		}
		
		return activeItemList;
	}
}
