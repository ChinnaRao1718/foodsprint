package com.foodsprint.model;

public class Restaurent {
	
	private int restaurentId;
	private String name;
	private String address;
	private String phone;
	private float rating;
	private String cusineType;
	private boolean isActive;
	private int eta;
	private int adminUserId;
	private String imagePath;
	
	
	public Restaurent() {
		
	}
	
	
	public Restaurent(int restaurentId, String name, String address, String phone, float rating, String cusineType,
			boolean isActive, int eta, int adminUserId, String imagePath) {
		super();
		this.restaurentId = restaurentId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cusineType = cusineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}


	public int getRestaurentId() {
		return restaurentId;
	}


	public void setRestaurentId(int restaurentId) {
		this.restaurentId = restaurentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	public String getCusineType() {
		return cusineType;
	}


	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public int getEta() {
		return eta;
	}


	public void setEta(int eta) {
		this.eta = eta;
	}


	public int getAdminUserId() {
		return adminUserId;
	}


	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	

}
