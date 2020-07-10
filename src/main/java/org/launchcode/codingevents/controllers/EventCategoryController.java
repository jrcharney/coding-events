package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * TODO: Apply mappings for Edit and Delete.
 */

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCatRepo;

    // "index" is implied
    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Categories");
        model.addAttribute("categories",eventCatRepo.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute("title","Create Category");
        model.addAttribute("category", new EventCategory());  // DON'T FORGET ATTRIBUTE NAMES!
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCat, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // GO back to the create for, but add an error message.
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        eventCatRepo.save(newEventCat);
        return "redirect:";
    }
}
