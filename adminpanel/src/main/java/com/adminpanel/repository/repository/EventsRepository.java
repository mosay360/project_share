package com.adminpanel.repository.repository;

import com.adminpanel.domain.Events;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<Events, Long> {
}
