package com.moccons.repository;


import com.moccons.domain.Items;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Items, Long>{
	List<Items> findByCategory(String category);
	
	List<Items> findByTitleContaining(String title);
}
