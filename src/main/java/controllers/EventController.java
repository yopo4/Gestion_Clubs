package controllers;

import dao.EventDAO;
import dao.EventDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Event;

import java.io.IOException;

@WebServlet(name = "event", value = "/event")
public class EventController extends HttpServlet {

    private final String EVENT_PAGE = "/WEB-INF/views/protected/event.jsp";

    private EventDAO eventDAO = new EventDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int eventId = Integer.parseInt(request.getParameter("id_event"));
        int eventMembersCount = eventDAO.getMembersCountByEventId(eventDAO.selectEventById(eventId).getId_evenement());
        Event event = eventDAO.selectEventById(eventId);

        request.setAttribute("event", event);
        request.setAttribute("eventMembersCount", eventMembersCount);
        request.getRequestDispatcher(EVENT_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
