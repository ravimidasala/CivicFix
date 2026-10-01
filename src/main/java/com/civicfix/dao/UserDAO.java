package com.civicfix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.civicfix.model.User;
import com.civicfix.util.DBConnection;

public class UserDAO {

	public boolean emailExists(String email) {

		String sql = "SELECT id FROM users WHERE email = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, email);

			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public boolean registerUser(User user) {

		String sql = "INSERT INTO users " + "(name, email, password, phone, role) " + "VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getRole());

			int rowsInserted = statement.executeUpdate();

			return rowsInserted > 0;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public User findByEmail(String email) {

		String sql = "SELECT id, name, email, password, phone, role " + "FROM users WHERE email = ?";

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement= connection.prepareStatement(sql)){
			statement.setString(1, email);
			
			try(ResultSet resultSet= statement.executeQuery()){
				
				if(resultSet.next()) {
					
					User user = new User();
					
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setPhone(resultSet.getString("phone"));
	                user.setRole(resultSet.getString("role"));
					
	                return user;
				}
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
