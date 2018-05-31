package com.moccons.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.moccons.domain.Events;
import com.moccons.repository.EventsRepository;
import com.moccons.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventImp implements EventService {

    @Autowired
    private EventsRepository eventsRepository;


    public List<Events> findAll() {
        List<Events> eventsList=(List<Events>) eventsRepository;
        List<Events> activeEventList = new ArrayList<>();

        for (Events events: eventsList){
            if (events.isActive()){
                activeEventList.add(events);
            }
        }
        return activeEventList;
    }
}
