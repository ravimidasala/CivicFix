<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="com.civicfix.model.User"%>
<%@ page import="com.civicfix.model.Issue"%>

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
<title>CivicFix - Issue Reported</title>
</head>

<body>

	<h1>Issue Reported Successfully</h1>

	<p>
		Thank you,
		<%=user.getName()%>.
	</p>

	<p>Your civic issue has been submitted successfully.</p>

	<%
	Issue issue = (Issue) request.getAttribute("issue");
	%>

	<p>
		<strong>Your Tracking ID:</strong>
		<%=issue.getTrackingId()%>
	</p>

	<p>You can track its progress from your dashboard.</p>

	<br>

	<a href="${pageContext.request.contextPath}/dashboard.jsp"> Go to
		Dashboard </a>

</body>
</html>