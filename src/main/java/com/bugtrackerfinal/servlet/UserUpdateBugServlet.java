package com.bugtrackerfinal.servlet;

import java.io.IOException;

import com.bugtrackerfinal.dao.BugDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userUpdateBug")
public class UserUpdateBugServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BugDAO bugDAO = new BugDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bugId = request.getParameter("bugId");
        String status = request.getParameter("status");

        bugDAO.updateBugStatusOnly(bugId, status);

        response.sendRedirect("bug-list?view=assigned");
    }
}