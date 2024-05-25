package controllers;

import dao.*;
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

@WebServlet(name = "admin", urlPatterns = {
        "/admin/clubs/show",
        "/admin/events/show",
        "/admin/home",
        "/admin/clubs/add",
        "/admin/clubs/update",
        "/admin/events/add",
        "/admin/events/update"
})
public class AdminController extends HttpServlet {
    private final String ADMIN_HOME_PAGE = "/WEB-INF/views/admin/home.jsp";
    private final String ADMIN_CLUB_PAGE = "/WEB-INF/views/admin/club/show.jsp";
    private final String ADMIN_EVENT_PAGE = "/WEB-INF/views/admin/event/show.jsp";
    private final String LOGIN_PAGE = "/WEB-INF/views/authentification/login.jsp";
    private final String ADD_CLUB_PAGE = "/WEB-INF/views/admin/club/add.jsp";
    private final String UPDATE_CLUB_PAGE = "/WEB-INF/views/admin/club/update.jsp";
    private final String ADD_EVENT_PAGE = "/WEB-INF/views/admin/event/add.jsp";
    private final String UPDATE_EVENT_PAGE = "/WEB-INF/views/admin/event/update.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();
    private EventDAO eventDAO = new EventDAOImp();
    private UserDAO userDAO = new UserDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int top = 10;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<Integer, String> gerantNames = new HashMap<>();
        Map<Integer, Integer> clubMembersCount = new HashMap<>();
        Map<Integer, Integer> eventMembersCount = new HashMap<>();
        List<User> noManagerUsers = userDAO.findUsersWithoutRoleGerant();
        List<Club> clubs = clubDAO.getTopClubs(top);
        List<Event> events = eventDAO.selectTopEventsByMembersCount(top);

        for (Club club : clubs) {
            gerantNames.put(club.getIdUser(), clubDAO.getGerantNameById(club.getIdUser()));
            clubMembersCount.put(club.getIdClub(), clubDAO.getMembersCountByClubId(club.getIdClub()));
        }

        for (Event event : events) {
            eventMembersCount.put(event.getId_evenement(), eventDAO.getMembersCountByEventId(event.getId_evenement()));
        }

        String path = request.getServletPath();

        if ("/admin/clubs/show".equals(path)) {
            if (user != null) {
                request.setAttribute("clubs", clubs);
                request.setAttribute("clubMembersCount", clubMembersCount);
                request.setAttribute("gerantNames", gerantNames);
                request.getRequestDispatcher(ADMIN_CLUB_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if ("/admin/events/show".equals(path)) {
            if (user != null) {
                request.setAttribute("events", events);
                request.setAttribute("eventMembersCount", eventMembersCount);
                request.getRequestDispatcher(ADMIN_EVENT_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if ("/admin/home".equals(path)) {
            if (user != null) {
                request.getRequestDispatcher(ADMIN_HOME_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if ("/admin/clubs/add".equals(path)) {
            if (user != null) {
                request.setAttribute("noManagerUsers", noManagerUsers);
                request.getRequestDispatcher(ADD_CLUB_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if ("/admin/clubs/update".equals(path)) {
            if (user != null) {
                int clubId = Integer.parseInt(request.getParameter("id"));
                Club club = clubDAO.getClubById(clubId);
                User manager = userDAO.getUserById(club.getIdUser());
                request.setAttribute("club", club);
                request.setAttribute("manager", manager);
                request.setAttribute("noManagerUsers", noManagerUsers);
                request.getRequestDispatcher(UPDATE_CLUB_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if ("/admin/events/add".equals(path)) {
            if (user != null) {
                request.setAttribute("clubs", clubs); // Add this line
                request.getRequestDispatcher(ADD_EVENT_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else if ("/admin/events/update".equals(path)) {
            if (user != null) {
                int eventId = Integer.parseInt(request.getParameter("id"));
                Event event = eventDAO.selectEventById(eventId);
                request.setAttribute("event", event);
                request.setAttribute("clubs", clubs); // Add this line
                request.getRequestDispatcher(UPDATE_EVENT_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin/clubs/add".equals(path)) {
            String clubName = request.getParameter("clubName");
            int gerantId = Integer.parseInt(request.getParameter("gerantId"));

            System.out.println("Club Name: " + clubName);
            System.out.println("Gerant ID: " + gerantId);

            User gerant = userDAO.getUserById(gerantId);
            if (gerant != null) {
                if ("membre".equalsIgnoreCase(gerant.getRole())) {
                    gerant.setRole("gerant");
                    userDAO.updateUserRole(gerant);
                }
            }

            Club club = new Club();
            club.setNom(clubName);
            club.setIdUser(gerantId);

            clubDAO.createClub(club);
            response.sendRedirect(request.getContextPath() + "/admin/clubs/show");
        } else if ("/admin/clubs/update".equals(path)) {
            int clubId = Integer.parseInt(request.getParameter("clubId"));
            String clubName = request.getParameter("clubName");
            int gerantId = Integer.parseInt(request.getParameter("gerantId"));

            Club club = clubDAO.getClubById(clubId);
            int previousGerantId = club.getIdUser();

            club.setNom(clubName);
            club.setIdUser(gerantId);
            clubDAO.updateClub(club);

            if (previousGerantId != gerantId) {
                User previousGerant = userDAO.getUserById(previousGerantId);
                User newGerant = userDAO.getUserById(gerantId);

                if (previousGerant != null && !"admin".equals(previousGerant.getRole())) {
                    previousGerant.setRole("membre");
                    userDAO.updateUserRole(previousGerant);
                }
                if (newGerant != null && !"admin".equals(newGerant.getRole())) {
                    newGerant.setRole("gerant");
                    userDAO.updateUserRole(newGerant);
                }
            }

            response.sendRedirect(request.getContextPath() + "/admin/clubs/show");
        } else if ("/admin/events/add".equals(path)) {
            String eventName = request.getParameter("eventName");
            String eventDescription = request.getParameter("eventDescription");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            int clubId = Integer.parseInt(request.getParameter("clubId"));

            Event event = new Event();
            event.setTitre(eventName);
            event.setDescription(eventDescription);
            event.setDateDebut(startDate);
            event.setDateFin(endDate);
            event.setId_club(clubId);

            eventDAO.createEvent(event);
            response.sendRedirect(request.getContextPath() + "/admin/events/show");
        } else if ("/admin/events/update".equals(path)) {
            int eventId = Integer.parseInt(request.getParameter("eventId"));
            String eventName = request.getParameter("eventName");
            String eventDescription = request.getParameter("eventDescription");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            int clubId = Integer.parseInt(request.getParameter("clubId"));

            Event event = eventDAO.selectEventById(eventId);
            event.setTitre(eventName);
            event.setDescription(eventDescription);
            event.setDateDebut(startDate);
            event.setDateFin(endDate);
            event.setId_club(clubId);

            eventDAO.updateEvent(event);
            response.sendRedirect(request.getContextPath() + "/admin/events/show");
        }
    }
}
