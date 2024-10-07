package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("file");

        if (filename == null || filename.isEmpty()) {
            resp.setContentType("text/plain");
            resp.getWriter().println("File not found: " + filename);
            return;
        }
        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/" + filename);

        if (in == null) {
            resp.setContentType("text/plain");
            resp.getWriter().println("File not found: " + filename);
            return;
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        try (OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } finally {
            in.close();
        }
    }
}