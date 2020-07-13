package org.launchcode.codingevents.models;

//import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

// What happens here?
// Hibernate will scan this entity object and create a table in MySQL called 'coding_events'.'event'.
@Entity
public class Event extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails details;

    // Something about Gradle? (Yes, needs and older version. 6.3)
    @ManyToOne
    @NotNull(message="Category is required")        // Note: this NotNull instead of NotBlank
    private EventCategory category;    //private EventType type;
    // At this point, everything that was an "EventType type" is now an "EventCategory category".

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Event() { }

    public Event(String name, String description, String contactEmail, EventCategory category) {
        /* this();         // Call Event() */
        this.name = name;
        // TODO: shouldn't there be a this.description (Stuff like this shouldn't be implied!)
        //          Actually, there should!
        this.details = new EventDetails(description,contactEmail);      // TODO: Inspect this later!
        this.category = category;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public EventDetails getDetails() {
        return details;
    }

    public void setDetails(EventDetails details) {
        this.details = details;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public List<Tag> getTags() { return tags; }

    // TODO: see if we can use spread/rest syntax like in JavaScript.
    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    public void deleteTag(Tag tag){
        this.tags.remove(tag);
    }

    // TODO: Why not a hasTag(Tag tag) function to check if the tags contains a tag?

    @Override
    public String toString() { return name; }

}