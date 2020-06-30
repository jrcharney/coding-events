package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        // TODO: I have a feeling this line shouldn't be here.
        model.addAttribute(new Event());
        model.addAttribute("types",EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if(errors.hasErrors()){
            // Go back to the create form, but add an error message
            model.addAttribute("title","Create Event");
            // Type MUST be rendered
            model.addAttribute("types",EventType.values());
            return "events/create";
        }
        EventData.add(newEvent);
        //return "redirect:/events";    // Implied.
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String renderEditForm(Model model, @PathVariable int eventId){
        Event event = EventData.getById(eventId);
        model.addAttribute("title","Edit Event '" + event.getName() + "' (id="+ event.getId() +")");
        model.addAttribute(event);
        // This should add the drop down attributes
        model.addAttribute("types",EventType.values());
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditEvent(int eventId, String name, String description, String contactEmail, EventType type){
        // TODO: Do I need to set a Model? YES! but we can't do it right now.
        /* TODO: Error checking.
         * (Error checking will require a redirect similar to our GetMapping.
         * This would be easier if we were using client-side validation with JavaScript.
         */
        Event event = EventData.getById(eventId);
        event.setName(name);
        event.setDescription(description);
        event.setContactEmail(contactEmail);
        event.setType(type);
        // This should keep the event ID number
        EventData.remove(eventId);
        EventData.add(event);
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
