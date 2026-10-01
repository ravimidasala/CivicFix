package com.civicfix.controller;

import java.io.IOException;

import com.civicfix.model.User;
import com.civicfix.service.UserService;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public void init(ServletConfig config) throws ServletException {

		userService = new UserService();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		doGet(request, response);

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.loginUser(email, password);

		if (user != null) {

			HttpSession session = request.getSession();

			session.setAttribute("user", user);
			session.setAttribute("userid", user.getId());
			session.setAttribute("userRole", user.getRole());

			response.sendRedirect(request.getContextPath() + "/dashboard.jsp");

		} else {

			request.setAttribute("error", "Invalid email or password.");
			
			 request.getRequestDispatcher("/login.jsp")
             .forward(request, response);

		}

	}

}
