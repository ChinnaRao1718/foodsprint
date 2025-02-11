package com.foodsprint.servlet;

import java.io.IOException;
import java.time.Instant;
import java.time.Duration;

import com.foodsprint.dao.UserDAO;
import com.foodsprint.daoimplementation.UserDAOImpl;
import com.foodsprint.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        HttpSession session = request.getSession();
	        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
	        Instant firstFailedAttemptTime = (Instant) session.getAttribute("firstFailedAttemptTime");

	        if (failedAttempts == null) {
	            failedAttempts = 0;
	        }

	        // Unblock user after 5 minutes
	        if (failedAttempts >= 4) {
	            if (firstFailedAttemptTime != null) {
	                Instant now = Instant.now();
	                Duration duration = Duration.between(firstFailedAttemptTime, now);

	                if (duration.toMinutes() >= 5) { // Reset attempts after 5 minutes
	                    session.removeAttribute("failedAttempts");
	                    session.removeAttribute("firstFailedAttemptTime");
	                    failedAttempts = 0;
	                } else {
	                    response.sendRedirect("blocked.html");
	                    return;
	                }
	            } else {
	                response.sendRedirect("blocked.html");
	                return;
	            }
	        }

	        UserDAO userDAO = new UserDAOImpl();
	        User authenticatedUser = null;

	        // Check if user exists and password matches
	        for (User user : userDAO.getAllUsers()) {
	            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
	                authenticatedUser = user;
	                break;
	            }
	            
	        }

	        if (authenticatedUser != null) {
	            session.setAttribute("user", authenticatedUser);
	            session.removeAttribute("failedAttempts"); // Reset failed attempts on success
	            session.removeAttribute("firstFailedAttemptTime");
	            response.sendRedirect("HomeServlet"); // Redirect to home or dashboard page
	        } else {
	            failedAttempts++;

	            // Store the first failed attempt time if not already set
	            if (failedAttempts == 1) {
	                session.setAttribute("firstFailedAttemptTime", Instant.now());
	            }

	            session.setAttribute("failedAttempts", failedAttempts);
	            request.setAttribute("errorMessage", "Invalid login details");

	            if (failedAttempts >= 4) {
	                response.sendRedirect("blocked.html");
	            } else {
	                request.setAttribute("failedAttempts", failedAttempts);
	                request.getRequestDispatcher("/login.jsp").forward(request, response);
	            }
	        }
	    }

}
