<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CivicFix - Register</title>
</head>
<body>

<h1>Create CivicFix Account</h1>

    <form action="register" method="post">

        <div>
            <label for="name">Name</label>

            <input type="text"
                   id="name"
                   name="name"
                   required>
        </div>

        <br>

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
                   minlength="8"
                   required>
        </div>

        <br>

        <div>
            <label for="phone">Phone</label>

            <input type="text"
                   id="phone"
                   name="phone">
        </div>

        <br>

        <button type="submit">
            Create Account
        </button>

    </form>
</body>
</html>