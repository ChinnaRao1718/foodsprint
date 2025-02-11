<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // Redirect to login if user is not in session
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Retrieve subtotal, delivery fee, and total from request parameters
    int subtotal = Integer.parseInt(request.getParameter("subtotal"));
    int deliveryFee = Integer.parseInt(request.getParameter("deliveryFee"));
    int total = Integer.parseInt(request.getParameter("total"));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout Page</title>
    <link rel="stylesheet" href="checkout.css">
    <script>
    function toggleCardDetails() {
        var cardDetails = document.getElementById("card-details");
        var paymentMethod = document.querySelector('input[name="payment-method"]:checked').value;
        var cardInputs = document.querySelectorAll("#card-details input");

        if (paymentMethod === "cash-on-delivery") {
            cardDetails.style.display = "none";

            // Remove "required" attribute when Cash on Delivery is selected
            cardInputs.forEach(input => input.removeAttribute("required"));
        } else {
            cardDetails.style.display = "block";

            // Add "required" attribute when Credit/Debit Card is selected
            cardInputs.forEach(input => input.setAttribute("required", "true"));
        }
    }

    </script>
</head>
<body>
    <div class="checkout-container">
        <h1>Checkout</h1>

        <form action="orderconfirm.jsp" method="post">
            <section class="checkout-section">
                <h2>Delivery Address</h2>
                <div class="form-row">
                    <div class="form-group">
                        <label for="full-name">Full Name</label>
                        <input type="text" id="full-name" name="full-name" required>
                    </div>
                    <div class="form-group">
                        <label for="phone-number">Phone Number</label>
                        <input type="tel" id="phone-number" name="phone-number" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="street-address">Street Address</label>
                    <input type="text" id="street-address" name="street-address" required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="city">City</label>
                        <input type="text" id="city" name="city" required>
                    </div>
                    <div class="form-group">
                        <label for="state">State</label>
                        <input type="text" id="state" name="state" required>
                    </div>
                    <div class="form-group">
                        <label for="zip-code">ZIP Code</label>
                        <input type="text" id="zip-code" name="zip-code" required>
                    </div>
                </div>
            </section>

            <section class="checkout-section">
                <h2>Payment Method</h2>
                <div class="payment-options">
                    <label class="payment-option">
                        <input type="radio" name="payment-method" value="credit-debit" checked onclick="toggleCardDetails()">
                        <span>Credit/Debit Card</span>
                    </label>
                    <label class="payment-option">
                        <input type="radio" name="payment-method" value="cash-on-delivery" onclick="toggleCardDetails()">
                        <span>Cash on Delivery</span>
                    </label>
                </div>

                <div id="card-details" class="card-details">
                    <div class="form-group">
                        <label for="card-number">Card Number</label>
                        <input type="text" id="card-number" name="card-number" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="expiry-date">Expiry Date</label>
                            <input type="text" id="expiry-date" name="expiry-date" placeholder="MM/YY" required>
                        </div>
                        <div class="form-group">
                            <label for="cvv">CVV</label>
                            <input type="text" id="cvv" name="cvv" required>
                        </div>
                    </div>
                </div>
            </section>

            <section class="checkout-section order-summary">
                <h2>Order Summary</h2>
                <div class="summary-details">
                    <div class="summary-row">
                        <span>Subtotal</span>
                        <span>₹<%= subtotal %></span>
                    </div>
                    <div class="summary-row">
                        <span>Delivery Fee</span>
                        <span>₹<%= deliveryFee %></span>
                    </div>
                    <div class="summary-row total">
                        <span>Total</span>
                        <span>₹<%= total %></span>
                    </div>
                </div>

                <!-- Hidden Inputs to send subtotal, deliveryFee, and total -->
                <input type="hidden" name="subtotal" value="<%= subtotal %>">
                <input type="hidden" name="deliveryFee" value="<%= deliveryFee %>">
                <input type="hidden" name="total" value="<%= total %>">

                <button type="submit" class="place-order-btn">Place Order</button>
            </section>
        </form>
    </div>
</body>
</html>
