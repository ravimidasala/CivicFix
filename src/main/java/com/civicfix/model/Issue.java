package com.civicfix.model;

public class Issue {

	private int id;
	private String trackingId;
	private int userId;
	private String category;
	private String title;
	private String description;
	private String location;
	private String severity;
	private String status;

	public Issue() {
	}

	public Issue(String trackingId, int userId, String category, String title, String description, String location,
			String severity, String status) {

		this.trackingId = trackingId;
		this.userId = userId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.location = location;
		this.severity = severity;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
