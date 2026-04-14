package com.bugtrackerfinal.servlet;

import java.io.IOException;

import com.bugtrackerfinal.dao.UserDAO;
import com.bugtrackerfinal.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(name, email, password, role);

        boolean success = userDAO.registerUser(user);

        if (success) {
            response.sendRedirect("login.jsp?registered=1");
        } else {
            response.sendRedirect("signup.jsp?error=1");
        }
    }
}