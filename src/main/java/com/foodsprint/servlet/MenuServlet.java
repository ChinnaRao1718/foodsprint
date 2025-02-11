package com.foodsprint.servlet;

import java.io.IOException;
import java.util.List;

import com.foodsprint.daoimplementation.MenuDAOImpl;
import com.foodsprint.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int rid = Integer.parseInt(request.getParameter("restaurentId"));
		MenuDAOImpl daoImpl = new MenuDAOImpl();
		List<Menu> menuList = daoImpl.getAllMenusByResataurent(rid);
		
		request.setAttribute("menuList", menuList);
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);
		
	}
	
	
}
