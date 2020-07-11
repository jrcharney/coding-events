package org.launchcode.codingevents.models;

import javax.persistence.Entity;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.OneToMany;     // We're not using it here, but it probably wouldn't hurt to import it.
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    @Size(min = 3, message = "Name must be at least 3 characters.")
    private String name;

    /*
    // final added to prevent the list from changing, but not its contents.
    @OneToMany(mappedBy="category")
    private final List<Event> events = new ArrayList<>();

     */

    public EventCategory() { }

    public EventCategory(@Size(min = 3, message = "Name must be at least characters.") String name) { this.name = name; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    /*
    public List<Event> getEvents() {
        return events;
    }

     */

    @Override
    public String toString() { return name; }

}
