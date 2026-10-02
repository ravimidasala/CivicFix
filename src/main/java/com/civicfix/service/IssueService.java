package com.civicfix.service;

import java.util.List;

import com.civicfix.dao.IssueDAO;
import com.civicfix.model.Issue;

public class IssueService {

	private final IssueDAO issueDAO;

	public IssueService() {
		issueDAO = new IssueDAO();
	}

	public Issue createIssue(int userId, String category, String title, String description, String location,
			String severity) {

		if (userId <= 0) {
			return null;
		}

		if (category == null || category.trim().isEmpty()) {
			return null;
		}

		if (title == null || title.trim().isEmpty()) {
			
			return null;
			
		}

		if (description == null || description.trim().isEmpty()) {
			 
			return null;
		}

		if (location == null || location.trim().isEmpty()) {
			 
			return null;
		}

		if (severity == null || severity.trim().isEmpty()) {
			 
			return null;
		}

		String trackingId = generateTrackingId();

		Issue issue = new Issue(trackingId, userId, category.trim(), title.trim(), description.trim(), location.trim(),
				severity.trim(), "PENDING");

		if (issueDAO.createIssue(issue)) {
		    return issue;
		}

		return null;
	}

	private String generateTrackingId() {

		long timestamp = System.currentTimeMillis();

		return "CF-" + timestamp;
	}
	
	
	public List<Issue> getIssuesByUserId(int userId) {
	    return issueDAO.findIssuesByUserId(userId);
	}
	
	public int getTotalIssuesByUserId(int userId) {
	    return issueDAO.countIssuesByUserId(userId);
	}
	
	public int getPendingIssuesByUserId(int userId) {
	    return issueDAO.countPendingIssuesByUserId(userId);
	}
	
}