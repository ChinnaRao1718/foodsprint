<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.foodsprint.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Menu</title>
    <link rel="stylesheet" href="menucss.css">
</head>
<body>
    <div class="container">
        <h1>Our Menu</h1>
        <div class="menu-grid">
            <%
            List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
            for (Menu m : menuList) {
            %>
            <div class="menu-item">
                <img src="<%= m.getImagePath() %>" alt="<%= m.getItemName() %>">
                <div class="item-content">
                    <div class="item-header">
                        <h2><%= m.getItemName() %></h2>
                        <span class="price">₹<%= m.getPrice() %></span>
                    </div>
                    <div class="rating">
                        ★★★★☆ <span><%= m.getRatings() %></span>
                    </div>
                    <p class="description"><%= m.getDescription() %></p>
                    <div class="item-footer">
                        <div class="availability <%= m.isAvailable() ? "available" : "not-available" %>">
                            <%= m.isAvailable() ? "Available" : "Not Available" %>
                        </div>
                        <form action="cart" method="post">
                            <input type="hidden" name="restaurentId" value="<%= request.getParameter("restaurentId") %>">
                            <input type="hidden" name="itemId" value="<%= m.getMenuId() %>">
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="action" value="add">
                            <button type="submit" class="add-to-cart">Add to Cart</button>
                        </form>
                    </div>
                </div>
            </div>
            <%
            }
            %>
        </div>
    </div>
</body>
</html>