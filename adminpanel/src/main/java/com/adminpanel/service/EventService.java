package com.adminpanel.service;


import com.adminpanel.domain.Events;

import java.util.List;

public interface EventService {
    List<Events> findAll();
    Events findOne(Long id);
    void removeOne(Long id);
    Events save(Events events);
}

