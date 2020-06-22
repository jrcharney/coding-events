package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";  // You don't need to add file extensions when specifying template names.
    }

    // create lives at /events, like everything else in this controller
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEvent(@ModelAttribute Event newEvent){
        EventData.add(newEvent);
        //return "redirect:/events";    // Implied.
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",EventData.getAll());
        return "events/delete";
    }

    // Here we add "required = false" to the RequestParam in case there are no events.
    // If we don't do that. We get a Whitelabel Error.
    @PostMapping("delete")
    public String processDeleteEvents(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }
}
