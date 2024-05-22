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
            response.sendRedirect(request.getContextPath() + "/login");
        }else{
            int idEvent = Integer.parseInt(request.getParameter("id"));
//            int idEvent = 1;
            System.out.println(idEvent);
            Membre membre = membreDAOImp.getMembreByUserId(user.getId_user());
            Event event = eventDAOImp.selectEventById(idEvent);
            eventDAOImp.addMembreToEvent(event, membre);
            response.sendRedirect(request.getContextPath() + "/event?id_event="+idEvent);
        }
    }
}
