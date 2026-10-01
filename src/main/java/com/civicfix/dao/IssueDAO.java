package com.civicfix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
