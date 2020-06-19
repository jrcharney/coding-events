package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    // If you put the datatypes in your List<> on the left side,
    // you don't need to use them on the right side in the constructor.
    private static List<String> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model){
        /*
        events.add("Strange Loop");
        events.add("LaunchCode LC101");
        events.add("Code with Pride");
        events.add("Code Until Dawn");  // or is it Code Till Dawn?
        events.add("Venture Cafe");
        events.add("OpenSTL");
        events.add("Global Game Jam");
        events.add("Raspberry Pi Jam");

         */
        model.addAttribute("events",events);
        return "events/index";  // You don't need to add file extensions when specifying template names.
    }

    // create lives at /events, like everything else in this controller
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }

    @PostMapping("create")
    public String createEvent(@RequestParam String eventName){
        // Note: The name of the RequestParam in our needs to be the same as the name in our form input.
        // So if we have an input named eventName, we should use a RequestParam called eventName.
        events.add(eventName);  // events will get a new event after each time the form is processed.
        //return "redirect:/events";    // Implied.
        return "redirect:";
    }
}
