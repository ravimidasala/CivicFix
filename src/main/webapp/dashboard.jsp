<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.civicfix.model.User"%>
<%@ page import="com.civicfix.model.Issue"%>

<%
User user = (User) session.getAttribute("user");

if (user == null) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}
@SuppressWarnings("unchecked")
List<Issue> issues = (List<Issue>) request.getAttribute("issues");

Integer totalIssues = (Integer) request.getAttribute("totalIssues");
Integer pendingIssues = (Integer) request.getAttribute("pendingIssues");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CivicFix - Citizen Dashboard</title>

<style>
.issue-table {
	border-collapse: collapse;
	width: 100%;
}

.issue-table th, .issue-table td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}
</style>

</head>

<body>

	<h1>CivicFix</h1>

	<h2>
		Welcome,
		<%=user.getName()%>
	</h2>

	<p>
		Email:
		<%=user.getEmail()%>
	</p>

	<p>
		<a href="${pageContext.request.contextPath}/logout"> Logout </a>
	</p>

	<hr>

	<h3>My Dashboard</h3>

	<div>
		<h4>Total Issues</h4>
		<p><%=totalIssues%></p>
	</div>

	<div>
		<h4>Pending Issues</h4>
		<p><%=pendingIssues%></p>
	</div>

	<a href="${pageContext.request.contextPath}/report-issue">
		<button type="button">+ Report New Issue</button>
	</a>

	<br>
	<br>

	<h3>My Issues</h3>

	<%
	if (issues == null || issues.isEmpty()) {
	%>

	<p>You haven't reported any issues yet.</p>

	<%
	} else {
	%>

	<table class="issue-table">

		<thead>
			<tr>
				<th>Tracking ID</th>
				<th>Category</th>
				<th>Title</th>
				<th>Severity</th>
				<th>Status</th>
				<th>Location</th>
			</tr>
		</thead>

		<tbody>

			<%
			for (Issue issue : issues) {
			%>

			<tr>

				<td><%=issue.getTrackingId()%></td>

				<td><%=issue.getCategory()%></td>

				<td><%=issue.getTitle()%></td>

				<td><%=issue.getSeverity()%></td>

				<td><%=issue.getStatus()%></td>

				<td><%=issue.getLocation()%></td>

			</tr>

			<%
			}
			%>

		</tbody>

	</table>

	<%
	}
	%>

	<br>

	<a href="${pageContext.request.contextPath}/report-issue"> Report
		Another Issue </a>

</body>
</html>