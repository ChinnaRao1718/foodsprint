<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.foodsprint.model.Cart" %>
<%@ page import="com.foodsprint.model.CartItem" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="cart.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <h1>Your Cart</h1>
        <div class="cart-container">
            <%
                // Retrieve the cart object from the session
                Cart cart = (Cart) session.getAttribute("cart");

                // Check if the cart is not null and contains items
                if (cart != null && !cart.getCart().isEmpty()) {
                    int subtotal = 0; // To calculate the subtotal dynamically
            %>
            <!-- Single Card to Contain All Items -->
            <div class="cart-items">
                <%
                    // Loop through the items in the cart
                    for (CartItem item : cart.getCart().values()) {
                        int itemTotal = item.getQuantity() * item.getPrice();
                        subtotal += itemTotal;
                %>
                <div class="cart-item">
                    <!-- Product Details -->
                    <div class="item-details">
                        <h2><%= item.getName() %></h2>
                        <p class="price">₹<%= item.getPrice() %></p>
                        <div class="quantity-controls">
                            <!-- Decrease Quantity Form -->
                            <form action="cart?restaurentId=<%=request.getParameter("restaurentId") %>" method="post" style="display: inline;">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                                <button type="submit" class="quantity-btn">-</button>
                            </form>
                            <!-- Display Quantity -->
                            <span class="quantity"><%= item.getQuantity() %></span>
                            <!-- Increase Quantity Form -->
                            <form action="cart?restaurentId=<%=request.getParameter("restaurentId") %>" method="post" style="display: inline;">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                                <button type="submit" class="quantity-btn">+</button>
                            </form>
                        </div>
                    </div>
                    <!-- Total Price and Remove Button -->
                    <div class="item-total">
                        <p>₹<%= itemTotal %></p>
                        <form action="cart?restaurentId=<%=request.getParameter("restaurentId") %>" method="post">
                            <input type="hidden" name="itemId" value="<%= item.getId() %>">
                            <input type="hidden" name="action" value="remove">
                            <button type="submit" class="remove-btn"><i class="fas fa-trash"></i></button>
                        </form>
                    </div>
                </div>
                <%
                    } // End of for loop
                %>
            </div>
            <!-- Order Summary -->
            <div class="cart-summary">
                <h2>Order Summary</h2>
                <div class="summary-item">
                    <span>Subtotal</span>
                    <span>₹<%= subtotal %></span>
                </div>
                <div class="summary-item">
                    <span>Delivery Fee</span>
                    <span>₹50</span>
                </div>
                <div class="summary-item discount">
                    <span>Discount</span>
                    <span>-₹20</span>
                </div>
                <div class="summary-item total">
                    <span>Total</span>
                    <span>₹<%= subtotal + 50 - 20 %></span>
                </div>
                <form action="checkout.jsp" method="post">
				    <input type="hidden" name="subtotal" value="<%= subtotal %>">
				    <input type="hidden" name="deliveryFee" value="50">
				    <input type="hidden" name="total" value="<%= subtotal + 50 - 20 %>">
				    
				    <button type="submit" class="checkout-btn">Proceed to Checkout</button>
				</form>

                <a href="menu?restaurentId=<%=request.getParameter("restaurentId") %>" class="continue-shopping">Add more items</a>
            </div>
            <%
                } else {
            %>
            <!-- Empty Cart Message -->
            <p>Your cart is empty. <a href="HomeServlet">Continue Shopping</a></p>
            <%
                } // End of if-else
            %>
        </div>
    </div>
</body>
</html>