package org.example;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/image1")
public class ImageServlet1 extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        ServletOutputStream outputStream = resp.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/img2.jpg"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        int ch = 0;
        while ((ch = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(ch);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
        outputStream.close();
        fileInputStream.close();
    }
}
