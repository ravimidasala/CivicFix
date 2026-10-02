<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.civicfix.model.Issue"%>
<%@ page import="jakarta.servlet.http.HttpServletResponse"%>

<%
Issue issue = (Issue) request.getAttribute("issue");

if (issue == null) {
	response.sendError(HttpServletResponse.SC_NOT_FOUND, "Issue not found.");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CivicFix - Issue Details</title>
</head>

<body>

	<h1>CivicFix</h1>

	<h2>Issue Details</h2>

	<hr>

	<p>
		<strong>Tracking ID:</strong>
		<%=issue.getTrackingId()%>
	</p>

	<p>
		<strong>Category:</strong>
		<%=issue.getCategory()%>
	</p>

	<p>
		<strong>Title:</strong>
		<%=issue.getTitle()%>
	</p>

	<p>
		<strong>Description:</strong>
		<%=issue.getDescription()%>
	</p>

	<p>
		<strong>Location:</strong>
		<%=issue.getLocation()%>
	</p>

	<p>
		<strong>Severity:</strong>
		<%=issue.getSeverity()%>
	</p>

	<p>
		<strong>Status:</strong>
		<%=issue.getStatus()%>
	</p>

	<hr>

	<p>
		<a href="${pageContext.request.contextPath}/dashboard"> Back to
			Dashboard </a>
	</p>

</body>
</html>