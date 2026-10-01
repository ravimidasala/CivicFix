<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.civicfix.model.User" %>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect(
            request.getContextPath() + "/login"
        );
        return;
    }
%>   
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>CivicFix - Citizen Dashboard</title>
</head>
<body>

     <h1>CivicFix</h1>

    <h2>
        Welcome, <%= user.getName() %>
    </h2>

    <p>
        Email: <%= user.getEmail() %>
    </p>

    <hr>

    <h3>My Dashboard</h3>

    <p>Reported Issues: 0</p>
    <p>In Progress: 0</p>
    <p>Resolved: 0</p>

    <br>

    <button>
        Report New Issue
    </button>

    <br><br>

    <h3>My Recent Issues</h3>

    <p>No issues reported yet.</p>

</body>
</html>