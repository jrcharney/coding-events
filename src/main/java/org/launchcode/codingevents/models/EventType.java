package org.launchcode.codingevents.models;

/* It was fun playing with enums, but now it's time to use the database. */
@Deprecated
public enum EventType {
    CONFERENCE("Conference"),
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
