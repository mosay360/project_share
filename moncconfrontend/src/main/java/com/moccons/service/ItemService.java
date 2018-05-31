package com.moccons.service;


import com.moccons.domain.Items;
import java.util.List;

public interface ItemService {
	List<Items> findAll();
	
	Items findOne(Long id);
	
	List<Items> findByCategory(String category);
	
	List<Items> blurrySearch(String title);
}
