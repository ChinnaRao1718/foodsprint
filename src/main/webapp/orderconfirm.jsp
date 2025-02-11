<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // Redirect to login if user is not in session
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Retrieve order details from request/session
    String fullName = request.getParameter("full-name");
    String phoneNumber = request.getParameter("phone-number");
    String streetAddress = request.getParameter("street-address");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    String zipCode = request.getParameter("zip-code");

    int subtotal = Integer.parseInt(request.getParameter("subtotal"));
    int deliveryFee = Integer.parseInt(request.getParameter("deliveryFee"));
    int total = Integer.parseInt(request.getParameter("total"));

    // Generate a random order number (for demonstration purposes)
    int orderNumber = (int)(Math.random() * 100000 + 10000);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmed</title>
    <link rel="stylesheet" href="orderconfirm.css">
    <script>
        window.addEventListener("load", function() {
            document.getElementById("welcomeSound").play();
        });
    </script>
</head>
<body>
	<audio id="welcomeSound" src="ConfirmTone/sound.mp3.wav"></audio>
    <div class="confirmation-container">
        <div class="checkmark-circle">
            <div class="checkmark draw"></div>
        </div>
        
        <div class="confirmation-content">
            <h1>Order Confirmed!</h1>
            <p class="order-number">Order #<%= orderNumber %></p>
            <p class="thank-you">Thank you for your purchase</p>
            
            <div class="order-details">
                <h2>Order Details</h2>
                <div class="detail-row">
                    <span>Order Total:</span>
                    <span>₹<%= total %></span>
                </div>
                <div class="detail-row">
                    <span>Estimated Delivery:</span>
                    <span>30 Minutes</span>
                </div>
            </div>

            <div class="shipping-address">
                <h2>Shipping Address</h2>
                <p><%= fullName %> <br>
                <%= streetAddress %> <br>
                <%= city %>, <%= state %> <%= zipCode %> <br>
                Phone: <%= phoneNumber %></p>
            </div>

            <div class="button-group">
                <a href="HomeServlet" class="primary-button">Continue Shopping</a>
                <button class="secondary-button">Track Order</button>
            </div>
        </div>
    </div>
</body>
</html>
