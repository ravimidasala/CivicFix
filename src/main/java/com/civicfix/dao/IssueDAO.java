package com.civicfix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.civicfix.model.Issue;
import com.civicfix.util.DBConnection;

public class IssueDAO {

	public boolean createIssue(Issue issue) {

		String sql = "INSERT INTO issues " + "(tracking_id, user_id, category, title, "
				+ "description, location, severity, status) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, issue.getTrackingId());
			statement.setInt(2, issue.getUserId());
			statement.setString(3, issue.getCategory());
			statement.setString(4, issue.getTitle());
			statement.setString(5, issue.getDescription());
			statement.setString(6, issue.getLocation());
			statement.setString(7, issue.getSeverity());
			statement.setString(8, issue.getStatus());

			int rowsInserted = statement.executeUpdate();

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public List<Issue> findIssuesByUserId(int userId) {

	    List<Issue> issues = new ArrayList<>();

	    String sql =
	            "SELECT id, tracking_id, user_id, category, title, " +
	            "description, location, severity, status " +
	            "FROM issues " +
	            "WHERE user_id = ? " +
	            "ORDER BY created_at DESC";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement =
	                 connection.prepareStatement(sql)) {

	        statement.setInt(1, userId);

	        try (ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {

	                Issue issue = new Issue();

	                issue.setId(resultSet.getInt("id"));
	                issue.setTrackingId(
	                        resultSet.getString("tracking_id"));
	                issue.setUserId(
	                        resultSet.getInt("user_id"));
	                issue.setCategory(
	                        resultSet.getString("category"));
	                issue.setTitle(
	                        resultSet.getString("title"));
	                issue.setDescription(
	                        resultSet.getString("description"));
	                issue.setLocation(
	                        resultSet.getString("location"));
	                issue.setSeverity(
	                        resultSet.getString("severity"));
	                issue.setStatus(
	                        resultSet.getString("status"));

	                issues.add(issue);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return issues;
	}
	
	public int countIssuesByUserId(int userId) {

	    String sql = "SELECT COUNT(*) FROM issues WHERE user_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, userId);

	        try (ResultSet resultSet = statement.executeQuery()) {

	            if (resultSet.next()) {
	                return resultSet.getInt(1);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return 0;
	}
	
	
	public int countPendingIssuesByUserId(int userId) {

	    String sql =
	        "SELECT COUNT(*) FROM issues " +
	        "WHERE user_id = ? AND status = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement =
	             connection.prepareStatement(sql)) {

	        statement.setInt(1, userId);
	        statement.setString(2, "PENDING");

	        try (ResultSet resultSet = statement.executeQuery()) {

	            if (resultSet.next()) {
	                return resultSet.getInt(1);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return 0;
	}
	
	
	public int countResolvedIssuesByUserId(int userId) {

	    String sql =
	        "SELECT COUNT(*) FROM issues " +
	        "WHERE user_id = ? AND status = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement =
	             connection.prepareStatement(sql)) {

	        statement.setInt(1, userId);
	        statement.setString(2, "RESOLVED");

	        try (ResultSet resultSet = statement.executeQuery()) {

	            if (resultSet.next()) {
	                return resultSet.getInt(1);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return 0;
	}
	
}
