package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {

    // TODO: Add a message
    @Size(min = 1, max = 50)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();

    public Tag(){}

    public Tag(@Size(min = 1, max = 50) @NotBlank String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Event> getEvents() {
        return events;
    }

    public String getDisplayName() { return "#" + name; }

}
