package controllers;

import dao.EventDAOImp;
import dao.MembreDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Event;
import models.Membre;
import models.User;

import java.io.IOException;

@WebServlet(name = "AttendEventController", value = "/AttendEventController")
public class AttendEventController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EventDAOImp eventDAOImp = new EventDAOImp();
        MembreDAOImp membreDAOImp = new MembreDAOImp();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            //change sending to home to sign up
            response.sendRedirect(request.getContextPath() + "/home");
        }else{
            //add the member to the ones attending
            int idEvent = (int) request.getAttribute("id");
            Membre membre = membreDAOImp.getMembreByUserId(user.getId_user());
            Event event = eventDAOImp.selectEventById(idEvent);
            eventDAOImp.addMembreToEvent(event, membre);
            response.sendRedirect(request.getContextPath() + "/event?"+idEvent);
        }
    }
}
