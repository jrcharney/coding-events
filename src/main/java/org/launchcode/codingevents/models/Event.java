package org.launchcode.codingevents.models;

//import javax.persistence.CascadeType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public Event() { }

    public Event(String name, String description, String contactEmail, EventCategory category) {
        /* this();         // Call Event() */
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

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

    @Override
    public String toString() { return name; }

}