package com.civicfix.controller;

import java.io.IOException;

import com.civicfix.model.Issue;
import com.civicfix.model.User;
import com.civicfix.service.IssueService;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/report-issue")
public class ReportIssueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IssueService issueService;

	public void init(ServletConfig config) throws ServletException {

		issueService = new IssueService();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/report-issue.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {

			response.sendRedirect(request.getContextPath() + "/login");

			return;
		}

		User user = (User) session.getAttribute("user");

		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String severity = request.getParameter("severity");

		Issue issue  = issueService.createIssue(user.getId(), category, title, description, location, severity);

		if (issue != null) {

			request.setAttribute("issue", issue);

			request.getRequestDispatcher("/issue-success.jsp").forward(request, response);

		} else {

			request.setAttribute("error", "Unable to report the issue. Please check your information.");

			request.getRequestDispatcher("/report-issue.jsp").forward(request, response);
		}

	}

}
