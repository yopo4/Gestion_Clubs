package controllers;

import dao.ClubDAO;
import dao.ClubDAOImp;
import dao.MembreDAO;
import dao.MembreDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Club;
import models.Membre;
import models.User;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "members", value = {"/members", "/requests", "/members/delete"})
public class MemberController extends HttpServlet {

    private final String MEMBERS_PAGE = "/WEB-INF/views/protected/members.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();
    private MembreDAO membreDAO = new MembreDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int clubId = Integer.parseInt(request.getParameter("id_club"));
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/Gestion_Clubs/login");
            return;
        }
        Club club = clubDAO.getClubById(clubId);
        Membre membre = membreDAO.getMembreByUserId(user.getId_user());
        boolean isGerantOfClub = clubDAO.isGerantOfClub(membre, club);
        if (!isGerantOfClub) {
            response.sendRedirect("/Gestion_Clubs/home");
            return;
        }
        List<Membre> membres = null;
        if (request.getServletPath().equals("/members")) {
            request.setAttribute("officials", true);
            membres = membreDAO.getMembersOfClub(club, true);
        } else {
            request.setAttribute("officials", false);
            membres = membreDAO.getMembersOfClub(club, false);
        }
        request.setAttribute("club", club);
        request.setAttribute("members", membres);
        request.getRequestDispatcher(MEMBERS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/members/delete")) {
            int id_membre = (int) request.getAttribute("member");
            int id_club = (int) request.getAttribute("club");
            Membre membre = membreDAO.getMembreById(id_membre);
            Club club = clubDAO.getClubById(id_club);
            System.out.println(id_membre + " " + id_club);
            membreDAO.deleteMemberFromClub(membre,club);
            request.getRequestDispatcher(MEMBERS_PAGE).forward(request,response);
        }
    }
}
