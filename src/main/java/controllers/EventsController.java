package controllers;

import dao.ClubDAO;
import dao.ClubDAOImp;
import dao.EventDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Club;
import models.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "events", value = "/events")
public class EventsController extends HttpServlet {

    private final String EVENTS_PAGE = "/WEB-INF/views/protected/events.jsp";

    private EventDAOImp eventDAO = new EventDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Event> events = eventDAO.selectAllEvents();
        Map<Integer, String> gerantNames = new HashMap<>();
        Map<Integer, Integer> eventMembersCount = new HashMap<>();

        for (Event event : events) {
            eventMembersCount.put(event.getId_evenement(), eventDAO.getMembersCountByEventId(event.getId_evenement()));
        }
        System.out.println(eventMembersCount);
        request.setAttribute("events", events);
        request.setAttribute("clubMembersCount", eventMembersCount);
        request.getRequestDispatcher(EVENTS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
