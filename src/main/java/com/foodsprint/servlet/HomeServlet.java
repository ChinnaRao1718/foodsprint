package com.foodsprint.servlet;

import java.io.IOException;
import java.util.List;

import com.foodsprint.daoimplementation.RestaurentDAOImpl;
import com.foodsprint.model.Restaurent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RestaurentDAOImpl restaurentDAO = new RestaurentDAOImpl();
		
		List<Restaurent> allRestaurents = restaurentDAO.getAllRestaurents();
		
		request.setAttribute("restaurents" , allRestaurents);
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
}
