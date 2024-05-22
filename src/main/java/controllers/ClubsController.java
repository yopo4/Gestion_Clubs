package controllers;

import dao.ClubDAO;
import dao.ClubDAOImp;
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

@WebServlet(name = "clubs", value = "/clubs")
public class ClubsController extends HttpServlet {

    private final String CLUBS_PAGE = "/WEB-INF/views/protected/clubs.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Club> clubs = clubDAO.getAllClubs();
        Map<Integer, String> gerantNames = new HashMap<>();
        Map<Integer, Integer> clubMembersCount = new HashMap<>();

        for (Club club : clubs) {
            gerantNames.put(club.getIdUser(), clubDAO.getGerantNameById(club.getIdUser()));
            clubMembersCount.put(club.getIdClub(), clubDAO.getMembersCountByClubId(club.getIdClub()));
        }
        request.setAttribute("clubs", clubs);
        request.setAttribute("clubMembersCount", clubMembersCount);
        request.setAttribute("gerantNames", gerantNames);
        request.getRequestDispatcher(CLUBS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
