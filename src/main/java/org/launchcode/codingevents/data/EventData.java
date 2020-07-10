package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


// Note: All the members in this class are static!
// Note: Rather than deleting this file, this class is now DEPRECATED!
@Deprecated
public class EventData {

    // Need a place to put events
    private static final Map<Integer, Event> events = new HashMap<>();

    // Get all events
    public static Collection<Event> getAll(){ return events.values(); }

    // Get a single event
    public static Event getById(int id){
        return events.get(id);
    }

    // Add an event
    public static void add(Event event){
        events.put(event.getId(), event);
    }

    // Remove an event
    public static void remove(int id){
        events.remove(id);
    }
}
