package controllers;

import com.mysql.cj.Session;
import dao.ClubDAO;
import dao.ClubDAOImp;
import dao.MembreDAO;
import dao.MembreDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Club;
import models.Membre;
import models.User;

import java.io.IOException;

@WebServlet(name = "club", value = "/club")
public class ClubController extends HttpServlet {

    private final String CLUB_PAGE = "/WEB-INF/views/protected/club.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();
    private MembreDAO membreDAO = new MembreDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int clubId = Integer.parseInt(request.getParameter("id_club"));
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("/Gestion_Clubs/login");
            return;
        }
        Club club = clubDAO.getClubById(clubId);
        Membre membre = membreDAO.getMembreByUserId(user.getId_user());
        boolean isPartOfClub = clubDAO.userIsMemberOfClub(user, club);
        boolean isGerantOfClub = clubDAO.isGerantOfClub(membre,club);
        String gerantNames = membreDAO.getClubManager(club) == null ? "No manager assigned yet":membreDAO.getClubManager(club).getNom();
        int clubMembersCount = clubDAO.getMembersCountByClubId(club.getIdClub());
        int clubRequestsCount = clubDAO.getNumberOfRequests(club);


        request.setAttribute("club", club);
        request.setAttribute("clubMembersCount", clubMembersCount);
        request.setAttribute("clubRequestsCount", clubRequestsCount);
        request.setAttribute("gerantNames", gerantNames);
        request.setAttribute("isPartOfClub",isPartOfClub);
        request.setAttribute("isGerantOfClub",isGerantOfClub);
        request.getRequestDispatcher(CLUB_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
