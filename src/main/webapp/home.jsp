<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, com.foodsprint.model.Restaurent" %>
<%@ page import="com.foodsprint.model.User" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foodize - Food Delivery</title>
    <link rel="stylesheet" href="restaurantcss.css">
</head>
<body>
    <nav>
        <div class="nav-container">
            <div class="nav-left">
                <a href="#" class="logo">Foodize</a>
            </div>
            <div class="nav-right">
                <form action="#restaurants" method="get" class="search-form">
                    <input type="text" placeholder="Search restaurants..." class="search-bar">
                    <button type="submit" class="search-button">Search</button>
                </form>
                                <%
				                User user = (User) session.getAttribute("user");
				    if (user != null) {
				%>
				    <a href="cart?restaurentId=<%= session.getAttribute("restaurentId") %>" class="nav-icon">🛒</a>
				<%
				    } else {
				%>
				    <a href="login.jsp" class="nav-icon">🛒</a>
				<%
				    }
				%>
                <a href="login.jsp" class="nav-icon">👤 <span>Sign In</span></a>
            </div>
        </div>
    </nav>

    <main>
        <section class="section-heading">
                     <h1>Welcome to Foodize  
			    <%
			        if (user != null) {
			    %>
			        <span class="welcome-user"><%= user.getUserName() %></span>
			    <%
			        } else {
			    %>
			        <span class="welcome-user">Guest</span>  <%-- Show "Guest" if not logged in --%>
			    <%
			        }
			    %>
			</h1>
            <h2>Exclusive Offers</h2>
            <p>Delicious deals that'll make your day special</p>
        </section>
        <section class="offers">
            <div class="offer-card">
                <img src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&q=80&w=500" alt="Offer 1">
                <div class="offer-content">
                    <h2>50% OFF</h2>
                    <p>On your first order</p>
                </div>
            </div>
            <div class="offer-card">
                <img src="https://images.unsplash.com/photo-1493770348161-369560ae357d?auto=format&fit=crop&q=80&w=500" alt="Offer 2">
                <div class="offer-content">
                    <h2>Free Delivery</h2>
                    <p>On orders above $30</p>
                </div>
            </div>
            <div class="offer-card">
                <img src="https://images.unsplash.com/photo-1476224203421-9ac39bcb3327?auto=format&fit=crop&q=80&w=500" alt="Offer 3">
                <div class="offer-content">
                    <h2>Special Deal</h2>
                    <p>20% off on family meals</p>
                </div>
            </div>
        </section>
        <section class="section-heading" id="restaurants">
            <h2>Popular Restaurants</h2>
            <p>Explore the finest dining experiences in your area</p>
        </section>

        <section class="restaurants">
            <%
                List<Restaurent> restaurents = (List<Restaurent>) request.getAttribute("restaurents");
                for (Restaurent res : restaurents) {
            %>
            <a href="menu?restaurentId=<%= res.getRestaurentId() %>">
                <div class="restaurant-card">
                    <%
                        String imagePath = res.getImagePath();
                        if (imagePath.startsWith("/")) {
                            imagePath = imagePath.substring(1);
                        }
                    %>
                    <img src="<%= imagePath %>" alt="Restaurant">
                    <div class="restaurant-info">
                        <div class="restaurant-name"><%= res.getName() %></div>
                        <div class="restaurant-cuisine"><%= res.getCusineType() %></div>
                        <div class="restaurant-meta">
                            <span>⭐ <%= res.getRating() %></span>
                            <span><%= res.getEta() %> mins</span>
                        </div>
                    </div>
                </div>
            </a>
            <%
                }
            %>
        </section>
    </main>

    <footer>
        <div class="footer-content">
            <div class="footer-section">
                <h3>About Foodize</h3>
                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Careers</a></li>
                    <li><a href="#">Contact Us</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>For Restaurants</h3>
                <ul>
                    <li><a href="#">Partner With Us</a></li>
                    <li><a href="#">Apps For You</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Learn More</h3>
                <ul>
                    <li><a href="#">Privacy</a></li>
                    <li><a href="#">Security</a></li>
                    <li><a href="#">Terms</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Social Links</h3>
                <div class="social-links">
                    <a href="#">📘 Facebook</a>
                    <a href="#">📸 Instagram</a>
                    <a href="#">🐦 Twitter</a>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>