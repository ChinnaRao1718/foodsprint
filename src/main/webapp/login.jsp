<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
    <!-- Add Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="login-container">
        <h2>Welcome Back!</h2>
        <p class="subtitle">Please log in to your account</p>

        <!-- Display error message if any -->
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <!-- Display remaining attempts only if attempts left are less than 4 -->
	   <%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
    if (failedAttempts != null && failedAttempts < 4) {
%>
    <p class="attempts-message">Attempts left: <%= 4 - failedAttempts %></p>
<%
    }
%>

        <form action="login" method="post">
            <div class="input-group">
                <label for="email">Email</label>
                <div class="input-with-icon">
                    <i class="fas fa-envelope icon"></i>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <div class="input-with-icon">
                    <i class="fas fa-lock icon"></i>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                    <span class="eye-icon" onclick="togglePassword()">👁</span>
                </div>
            </div>
            <button type="submit">Login</button>
        </form>

        <p class="signup-link">Don't have an account? <a href="register.jsp">Sign up</a></p>
    </div>

    <script>
        function togglePassword() {
            let passwordField = document.getElementById("password");
            if (passwordField.type === "password") {
                passwordField.type = "text";
            } else {
                passwordField.type = "password";
            }
        }
    </script>
</body>
</html>