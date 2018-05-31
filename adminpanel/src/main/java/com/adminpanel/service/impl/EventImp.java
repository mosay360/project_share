package com.adminpanel.service.impl;


import com.adminpanel.domain.Events;
import com.adminpanel.repository.repository.EventsRepository;
import com.adminpanel.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventImp implements EventService {

    @Autowired
    private EventsRepository eventsRepository;


    @Override
    public Events save(Events events) {
        return eventsRepository.save(events);
    }

    @Override
    public List<Events> findAll() {
        return  (List<Events>) eventsRepository.findAll();
    }

    @Override
    public Events findOne(Long id) {
        return eventsRepository.findOne(id);
    }

    @Override
    public void removeOne(Long id) {
        eventsRepository.delete(id);
    }
}
