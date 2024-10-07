package org.example;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/UploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class UploadServlet extends HttpServlet {
    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(
            Arrays.asList("txt", "doc", "docx", "img", "pdf", "rar", "zip")
    );
    private String uploadsPath;

    @Override
    public void init() {
        uploadsPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadsDir = new File(uploadsPath);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String filename = request.getParameter("filename");
            Part filePart = request.getPart("file");
            boolean override = request.getParameter("override") != null;

            String originalFileName = filePart.getSubmittedFileName();
            String fileExtension = getFileExtension(originalFileName);

            if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
                outputMessage(response, "Unsupported file extension");
                return;
            }
            String fullFilePath = uploadsPath + File.separator + filename + "." + fileExtension;
            File file = new File(fullFilePath);

            if (file.exists() && !override) {
                outputMessage(response, "File already exists");
                return;
            }
            filePart.write(fullFilePath);

            String downloadLink = request.getContextPath() + "/uploads/" + filename + "." + fileExtension;
            String message = file.exists() && override ?
                    "File has been overridden" : "File has been uploaded";
            outputSuccessMessage(response, message, downloadLink);

        } catch (Exception e) {
            outputMessage(response, "Error uploading file: " + e.getMessage());
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotPosition = fileName.lastIndexOf('.');
        if (lastDotPosition < 0) {
            return "";
        }
        return fileName.substring(lastDotPosition + 1).toLowerCase();
    }

    private void outputMessage(HttpServletResponse response, String message) throws IOException {
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>" + message + "</h2>");
        response.getWriter().println("</body></html>");
    }

    private void outputSuccessMessage(HttpServletResponse response, String message, String downloadLink)
            throws IOException {
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>" + message + "</h2>");
        response.getWriter().println("File uploaded. Click <a href='" + downloadLink + "'>here</a> to visit file<br>");
        response.getWriter().println("</body></html>");
    }
}

