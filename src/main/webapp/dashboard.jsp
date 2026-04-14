<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
</head>
<body>

<%
    String email = (String) session.getAttribute("userEmail");
    String role = (String) session.getAttribute("userRole");
    String name = (String) session.getAttribute("userName");

    if (email == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<div class="container card-box text-center">
    <h1 class="mb-3">Dashboard</h1>

    <p><strong>Welcome:</strong> <%= name != null ? name : email %></p>
    <p><strong>Role:</strong> <%= role %></p>

    <div class="btn-group">

        <% if ("admin".equalsIgnoreCase(role)) { %>

            <a href="bug-list?view=unassigned" class="btn btn-primary dashboard-card">
                Manage All Bugs
            </a>

            <a href="bug-list?view=assignedAdmin" class="btn btn-success dashboard-card">
                Assigned Tasks
            </a>

        <% } else { %>

            <a href="report-bug.jsp" class="btn btn-primary dashboard-card">
                Report Bug
            </a>

            <a href="bug-list?view=reported" class="btn btn-success dashboard-card">
                My Reported Bugs
            </a>

            <a href="bug-list?view=assigned" class="btn btn-warning dashboard-card">
                Assigned Bugs
            </a>

        <% } %>

        <a href="logout" class="btn btn-danger dashboard-card">
            Logout
        </a>

    </div>
</div>

</body>
</html>