package dao;

import models.Event;
import utils.ConnectionDB;

import java.util.List;

public class TestEventDAO {
    public static void main(String[] args) {
//        selectAllEventsTest();
//        selectEventByIdTest();
//        selectTopEventsByMembersCountTest();
//        createEventTest();
//        updateEventTest();
    }

    public static List<Event> selectAllEventsTest() {
        EventDAOImp eventDAOImp = new EventDAOImp();
        List<Event> events = eventDAOImp.selectAllEvents();
        for (Event event : events) {
            System.out.println(event);
        }
        return events;
    }

    public static Event selectEventByIdTest() {
        EventDAOImp eventDAOImp = new EventDAOImp();
        int eventId = 1; // Use a valid ID for testing
        Event event = eventDAOImp.selectEventById(eventId);
        if (event != null) {
            System.out.println("Event found: " + event);
        } else {
            System.out.println("Event not found with ID: " + eventId);
        }
        return event;
    }

    public static List<Event> selectTopEventsByMembersCountTest() {
        EventDAOImp eventDAOImp = new EventDAOImp();
        int topNumber = 5; // Number of top events to retrieve
        List<Event> events = eventDAOImp.selectTopEventsByMembersCount(topNumber);
        for (Event event : events) {
            System.out.println(event);
        }
        return events;
    }

    public static void createEventTest() {
        EventDAOImp eventDAOImp = new EventDAOImp();
        Event event = new Event();
        event.setId_club(1);
        event.setTitre("New Event");
        event.setDescription("Description of the new event");
        event.setDateDebut("2024-06-01");
        event.setDateFin("2024-06-02");
        boolean isCreated = eventDAOImp.createEvent(event);
        if (isCreated) {
            System.out.println("Event created successfully: " + event);
        } else {
            System.out.println("Failed to create event: " + event);
        }
    }

    public static void updateEventTest() {
        EventDAOImp eventDAOImp = new EventDAOImp();
        int eventId = 1; // Use a valid ID for testing
        Event event = eventDAOImp.selectEventById(eventId);
        if (event != null) {
            event.setId_club(2);
            event.setTitre("Updated Event Title");
            event.setDescription("Updated Description");
            event.setDateDebut("2024-07-01");
            event.setDateFin("2024-07-02");
            Event updatedEvent = eventDAOImp.updateEvent(event);
            if (updatedEvent != null) {
                System.out.println("Event updated successfully: " + updatedEvent);
            } else {
                System.out.println("Failed to update event: " + event);
            }
        } else {
            System.out.println("Event not found with ID: " + eventId);
        }
    }
}
