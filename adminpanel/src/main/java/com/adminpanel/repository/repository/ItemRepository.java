package com.adminpanel.repository.repository;

import com.adminpanel.domain.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Items, Long>{
}
