package controllers;

import dao.ClubDAO;
import dao.ClubDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Club;
import models.Event;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "club", value = "/club")
public class ClubController extends HttpServlet {

    private final String CLUB_PAGE = "/WEB-INF/views/protected/club.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("id_club"));
        String gerantNames = clubDAO.getGerantNameById(clubDAO.getClubById(clubId).getIdUser());
        int clubMembersCount = clubDAO.getMembersCountByClubId(clubDAO.getClubById(clubId).getIdUser());

//        int clubId = 1;
        Club club = clubDAO.getClubById(clubId);
        request.setAttribute("club", club);
        request.setAttribute("clubMembersCount", clubMembersCount);
        request.setAttribute("gerantNames", gerantNames);
        request.getRequestDispatcher(CLUB_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}