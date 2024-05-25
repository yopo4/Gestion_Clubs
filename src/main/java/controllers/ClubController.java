package controllers;

import dao.ClubDAO;
import dao.ClubDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Club;

import java.io.IOException;

@WebServlet(name = "club", value = "/club")
public class ClubController extends HttpServlet {

    private final String CLUB_PAGE = "/WEB-INF/views/protected/club.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("id_club"));
        Club club = clubDAO.getClubById(clubId);
        String gerantNames = clubDAO.getGerantNameById(club.getIdUser());
        int clubMembersCount = clubDAO.getMembersCountByClubId(club.getIdClub());
        System.out.println("test "+clubId);
        System.out.println(clubMembersCount);
        System.out.println(clubDAO.getClubById(clubId).toString());

        request.setAttribute("club", club);
        request.setAttribute("clubMembersCount", clubMembersCount);
        request.setAttribute("gerantNames", gerantNames);
        request.getRequestDispatcher(CLUB_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
