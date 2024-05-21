package controllers;

import dao.EventDAO;
import dao.EventDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private final String HOME_PAGE = "/WEB-INF/views/protected/home.jsp";
    private EventDAO eventDAO = new EventDAOImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("events", eventDAO.selectTopEventsByMembersCount(3));
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }
}
