package controllers;

import dao.ClubDAO;
import dao.ClubDAOImp;
import dao.EventDAO;
import dao.EventDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Club;
import models.Event;
import models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "admin", urlPatterns = {"/admin/clubs/show", "/admin/events/show", "/admin/home"})
public class AdminController extends HttpServlet {
    private final String ADMIN_HOME_PAGE = "/WEB-INF/views/admin/home.jsp";
    private final String ADMIN_CLUB_PAGE = "/WEB-INF/views/admin/club/show.jsp";
    private final String ADMIN_EVENT_PAGE = "/WEB-INF/views/admin/event/show.jsp";
    private final String LOGIN_PAGE = "/WEB-INF/views/authentification/login.jsp";
    private ClubDAO clubDAO = new ClubDAOImp();
    private EventDAO eventDAO = new EventDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int top = 10;
        Map<Integer, String> gerantNames = new HashMap<>();
        Map<Integer, Integer> clubMembersCount = new HashMap<>();
        Map<Integer, Integer> eventMembersCount = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Club> clubs = clubDAO.getTopClubs(top);
        List<Event> events = eventDAO.selectTopEventsByMembersCount(top);

        for (Club club : clubs) {
            gerantNames.put(club.getIdUser(), clubDAO.getGerantNameById(club.getIdUser()));
            clubMembersCount.put(club.getIdClub(), clubDAO.getMembersCountByClubId(club.getIdClub()));
        }

        for (Event event : events) {
            eventMembersCount.put(event.getId_evenement(), eventDAO.getMembersCountByEventId(event.getId_evenement()));
        }
        System.out.println("test: " + request.getServletPath());
        if (request.getServletPath().equals("/admin/clubs/show")) {
            if (user != null) {
                request.setAttribute("clubs", clubs);
                request.setAttribute("clubMembersCount", clubMembersCount);
                request.setAttribute("gerantNames", gerantNames);
                request.getRequestDispatcher(ADMIN_CLUB_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if (request.getServletPath().equals("/admin/events/show")) {
            if (user != null) {
                request.setAttribute("events", events);
                request.setAttribute("eventMembersCount", eventMembersCount);
                request.getRequestDispatcher(ADMIN_EVENT_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if (request.getServletPath().equals("/admin/home")) {
            if (user != null) {
                request.getRequestDispatcher(ADMIN_HOME_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests here
    }
}
