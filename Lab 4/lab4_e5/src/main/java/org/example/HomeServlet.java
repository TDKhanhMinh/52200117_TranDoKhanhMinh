package org.example;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = request.getParameter("page");

        if (page == null || page.trim().isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().println("Welcome to our website");
            return;
        }

        String jspPage;
        switch (page.toLowerCase()) {
            case "about":
                jspPage = "/WEB-INF/about.jsp";
                break;
            case "contact":
                jspPage = "/WEB-INF/contact.jsp";
                break;
            case "help":
                jspPage = "/WEB-INF/help.jsp";
                break;
            default:
                response.setContentType("text/html");
                response.getWriter().println("Welcome to our website");
                return;
        }

        request.getRequestDispatcher(jspPage).forward(request, response);
    }
}
