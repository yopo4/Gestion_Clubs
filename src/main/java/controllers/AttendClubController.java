package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;

import java.io.IOException;

@WebServlet(name = "AttendClubController", value = "/AttendClubController")
public class AttendClubController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            //change sending to home to sign up
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            //add the member to the ones attending

        }
    }
}
