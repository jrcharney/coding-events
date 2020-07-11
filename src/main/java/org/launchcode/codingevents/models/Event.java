package org.launchcode.codingevents.models;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    // Not sure why this is not working
    // Something about Gradle?
    @ManyToOne //(cascade = CascadeType.ALL)          // many events to one category. (We need the cascade variable)
    @NotNull(message="Event Category is required")        // Note: this NotNull instead of NotBlank
    private EventCategory eventCategory;    //private EventType type;
    // At this point, everything that was an "EventType type" is now an "EventCategory category".

    public Event() { }

    public Event(String name, String description, String contactEmail, EventCategory eventCategory) {
        /* this();         // Call Event() */
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.eventCategory = eventCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }


    /* public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

     */

    @Override
    public String toString() { return name; }

}