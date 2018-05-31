package com.adminpanel.controller;


import com.adminpanel.domain.Events;
import com.adminpanel.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {


    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addEvent(Model model) {
        Events events= new Events();
        model.addAttribute("events", events);
        return "addEvent";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEventPost(@ModelAttribute("events") Events events ) {
        eventService.save(events);
        return "redirect:eventList";
    }

    @RequestMapping("/eventInfo")
    public String eventInfo(@RequestParam("id") Long id, Model model) {
        Events events = eventService.findOne(id);
        model.addAttribute("events", events);

        return "eventInfo";
    }

    @RequestMapping("/updateEvent")
    public String updateEvent(@RequestParam("id") Long id, Model model) {
        Events events = eventService.findOne(id);
        model.addAttribute("events", events);

        return "updateEvent";
    }

    @RequestMapping(value="/updateEvent", method=RequestMethod.POST)
    public String updateEvenPost(@ModelAttribute("events")  Events events) {
        eventService.save(events);

        return "redirect:/events/eventInfo?id="+events.getId();
    }

    @RequestMapping("/eventsList")
    public String eventList(Model model) {
        List<Events> eventsList = eventService.findAll();
        model.addAttribute("eventsList",  eventsList);
        return "eventsList";

    }

    @RequestMapping(value="/remove", method=RequestMethod.POST)
    public String remove(
            @ModelAttribute("id") String id, Model model
    ) {
        eventService.removeOne(Long.parseLong(id.substring(8)));
        List<Events> eventsList = eventService.findAll();
        model.addAttribute("eventsList", eventsList);

        return "redirect:/event/eventsList";
    }

}
