package org.launchcode.codingevents.data;
// Note: Some people might call something like EventRepository EventDAO.

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
