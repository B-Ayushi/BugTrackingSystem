<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Report Bug</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Your CSS -->
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <%
        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <div class="container">
        <h2>Report a Bug</h2>

        <%
            String error = request.getParameter("error");
            if ("1".equals(error)) {
        %>
            <p class="error">Failed to submit bug</p>
        <%
            }
        %>

        <form action="reportBug" method="post">
            <label>Bug Title</label>
            <input type="text" name="title" required>

            <label>Description</label>
            <textarea name="description" required></textarea>

            <label>Module</label>
            <select name="module" required>
                <option value="">Select Module</option>
                <option value="Authentication">Authentication</option>
                <option value="Dashboard">Dashboard</option>
                <option value="Bug Reporting">Bug Reporting</option>
                <option value="User Management">User Management</option>
                <option value="Database">Database</option>
            </select>

            <button type="submit">Submit Bug</button>
        </form>

        <div class="btn-group">
            <a href="dashboard.jsp" class="btn secondary">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>