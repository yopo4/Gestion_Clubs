package controllers;

import dao.ClubDAO;
import dao.ClubDAOImp;
import dao.MembreDAO;
import dao.MembreDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Club;
import models.Event;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "clubs", value = {"/clubs","/myClubs"})
public class ClubsController extends HttpServlet {

    private final String CLUBS_PAGE = "/WEB-INF/views/protected/clubs.jsp";

    private ClubDAO clubDAO = new ClubDAOImp();
    private MembreDAO membreDAO = new MembreDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Club> clubs = null;
        if(request.getServletPath().equals("/clubs")){
            clubs = clubDAO.getAllClubs();
        } else if (request.getServletPath().equals("/myClubs")) {
            if(user == null){
                request.getRequestDispatcher("/WEB-INF/views/authentification/login.jsp").forward(request, response);
            }
            System.out.println(user);
            clubs = clubDAO.getClubsOfMember(user.getId_membre());
            System.out.println(clubs);
        }
        Map<Integer, String> gerantNames = new HashMap<>();
        Map<Integer, Integer> clubMembersCount = new HashMap<>();

        for (Club club : clubs) {
            String managerName = membreDAO.getClubManager(club) == null ? "No manager Assigned yet" : membreDAO.getClubManager(club).getNom();
            gerantNames.put(club.getIdClub(),managerName);
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
