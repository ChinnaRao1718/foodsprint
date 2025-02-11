package com.foodsprint.dao;

import java.util.List;

import com.foodsprint.model.User;

public interface UserDAO {
	
	boolean addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();

}
