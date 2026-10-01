<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CivicFix - Login</title>
</head>
<body>

	<h1>Login to CivicFix</h1>

	  <% if (request.getAttribute("error") != null) { %>

        <p style="color: red;">
            <%= request.getAttribute("error") %>
        </p>

    <% } %>

    <form action="${pageContext.request.contextPath}/login"
          method="post">

        <div>
            <label for="email">Email</label>
            <input type="email"
                   id="email"
                   name="email"
                   required>
        </div>

        <br>

        <div>
            <label for="password">Password</label>
            <input type="password"
                   id="password"
                   name="password"
                   required>
        </div>

        <br>

        <button type="submit">Login</button>

    </form>

    <p>
        Don't have an account?
        <a href="${pageContext.request.contextPath}/register">
            Register here
        </a>
    </p>

</body>
</html>