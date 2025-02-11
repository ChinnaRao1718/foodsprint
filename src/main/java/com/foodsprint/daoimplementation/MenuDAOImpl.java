package com.foodsprint.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodsprint.dao.MenuDao;
import com.foodsprint.model.Menu;
import com.foodsprint.utility.DBConnection;

public class MenuDAOImpl implements MenuDao {
	
	private static final String GET_ALL_MENUS = "SELECT * FROM `menu` where restaurentId = ? ";
	private static final String GET_MENU = "SELECT * FROM `menu` where menuId = ? ";

	@Override
	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Menu getMenu(int menuId) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		Menu menu = null;
		
		
		connection = DBConnection.getConnection();
		try {
			prepareStatement = connection.prepareStatement(GET_MENU);
			prepareStatement.setInt(1, menuId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) menu = extractMenu(resultSet);
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenu(int menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Menu> getAllMenusByResataurent(int restaurentId) {
		
		List<Menu> menuList = new ArrayList<Menu>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(GET_ALL_MENUS);
			prepareStatement.setInt(1,restaurentId);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				Menu menu = extractMenu(resultSet);
				menuList.add(menu);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return menuList;
	}
	
	
	Menu extractMenu(ResultSet res) throws SQLException {
		
		int menuId = res.getInt("menuId");
		int restaurentId = res.getInt("restaurentId");
		String itemName = res.getString("itemName");
		String description = res.getString("description");
		int price = res.getInt("price");
		String ratings = res.getString("ratings");
		boolean isAvailable = res.getBoolean("isAvailable");
		String imagePath = res.getString("imagePath");
		Menu menu = new Menu(menuId, restaurentId, itemName, description, price, ratings, isAvailable, imagePath);
		
		return menu;
	}


}
