package com.foodsprint.model;

public class Menu {
	
	private int menuId;
	private int restaurentId;
	private String itemName;
	private String description;
	private int price;
	private String ratings;
	private boolean isAvailable;
	private String imagePath;
	
	public Menu() {
		
	}

	public Menu(int menuId, int restaurentId, String itemName, String description, int price, String ratings,
			boolean isAvailable, String imagePath) {
		super();
		this.menuId = menuId;
		this.restaurentId = restaurentId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.ratings = ratings;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurentId() {
		return restaurentId;
	}

	public void setRestaurentId(int restaurentId) {
		this.restaurentId = restaurentId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
