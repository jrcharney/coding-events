package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    TagRepository tagRepo;

    @GetMapping
    public String displayAllTags(Model model){
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepo.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String renderCreateTagForm(Model model){
        model.addAttribute("title", "Create Tag");
        //model.addAttribute(new Tag());
        model.addAttribute("tag",new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag newTag, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Tag");
            //model.addAttribute(new Tag());        // TODO: Watch this line. Compare it with other controllers.
            //model.addAttribute("tag",new Tag()); // This should not have happened!
            return "tags/create";
        }
        tagRepo.save(newTag);
        return "redirect:";
    }

    /* TODO: edit and delete methods */
}
