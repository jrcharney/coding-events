package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TODO: Apply mappings for Edit and Delete.
 */

@Controller
@RequestMapping("eventCategories")          // TODO: change this to "categories"
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCatRepo;

    // "index" is implied
    @GetMapping
    public String displayAllEventCategories(Model model){        // TODO: change this to "displayAllCategories"
        model.addAttribute("title","All Categories");
        model.addAttribute("categories",eventCatRepo.findAll());
        return "eventCategories/index";     // TODO: change this to "categories/index"
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){       // TODO: change this to "renderCreateCategoryForm"
        model.addAttribute("title","Create Category");
        //model.addAttribute(new EventCategory());  // DON'T FORGET ATTRIBUTE NAMES!
        model.addAttribute("category", new EventCategory());  // DON'T FORGET ATTRIBUTE NAMES!
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(
            @ModelAttribute @Valid EventCategory newEventCat,
            Errors errors,
            Model model
    ) {    // TODO: change this to "processCreateCategoryForm"
        if (errors.hasErrors()) {
            // GO back to the create for, but add an error message.
            model.addAttribute("title", "Create Category");
            //model.addAttribute(new EventCategory());        // This line should fix a lot of things!
            // TODO: Should the next line be rewritten? Why doesn't it use newEventCat?
            model.addAttribute("category",new EventCategory());    // This line should fix a lot of things!
            return "eventCategories/create";
        }
        eventCatRepo.save(newEventCat);
        return "redirect:";
    }

    /* --------- */
    /* TODO: Edit categories */
    /* TODO: Don't delete if there are events with a category */

    /* These two functions are not part of this assignment, in fact they shouldn't be used yet. */

    /*
    @GetMapping("delete")
    public String renderDeleteEventCategoriesForm(Model model){
        model.addAttribute("title","Delete Categories");
        model.addAttribute("categories",eventCatRepo.findAll());
        return "eventCategories/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventCategories(@RequestParam(required = false) int[] categoryIds){
        if(categoryIds != null) {
            for (int id : categoryIds) {
                eventCatRepo.deleteById(id);
            }
        }
        return "redirect:";
    }

     */
}
