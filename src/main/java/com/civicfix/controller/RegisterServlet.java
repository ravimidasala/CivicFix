package com.civicfix.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.civicfix.service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public RegisterServlet() {
		super();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		userService = new UserService();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/register.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		boolean registered = userService.registerUser(name, email, password, phone);

		if (registered) {

			response.sendRedirect(request.getContextPath() + "/login.jsp");

		} else {

			request.setAttribute("error", "Registration failed. " + "The email may already exist.");
			
			request.getRequestDispatcher("./register.jsp").forward(request, response);
			
		}

	}

}
