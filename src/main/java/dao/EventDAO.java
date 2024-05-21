package dao;

import models.Event;
import models.Membre;

import java.util.List;

public interface EventDAO {
    List<Event> selectAllEvents();
    Event selectEventById(int idEvent);
    List<Event> selectTopEventsByMembersCount(int top);
    boolean createEvent(Event event);
    Event updateEvent(Event event);
    int getMembersCountByEventId(int idEvent);
     boolean addMembreToEvent(Event event, Membre membre);
}
