package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] favoriteIdes = request.getParameterValues("favorite_ide");
        String toeic = request.getParameter("toeic");
        String message = request.getParameter("message");


        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder();

        if (isEmpty(name)) {
            errorMessage.append("Name is required<br>");
            hasError = true;
        }
        if (isEmpty(email)) {
            errorMessage.append("Email is required<br>");
            hasError = true;
        }
        if (isEmpty(gender)) {
            errorMessage.append("Gender is required<br>");
            hasError = true;
        }
        if (isEmpty(country) || country.equals("Select a country")) {
            errorMessage.append("Country is required<br>");
            hasError = true;
        }
        if (favoriteIdes == null || favoriteIdes.length == 0) {
            errorMessage.append("Please select at least one IDE<br>");
            hasError = true;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println(".error { color: red; margin-bottom: 20px; }");
        out.println("table { border-collapse: collapse; width: 500px; margin: 20px auto; }");
        out.println("table, th, td { border: 1px solid black; }");
        out.println("td { padding: 8px; }");
        out.println("td:first-child { width: 150px; color: navy; }");
        out.println("td:last-child { color: green; }");
        out.println(".error-message { color: red; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        if (hasError) {
            out.println("<div class='error'>");
            out.println("<h2>Registration Error</h2>");
            out.println(errorMessage.toString());
            out.println("<a href='javascript:history.back()'>Go Back</a>");
            out.println("</div>");
        } else {
            out.println("<table>");

            out.println("<tr>");
            out.println("<td>Họ tên</td>");
            out.println("<td>" + escapeHtml(name) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Email</td>");
            out.println("<td>" + escapeHtml(email) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Ngày sinh</td>");
            out.println("<td>" + escapeHtml(birthday) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Giờ sinh</td>");
            out.println("<td>" + escapeHtml(birthtime) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Giới tính</td>");
            out.println("<td>" + escapeHtml(gender) + "</td>");
            out.println("</tr>");


            out.println("<tr>");
            out.println("<td>Quốc gia</td>");
            out.println("<td>" + escapeHtml(country) + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>IDE Yêu thích</td>");
            out.println("<td>");
            for (String ide : favoriteIdes) {
                out.println(escapeHtml(ide) + "<br>");
            }
            out.println("</td>");
            out.println("</tr>");


            out.println("<tr>");
            out.println("<td>Điểm TOEIC</td>");
            out.println("<td>" + escapeHtml(toeic) + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Giới thiệu bản thân</td>");
            out.println("<td>" + escapeHtml(message) + "</td>");
            out.println("</tr>");

            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
