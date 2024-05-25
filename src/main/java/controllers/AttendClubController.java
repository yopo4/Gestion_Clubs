package controllers;

import dao.ClubDAOImp;
import dao.EventDAOImp;
import dao.MembreDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Club;
import models.Event;
import models.Membre;
import models.User;

import java.io.IOException;

@WebServlet(name = "AttendClubController", value = "/AttendClubController")
public class AttendClubController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        MembreDAOImp membreDAOImp = new MembreDAOImp();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //change sending to home to sign up
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            //add the member to the ones attending
            int idClub = Integer.parseInt(request.getParameter("id"));
//            int idEvent = 1;
            System.out.println(idClub);
            Membre membre = membreDAOImp.getMembreByUserId(user.getId_user());
            System.out.println(membre);
            Club club = clubDAOImp.getClubById(idClub);
            clubDAOImp.addMembreToClub(club, membre);
            response.sendRedirect(request.getContextPath() + "/club?id_club=" + idClub);
        }
    }
}
