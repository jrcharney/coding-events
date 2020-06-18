package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    @GetMapping
    public String displayAllEvents(Model model){
        List<String> events = new ArrayList<>();
        events.add("Strange Loop");
        events.add("LaunchCode LC101");
        events.add("Code with Pride");
        events.add("Code Until Dawn");  // or is it Code Till Dawn?
        events.add("Venture Cafe");
        events.add("OpenSTL");
        events.add("Global Game Jam");
        events.add("Raspberry Pi Jam");
        model.addAttribute("events",events);
        return "events/index";
    }
}
