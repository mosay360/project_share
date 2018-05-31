package com.moccons.repository;

import com.moccons.domain.Events;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<Events, Long> {
}
