<%@ page import="java.util.List" %>
<%@ page import="com.bugtrackerfinal.model.Bug" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bug List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
</head>
<body>

<%
    String email = (String) session.getAttribute("userEmail");
    String role = (String) session.getAttribute("userRole");
    String view = (String) request.getAttribute("view");
    String statusFilter = (String) request.getAttribute("statusFilter");

    if (email == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Bug> bugList = (List<Bug>) request.getAttribute("bugList");
%>

<div class="container wide-container">

    <h2 class="text-center mb-4">
        <%
            if ("assignedAdmin".equalsIgnoreCase(view)) {
                out.print("Assigned Tasks");
            } else if ("assigned".equalsIgnoreCase(view)) {
                out.print("Assigned Bugs");
            } else if ("reported".equalsIgnoreCase(view)) {
                out.print("My Reported Bugs");
            } else {
                out.print("Manage All Bugs");
            }
        %>
    </h2>

    <form method="get" action="bug-list" class="row g-2 mb-3">
        <input type="hidden" name="view" value="<%= view != null ? view : "" %>">

        <div class="col-md-7">
            <input type="text" name="search" class="form-control" placeholder="Search by title/module">
        </div>

        <%
            if ("admin".equalsIgnoreCase(role) && "assignedAdmin".equalsIgnoreCase(view)) {
        %>
        <div class="col-md-3">
            <select name="statusFilter" class="form-select">
                <option value="All" <%= (statusFilter == null || "All".equalsIgnoreCase(statusFilter)) ? "selected" : "" %>>All</option>
                <option value="In Progress" <%= "In Progress".equalsIgnoreCase(statusFilter) ? "selected" : "" %>>In Progress</option>
                <option value="Closed" <%= "Closed".equalsIgnoreCase(statusFilter) ? "selected" : "" %>>Closed</option>
            </select>
        </div>
        <%
            }
        %>

        <div class="col-md-2">
            <button type="submit" class="btn btn-dark w-100">Apply</button>
        </div>
    </form>

    <table class="table table-striped table-bordered table-hover mt-4 align-middle">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Module</th>
            <th>Priority</th>
            <th>Status</th>
            <th>Reported By</th>
            <th>Assigned To</th>
            <th>Deadline</th>
            <th>Comments</th>

            <%
                if ("admin".equalsIgnoreCase(role) && "unassigned".equalsIgnoreCase(view)) {
            %>
                <th>Admin Update</th>
            <%
                } else if ("admin".equalsIgnoreCase(role) && (view == null || view.isBlank())) {
            %>
                <th>Admin Update</th>
            <%
                } else if ("assigned".equalsIgnoreCase(view)) {
            %>
                <th>User Action</th>
            <%
                }
            %>
        </tr>

        <%
            if (bugList != null && !bugList.isEmpty()) {
                for (Bug bug : bugList) {
        %>
        <tr>
            <td><%= bug.getBugId() %></td>
            <td><%= bug.getTitle() %></td>
            <td><%= bug.getModule() %></td>
            <td><%= bug.getPriority() %></td>
            <td><%= bug.getStatus() %></td>
            <td><%= bug.getReportedByName() %></td>
            <td><%= bug.getAssignedTo() %></td>
            <td><%= bug.getDeadline() %></td>
            <td><%= bug.getAdminComments() %></td>

            <%
                if ("admin".equalsIgnoreCase(role) &&
                    ("unassigned".equalsIgnoreCase(view) || view == null || view.isBlank())) {
            %>
            <td>
                <form action="updateBug" method="post">
                    <input type="hidden" name="bugId" value="<%= bug.getBugId() %>">

                    <input type="text" name="assignedTo" class="form-control mb-1" placeholder="Assign email">

                    <input type="date" name="deadline" class="form-control mb-1">

                    <input type="text" name="comments" class="form-control mb-1" placeholder="Comment">

                    <select name="priority" class="form-select mb-1">
                        <option value="">Priority</option>
                        <option value="Low">Low</option>
                        <option value="Medium">Medium</option>
                        <option value="High">High</option>
                    </select>

                    <select name="status" class="form-select mb-1">
                        <option value="">Status</option>
                        <option value="Open">Open</option>
                        <option value="In Progress">In Progress</option>
                        <option value="Closed">Closed</option>
                    </select>

                    <button type="submit" class="btn btn-sm btn-primary w-100">Update</button>
                </form>
            </td>
            <%
                } else if ("assigned".equalsIgnoreCase(view)) {

                    if ("Closed".equalsIgnoreCase(bug.getStatus())) {
            %>
            <td>
                <span class="badge bg-success">Closed</span>
            </td>
            <%
                    } else {
            %>
            <td>
                <form action="userUpdateBug" method="post">
                    <input type="hidden" name="bugId" value="<%= bug.getBugId() %>">

                    <select name="status" class="form-select mb-1">
                        <option value="">Update Status</option>
                        <option value="In Progress"
                            <%= "In Progress".equalsIgnoreCase(bug.getStatus()) ? "selected" : "" %>>
                            In Progress
                        </option>
                        <option value="Closed">Closed</option>
                    </select>

                    <button type="submit" class="btn btn-sm btn-success w-100">Save</button>
                </form>
            </td>
            <%
                    }
                }
            %>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="10" class="text-center">No bugs found</td>
        </tr>
        <%
            }
        %>
    </table>

    <div class="text-center mt-3">
        <a href="dashboard.jsp" class="btn btn-secondary">Back</a>
    </div>
</div>

</body>
</html>