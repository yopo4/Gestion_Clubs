package controllers;

import dao.MembreDAO;
import dao.MembreDAOImp;
import dao.UserDAO;
import dao.UserDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

@WebServlet(name = "auth", urlPatterns = {"/login", "/logout"})
public class AuthentificationController extends HttpServlet {
    private final String LOGIN_PAGE = "/WEB-INF/views/authentification/login.jsp";
    private final String HOME_PAGE = "/WEB-INF/views/home.jsp";
    private final String ADMIN_HOME_PAGE = "/WEB-INF/views/admin/home.jsp";
    private UserDAO userDAO = new UserDAOImp();
    private MembreDAO membreDAO = new MembreDAOImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/logout")) {
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/login")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User user = userDAO.findByEmailAndPassword(email, password);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                if (user.getRole()) {
                    resp.sendRedirect(req.getContextPath() + "/admin/home");
                } else {
                    //check if the user is a gerant
                    boolean isGerant =  membreDAO.isGerant(membreDAO.getMembreByUserId(user.getId_user()));
                    if(isGerant){
                        session.setAttribute("role","gerant");
                    }else{
                        session.setAttribute("role","membre");
                    }
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                req.setAttribute("errorMessage", "Invalid email or password");
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
            }
        }
    }
}
