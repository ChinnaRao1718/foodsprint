package com.foodsprint.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.foodsprint.daoimplementation.UserDAOImpl;
import com.foodsprint.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = "USER";  // Default role
        Date createdDate = Date.valueOf(LocalDate.now());

        // Create User object
        User user = new User();
        user.setName(name);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);
        user.setCreatedDate(createdDate);

        // Save user to database
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean isRegistered = userDAO.addUser(user);

        if (isRegistered) {
            response.sendRedirect("registration-success.html");
        } else {
            response.sendRedirect("registration-failure.jsp");
        }
    }

}
