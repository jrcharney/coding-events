package org.launchcode.codingevents.models.dto;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.Tag;

import javax.validation.constraints.NotNull;

/**
 * This creates a Data Transfer Object (DTO) between our Event and Tag classes
 */


public class EventTagDTO {

    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO() { }

    // TODO: Create a constructor that sets the event so that the tag may be added later.
    // public EventTagDTO(Event event);
    // TODO: Create a constructor that uses the full constructor.
    // public EventTagDTO(Even event, tag Tag);

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
