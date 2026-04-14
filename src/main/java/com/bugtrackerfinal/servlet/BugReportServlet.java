package com.bugtrackerfinal.servlet;

import java.io.IOException;
import java.time.LocalDate;

import com.bugtrackerfinal.dao.BugDAO;
import com.bugtrackerfinal.model.Bug;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reportBug")
public class BugReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BugDAO bugDAO = new BugDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String module = request.getParameter("module");
        String reportedBy = (String) request.getSession().getAttribute("userEmail");
        String reportedByName = (String) request.getSession().getAttribute("userName");

        Bug bug = new Bug();
        bug.setTitle(title);
        bug.setDescription(description);
        bug.setModule(module);
        bug.setPriority("Medium");
        bug.setStatus("Open");
        bug.setReportedBy(reportedBy);
        bug.setReportedByName(reportedByName);
        bug.setAssignedTo("");
        bug.setDeadline("");
        bug.setAdminComments("");
        
        
        
        bug.setCreatedDate(LocalDate.now().toString());

        boolean success = bugDAO.addBug(bug);

        if (success) {
            response.sendRedirect("bug-list");
        } else {
            response.sendRedirect("report-bug.jsp?error=1");
        }
    }
}