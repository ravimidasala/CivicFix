<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.civicfix.model.User"%>

<%
User user = (User) session.getAttribute("user");

if (user == null) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CivicFix - Report Issue</title>
</head>

<body>

	<h1>Report a Civic Issue</h1>

	<%
	if (request.getAttribute("error") != null) {
	%>

	<p style="color: red;">
		<%=request.getAttribute("error")%>
	</p>

	<%
	}
	%>

	<p>
		Welcome,
		<%=user.getName()%>
	</p>

	<hr>

	<form action="${pageContext.request.contextPath}/report-issue"
		method="post">

		<div>
			<label for="category">Category</label> <br> <select
				id="category" name="category" required>
				<option value="">Select category</option>
				<option value="POTHOLE">Pothole</option>
				<option value="STREETLIGHT">Broken Streetlight</option>
				<option value="GARBAGE">Garbage Overflow</option>
				<option value="WATER_LEAKAGE">Water Leakage</option>
				<option value="DAMAGED_ROAD">Damaged Road</option>
			</select>
		</div>

		<br>

		<div>
			<label for="title">Issue Title</label> <br> <input type="text"
				id="title" name="title" maxlength="150" required>
		</div>

		<br>

		<div>
			<label for="description">Description</label> <br>

			<textarea id="description" name="description" rows="6" required></textarea>
		</div>

		<br>

		<div>
			<label for="location">Location</label> <br> <input type="text"
				id="location" name="location" maxlength="255"
				placeholder="Enter the issue location" required>
		</div>

		<br>

		<div>
			<label for="severity">Severity</label> <br> <select
				id="severity" name="severity" required>

				<option value="">Select severity</option>

				<option value="LOW">Low</option>

				<option value="MEDIUM">Medium</option>

				<option value="HIGH">High</option>

				<option value="CRITICAL">Critical</option>

			</select>
		</div>

		<br>

		<button type="submit">Submit Issue</button>

	</form>

	<br>

	<a href="${pageContext.request.contextPath}/dashboard"> Back to
		Dashboard </a>

</body>
</html>