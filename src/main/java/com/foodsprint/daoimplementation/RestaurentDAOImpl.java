package com.foodsprint.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodsprint.dao.RestaurentDAO;
import com.foodsprint.model.Restaurent;
import com.foodsprint.utility.DBConnection;

public class RestaurentDAOImpl implements RestaurentDAO {
	
	private static final String INSERT_QUERY = "INSERT INTO Restaurent (`name`,`address`,`phone`,`rating`,`cusineType`,`isActive`,`eta`,`imagePath`) VALUES (?,?,?,?,?,?,?,?)";
	private static final String GET_RESTAURENT_QUERY = "SELECT * FROM Restaurent WHERE restaurentId = ?";
	private static final String DELETE_RESTAURENT_QUERY = "DELETE FROM `Restaurent` WHERE `RestaurentId` = ?";
	private static final String GET_ALL_RESTAURENTS = "SELECT * FROM `RESTAURENT`";
	@Override
	public void addRestaurent(Restaurent restaurent) {
		Connection connection = null;
		 PreparedStatement prepareStatement = null;
		try  {
			 connection = DBConnection.getConnection();
			 prepareStatement = connection.prepareStatement(INSERT_QUERY);
			 prepareStatement.setString(1,restaurent.getName());
			 prepareStatement.setString(2,restaurent.getAddress());
			 prepareStatement.setString(3,restaurent.getPhone());
			 prepareStatement.setFloat(4,restaurent.getRating());
			 prepareStatement.setString(5,restaurent.getCusineType());
			 prepareStatement.setBoolean(6, restaurent.isActive());
			 prepareStatement.setInt(7, restaurent.getEta());
			 prepareStatement.setString(8, restaurent.getImagePath());
			 
			 prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public Restaurent getRestaurent(int restaurentId) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		Restaurent restaurent = null;
		try {
			connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(GET_RESTAURENT_QUERY);
			prepareStatement.setInt(1,restaurentId);
			ResultSet resultSet = prepareStatement.executeQuery();
			restaurent = extractRestaurent(resultSet);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurent;
	}
	
	
	

	@Override
	public void updateRestaurent(Restaurent restaurent) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void deleteRestaurent(int restaurentId) {
		Connection connection = null;
		PreparedStatement prepareStatement  = null;
		try {
			connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(DELETE_RESTAURENT_QUERY);
			prepareStatement.setInt(1, restaurentId);
			prepareStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Restaurent> getAllRestaurents() {
		List<Restaurent> restaurentList = new ArrayList<Restaurent>();
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_ALL_RESTAURENTS);
			while(resultSet.next()) {
				Restaurent restaurent = extractRestaurent(resultSet);
				restaurentList.add(restaurent);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return restaurentList;
	}
	
	Restaurent extractRestaurent(ResultSet res) throws SQLException {
		int restaurentId = res.getInt("restaurentId");
		String name = res.getString("name");
		String address = res.getString("address");
		String phone = res.getString("phone");
		Float rating = res.getFloat("rating");
		String cusinType = res.getString("cusineType");
		boolean isActive = res.getBoolean("isActive");
		int eta = res.getInt("eta");
		int adminUserId = res.getInt("adminUserId");
		String imagePath = res.getString("imagePath");
		Restaurent restaurent = new Restaurent(restaurentId, name, address, phone, rating, cusinType, isActive, eta, adminUserId, imagePath);
		return restaurent;
		
	}


}
