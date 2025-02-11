package com.foodsprint.daoimplementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodsprint.dao.UserDAO;
import com.foodsprint.model.User;
import com.foodsprint.utility.DBConnection;

public class UserDAOImpl implements UserDAO {
	
	private static final String INSERT_USER_QUERY = "INSERT INTO User (name, userName, password, email, phone, address, role, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_USER_QUERY = "SELECT * FROM `User` WHERE `userId` = ?";
	private static final String UPDATE_USER_QUERY = "UPDATE `User` SET `name` = ? `password` = ? `phone` = ? `address` = ? `role` = ? ";
	private static final String DELETE_USER_QUERY = "DELETE FROM `User` WHERE `userId` = ? ";
	private static final String GET_ALL_USERS = "SELECT * FROM User";
	private static final String emailQuery = "SELECT COUNT(*) FROM User WHERE email = ?";

	@Override
	public boolean addUser(User user) {
		 boolean isRegistered = false;
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_USER_QUERY);) {
			
			    ps.setString(1, user.getName());
	            ps.setString(2, user.getName().replaceAll("\\s", ""));
	            ps.setString(3, user.getPassword());
	            ps.setString(4, user.getEmail());
	            ps.setString(5, user.getPhone());
	            ps.setString(6, user.getAddress());
	            ps.setString(7, user.getRole());
	            ps.setDate(8, (Date) user.getCreatedDate());
			
	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                isRegistered = true;
	            }	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return  isRegistered;
		
	}
	
	
	//checking mail exists or not
	
	public boolean emailExists(String email) {
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(emailQuery)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // If count > 0, email exists
                }
            }
		 } catch (SQLException e) {
	            e.printStackTrace(); // Handle properly in real applications (e.g., logging)
	        }
	        return false; // Default return false if email not found or query fails
	        
	        
	    }
		
	
	
	
	
	
	


	@Override
	public User getUser(int userId) {
		User user = null;
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_QUERY);
				) {
			prepareStatement.setInt(1,userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			user = extractUser(resultSet);	
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}
	
	
	
	

	@Override
	public void updateUser(User user) {
		Connection connection = null; 
		PreparedStatement prepareStatement = null; 
		try {
			connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(UPDATE_USER_QUERY);
			
			prepareStatement.setString(1,user.getName());
			prepareStatement.setString(2,user.getPassword());
			prepareStatement.setString(3,user.getPhone());
			prepareStatement.setString(4,user.getAddress());
			prepareStatement.setString(5,user.getRole());
			prepareStatement.executeUpdate();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
		

	@Override
	public void deleteUser(int userId) {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement =connection.prepareStatement(DELETE_USER_QUERY);
			prepareStatement.setInt(1, userId);
			int res = prepareStatement.executeUpdate();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
		
	

	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		try  {
			Connection connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
			
			while(resultSet.next()) {
				User user = extractUser(resultSet);
				userList.add(user);
				
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	
	User extractUser(ResultSet res) throws SQLException {
		
		int userId = res.getInt("userId");
		String name = res.getString("name");
		String userName = res.getString("userName");
		String password = res.getString("password");
		String email = res.getString("email");
		String phone = res.getString("phone");
		String address = res.getString("address");
		String role = res.getString("role");
		
		User user = new User(userId, name, userName, password, email, phone, address, role, null, null);
		 return user;
		
	}

}
