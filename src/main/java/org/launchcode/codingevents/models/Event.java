package org.launchcode.codingevents.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Event {
    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required.")
    @Size(min=3, max=50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @Size(max=500, message="Description too long!")
    private String description;

    @NotBlank(message = "Email is required.")
    @Email(message="Invalid email. Try again.")
    private String contactEmail;

    private EventType type;

    public Event(){
        // TODO: Fix the problem where the output is non-consecutive numbers
        this.id = nextId++; // This will combine this.id = nextId; and nextId++;.
    }

    public Event(String name, String description, String contactEmail, EventType type) {
        this();         // Call Event()
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.type = type;
        //this.id = nextId++; // This will combine this.id = nextId; and nextId++;.
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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
        /*"Event{" +
                "name='" + name + '\',' +
                "description='" + description + '\'' +
                '}';

         */
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
