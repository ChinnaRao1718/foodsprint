package com.foodsprint.servlet;

import java.io.IOException;

import com.foodsprint.dao.MenuDao;
import com.foodsprint.daoimplementation.MenuDAOImpl;
import com.foodsprint.model.Cart;
import com.foodsprint.model.CartItem;
import com.foodsprint.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Cart cart = (Cart) session.getAttribute("cart");
	        
	        // Handle NullPointerException
	        int restaurentIdParam = Integer.parseInt(request.getParameter("restaurentId"));
	        Integer currentRestaurentId = (Integer) session.getAttribute("restaurentId");

	        if (cart == null || currentRestaurentId == null || restaurentIdParam != currentRestaurentId) {
	            cart = new Cart();
	            session.setAttribute("cart", cart);
	            session.setAttribute("restaurentId", restaurentIdParam);
	        }

	        String action = request.getParameter("action");

	        if (action != null) {
	            if (action.equals("add")) {
	                addToCart(request, cart);
	            } else if (action.equals("update")) {
	                updateCartItem(request, cart);
	            } else if (action.equals("remove")) {
	                removeItemFromCart(request, cart);
	            }
	        }

	        response.sendRedirect("cart.jsp?restaurentId=" + restaurentIdParam);
	    }

	    // ✅ Updated GET method to forward request to cart.jsp instead of using out.println()
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(false); // Don't create a new session
	        
	        if (session == null || session.getAttribute("cart") == null) {
	            request.setAttribute("cartEmpty", true);
	        } else {
	            request.setAttribute("cartEmpty", false);
	        }
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
	        dispatcher.forward(request, response);
	    }

	    private void addToCart(HttpServletRequest request, Cart cart) {
	        int itemId = Integer.parseInt(request.getParameter("itemId"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));

	        MenuDao menuDao = new MenuDAOImpl();
	        Menu menuItem = menuDao.getMenu(itemId);

	        if (menuItem != null) {
	            CartItem item = new CartItem(
	                menuItem.getMenuId(),
	                menuItem.getItemName(),
	                menuItem.getPrice(),
	                quantity);
	            cart.addCartItem(item);
	        }
	    }

	    private void updateCartItem(HttpServletRequest request, Cart cart) {
	        int itemId = Integer.parseInt(request.getParameter("itemId"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));
	        cart.updateCartItem(itemId, quantity);
	    }

	    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
	        int itemId = Integer.parseInt(request.getParameter("itemId"));
	        cart.removeCart(itemId);
	    }

}
