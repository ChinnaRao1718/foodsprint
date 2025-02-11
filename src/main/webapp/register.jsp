<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.foodsprint.daoimplementation.UserDAOImpl" %> <!-- Import your DAO class -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <link rel="stylesheet" href="reg.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2>Create Account</h2>

            <form id="registrationForm" action="Registration" method="post">
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" required onblur="checkEmailExistence()">
                    <p id="emailWarning" style="color: red; display: none;">Email already exists!</p>
                </div>

                <div class="form-group password-group">
                    <label for="password">Password</label>
                    <div class="password-input">
                        <input type="password" id="password" name="password" required>
                        <button type="button" class="toggle-password" onclick="togglePassword()">
                            <i id="eyeIcon" class="icon">👁️</i>
                        </button>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>

                <div class="form-group">
                    <label for="address">Address</label>
                    <textarea id="address" name="address" rows="3" required></textarea>
                </div>

                <button type="submit" class="submit-btn">Register</button>
            </form>

            <p class="signin-link">
                Already have an account? <a href="login.jsp">Sign in</a>
            </p>
        </div>
    </div>

    <script>
        function togglePassword() {
            const passwordInput = document.getElementById('password');
            const eyeIcon = document.getElementById('eyeIcon');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                eyeIcon.textContent = '🔒';
            } else {
                passwordInput.type = 'password';
                eyeIcon.textContent = '👁️';
            }
        }

        function checkEmailExistence() {
            const email = document.getElementById("email").value;
            const submitButton = document.querySelector(".submit-btn");

            if (email.length === 0) return; // If email is empty, do nothing

            $.ajax({
                url: "CheckEmailServlet",
                type: "POST",
                data: { email: email },
                success: function(response) {
                    if (response.trim() === "exists") {
                        $("#emailWarning").show();
                        submitButton.disabled = true; // Disable the submit button
                    } else {
                        $("#emailWarning").hide();
                        submitButton.disabled = false; // Enable the submit button
                    }
                }
            });
        }

    </script>
</body>
</html>
