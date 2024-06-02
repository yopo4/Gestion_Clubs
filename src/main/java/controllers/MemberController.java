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


@WebServlet(name = "members", value = {"/members", "/requests", "/members/delete","/requests/accept","/requests/refuse"})
public class MemberController extends HttpServlet {

    private final String MEMBERS_PAGE = "/WEB-INF/views/protected/members.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();
    private MembreDAO membreDAO = new MembreDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/Gestion_Clubs/login");
            return;
        }
        int clubId = Integer.parseInt(request.getParameter("id_club"));
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
        //don't send the manager in the list of members
        Membre manager = membreDAO.getMembreByUserId(user.getId_user());
        System.out.println("before: " + membres);
        membres.remove(manager);
        System.out.println("after: " + membres);
        request.setAttribute("club", club);
        request.setAttribute("members", membres);
        request.getRequestDispatcher(MEMBERS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_membre = Integer.parseInt(request.getParameter("member"));
        int id_club = Integer.parseInt(request.getParameter("club"));
        Membre membre = membreDAO.getMembreById(id_membre);
        Club club = clubDAO.getClubById(id_club);
        if (request.getServletPath().equals("/members/delete")) {
            membreDAO.deleteMemberFromClub(membre, club);
            request.getRequestDispatcher(MEMBERS_PAGE).forward(request, response);
        }else if(request.getServletPath().equals("/requests/accept")){
            membreDAO.updateAcceptation(membre,club,true);
            request.getRequestDispatcher(MEMBERS_PAGE).forward(request, response);
        }else if(request.getServletPath().equals("/requests/refuse")){
            membreDAO.deleteMemberFromClub(membre,club);
            request.getRequestDispatcher(MEMBERS_PAGE).forward(request, response);
        }

    }
}
