package com.bugtrackerfinal.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bugtrackerfinal.dao.BugDAO;
import com.bugtrackerfinal.model.Bug;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bug-list")
public class BugListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BugDAO bugDAO = new BugDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String role = (String) request.getSession().getAttribute("userRole");
        String email = (String) request.getSession().getAttribute("userEmail");
        String search = request.getParameter("search");
        String view = request.getParameter("view");
        String statusFilter = request.getParameter("statusFilter");

        List<Bug> bugList = new ArrayList<>();

        if ("admin".equalsIgnoreCase(role)) {

            if ("assignedAdmin".equalsIgnoreCase(view)) {
                if (statusFilter != null && !statusFilter.isBlank() && !"All".equalsIgnoreCase(statusFilter)) {
                    bugList = bugDAO.getAssignedBugsByStatus(statusFilter);
                } else {
                    bugList = bugDAO.getAssignedBugs();
                }
            } else {
                bugList = bugDAO.getUnassignedBugs();
            }

        } else {

            if ("assigned".equalsIgnoreCase(view)) {
                bugList = bugDAO.getBugsAssignedTo(email);
            } else {
                bugList = bugDAO.getBugsByReporter(email);
            }
        }

        if (search != null && !search.isBlank()) {
            String keyword = search.toLowerCase();
            List<Bug> filteredList = new ArrayList<>();

            for (Bug bug : bugList) {
                String title = bug.getTitle() != null ? bug.getTitle().toLowerCase() : "";
                String module = bug.getModule() != null ? bug.getModule().toLowerCase() : "";

                if (title.contains(keyword) || module.contains(keyword)) {
                    filteredList.add(bug);
                }
            }

            bugList = filteredList;
        }

        request.setAttribute("bugList", bugList);
        request.setAttribute("view", view);
        request.setAttribute("statusFilter", statusFilter);
        request.getRequestDispatcher("bug-list.jsp").forward(request, response);
    }
}