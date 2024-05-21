package controllers;

import dao.ClubDAO;
import dao.EventDAO;
import dao.EventDAOImp;
import dao.ClubDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private final String HOME_PAGE = "/WEB-INF/views/home.jsp";
    private EventDAO eventDAO = new EventDAOImp();
    private ClubDAO clubDAO = new ClubDAOImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int top = 3;
        Map<Integer, String> gerantNames = new HashMap<>();
        for (int i = 0; i < top; i++) {

        }

        req.setAttribute("clubs", clubDAO.getTopClubs(top));
        req.setAttribute("events", eventDAO.selectTopEventsByMembersCount(top));
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }
}
