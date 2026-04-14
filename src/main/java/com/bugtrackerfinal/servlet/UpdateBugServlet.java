package com.bugtrackerfinal.servlet;

import java.io.IOException;

import com.bugtrackerfinal.dao.BugDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateBug")
public class UpdateBugServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BugDAO bugDAO = new BugDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bugId = request.getParameter("bugId");
        String priority = request.getParameter("priority");
        String status = request.getParameter("status");
        String assignedTo = request.getParameter("assignedTo");
        String deadline = request.getParameter("deadline");
        String comments = request.getParameter("comments");

        bugDAO.updateBug(bugId, priority, status, assignedTo, deadline, comments);

        response.sendRedirect("bug-list");
    }
}