package com.civicfix.controller;

import java.io.IOException;
import java.util.List;

import com.civicfix.model.Issue;
import com.civicfix.model.User;
import com.civicfix.service.IssueService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IssueService issueService;

	public void init() {

		issueService = new IssueService();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {

			response.sendRedirect(request.getContextPath() + "/login");

			return;
		}

		User user = (User) session.getAttribute("user");

		List<Issue> issues = issueService.getIssuesByUserId(user.getId());

		int totalIssues = issueService.getTotalIssuesByUserId(user.getId());

		int pendingIssues = issueService.getPendingIssuesByUserId(user.getId());

		int resolvedIssues = issueService.getResolvedIssuesByUserId(user.getId());

		request.setAttribute("issues", issues);
		request.setAttribute("totalIssues", totalIssues);
		request.setAttribute("pendingIssues", pendingIssues);
		request.setAttribute("resolvedIssues", resolvedIssues);

		request.getRequestDispatcher("/dashboard.jsp").forward(request, response);

	}

}
