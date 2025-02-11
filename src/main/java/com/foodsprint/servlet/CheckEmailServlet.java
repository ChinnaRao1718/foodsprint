package com.foodsprint.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.foodsprint.daoimplementation.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/**
 * Servlet implementation class CheckEmailServlet
 */
@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
	   private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();

	        String email = request.getParameter("email");
	        UserDAOImpl userDAO = new UserDAOImpl();

	        if (userDAO.emailExists(email)) {
	            out.print("exists");
	        } else {
	            out.print("available");
	        }
	        out.flush();
	    }

}
