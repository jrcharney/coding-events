package org.launchcode.codingevents.controllers;

//import org.launchcode.codingevents.data.EventData;    // retired!
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;    // Do we still need this if it is not used?
// import org.launchcode.codingevents.models.EventType; // retired!
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
//import java.util.ArrayList;
//import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private EventCategoryRepository eventCatRepo;

    @Autowired
    private TagRepository tagRepo;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId == null){
            model.addAttribute("title","All Events");
            model.addAttribute("events", eventRepo.findAll());      // EventData.getAll()
        } else {
            Optional<EventCategory> result = eventCatRepo.findById(categoryId);
            // What if we wanted to find more than one?
            // Optional<List<EventCategory>> results = eventCatRepo.findAllById(categoryId);
            //      probably? but categoryId would need to be a list of ids.
            // Note: result.isPresent() is the same as !result.isEmpty()
            if(result.isEmpty()){
                model.addAttribute("title","Invalid category ID: " + categoryId);
                // TODO: Maybe use a variable with a more personable message?
            } else {
                EventCategory category = result.get();
                model.addAttribute("title",category.getName() + " events");
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";  // You don't need to add file extensions when specifying template names.
    }

    // create lives at /events, like everything else in this controller
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());        // basically model.addAttribute("event",new Event());
        model.addAttribute("categories",eventCatRepo.findAll());
        //model.addAttribute("types",EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if(errors.hasErrors()){
            // Go back to the create form, but add an error message
            model.addAttribute("title","Create Event");
            // Type MUST be rendered
            //model.addAttribute("categories",eventCatRepo.findAll());    // Turn out we don't need this.
            //model.addAttribute("types",EventType.values());
            return "events/create";
        }

        eventRepo.save(newEvent);   //EventData.add(newEvent);      // Something is wrong here.
        //return "redirect:/events";    // Implied.
        return "redirect:";
    }

    @GetMapping("details")
    public String displayEventDetails(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepo.findById(eventId);
        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Event Id: " + eventId);
            // TODO: Needs a better error message.
        }else{
            Event event = result.get();
            model.addAttribute("title", "Details for " + event.getName());
            model.addAttribute("event",event);
            model.addAttribute("tags",event.getTags());
        }
        return "events/details";
    }

    // responds to /events/add-tag?eventId=13 for example
    @GetMapping("add-tag")
    public String renderAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepo.findById(eventId);
        Event event = result.get();
        model.addAttribute("title","Add Tag to " + event.getName());
        model.addAttribute("tags", tagRepo.findAll());
        //model.addAttribute("event",event);
        //model.addAttribute("eventTag", new EventTagDTO());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag",eventTag);
        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Errors errors, Model model){
        if(!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag     = eventTag.getTag();
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepo.save(event);  // update our event in the EventRepo
            }
            return "redirect:details?eventId=" + event.getId();
        }

        return "redirect:add-tag";
    }

    // TODO: Fix our edit stuff later.
    // TODO: apply similar methods in EventCategoryController
    /*
    @GetMapping("edit/{eventId}")
    public String renderEditForm(Model model, @PathVariable int eventId){
        //Event event = EventData.getById(eventId);
        Event event = eventRepo.findAllById(eventId);
        model.addAttribute("title","Edit Event '" + event.getName() + "' (id="+ event.getId() +")");
        model.addAttribute(event);
        // This should add the drop down attributes
        model.addAttribute("types",EventType.values());
        return "events/edit";
    }
    */

    /*
    @PostMapping("edit")
    public String processEditEvent(int eventId, String name, String description, String contactEmail, EventType type){
        // TODO: Do I need to set a Model? YES! but we can't do it right now.
        // TODO: Error checking.
        // (Error checking will require a redirect similar to our GetMapping.
        // This would be easier if we were using client-side validation with JavaScript.
        Event event = eventRepo.findAllById(eventId);   // Hopefully this works.
        //Event event = EventData.getById(eventId);
        event.setName(name);
        event.setDescription(description);
        event.setContactEmail(contactEmail);
        event.setType(type);
        // This should keep the event ID number
        eventRepo.deleteById(eventId);
        eventRepo.save(event);     // Not sure if this is a good substitution
        //EventData.remove(eventId);
        //EventData.add(event);
        return "redirect:";
    }
    */

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",eventRepo.findAll());    // EventData.getAll()
        return "events/delete";
    }

    // Here we add "required = false" to the RequestParam in case there are no events.
    // If we don't do that. We get a Whitelabel Error.
    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null) {
            for (int id : eventIds) {
                eventRepo.deleteById(id);   //EventData.remove(id);
            }
        }
        return "redirect:";
    }
}
