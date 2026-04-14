<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Your CSS -->
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Sign In</h2>

        <%
            String error = request.getParameter("error");
            String registered = request.getParameter("registered");

            if ("1".equals(error)) {
        %>
            <p class="error">Invalid email or password</p>
        <%
            }
            if ("1".equals(registered)) {
        %>
            <p class="success">Registration successful. Please login.</p>
        <%
            }
        %>

        <form action="login" method="post">
            <label>Email</label>
            <input type="text" name="email" required>

            <label>Password</label>
            <input type="password" name="password" required>

            <button type="submit">Sign In</button>
        </form>

        <p>New user? <a href="signup.jsp">Create account</a></p>
    </div>
</body>
</html>