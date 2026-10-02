package com.civicfix.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.civicfix.model.Issue;
import com.civicfix.model.User;
import com.civicfix.service.IssueService;

@WebServlet("/issue")
public class IssueDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private IssueService issueService;

    @Override
    public void init() {
        issueService = new IssueService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
 
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
 
        String issueIdParameter = request.getParameter("id");

        if (issueIdParameter == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Issue ID is required.");
            return;
        }

        int issueId;

        try {
            issueId = Integer.parseInt(issueIdParameter);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid issue ID.");
            return;
        }
 
        Issue issue = issueService.getIssueById(issueId);

        if (issue == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Issue not found.");
            return;
        }
 
        
        if (issue.getUserId() != user.getId()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "You are not allowed to view this issue.");
            return;
        }

        request.setAttribute("issue", issue);

        request.getRequestDispatcher("/issue-details.jsp")
               .forward(request, response);
    }
}