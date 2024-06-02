package controllers;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Club;
import models.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private final String HOME_PAGE = "/WEB-INF/views/home.jsp";
    private EventDAO eventDAO = new EventDAOImp();
    private ClubDAO clubDAO = new ClubDAOImp();
    private MembreDAO membreDAO = new MembreDAOImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int top = 3;
        Map<Integer, String> gerantNames = new HashMap<>();
        Map<Integer, Integer> clubMembersCount = new HashMap<>();
        Map<Integer, Integer> eventMembersCount = new HashMap<>();

        for (Club club : clubDAO.getTopClubs(top)) {
            String managerName = membreDAO.getClubManager(club) == null ? "No manager Assigned yet" : membreDAO.getClubManager(club).getNom();
            gerantNames.put(club.getIdClub(),managerName);
            clubMembersCount.put(club.getIdClub(), clubDAO.getMembersCountByClubId(club.getIdClub()));
        }

        for (Event event : eventDAO.selectTopEventsByMembersCount(top)) {
            eventMembersCount.put(event.getId_evenement(), eventDAO.getMembersCountByEventId(event.getId_evenement()));
        }

//        for (Map.Entry<Integer, Integer> entry : membersCount.entrySet()) {
//            int key = entry.getKey();
//            int value = entry.getValue();
//            System.out.println("Club ID: " + key + ", Members Count: " + value);
//        }

        req.setAttribute("clubs", clubDAO.getTopClubs(top));
        req.setAttribute("gerantNames", gerantNames);
        req.setAttribute("clubMembersCount", clubMembersCount);
        req.setAttribute("eventMembersCount", eventMembersCount);
        req.setAttribute("events", eventDAO.selectTopEventsByMembersCount(top));
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }
}
