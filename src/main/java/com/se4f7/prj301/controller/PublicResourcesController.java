package com.se4f7.prj301.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/public/*")
public class PublicResourcesController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle("application");
        String uploadDir = rb.getString("uploadDir");

        // Ensure the path is properly decoded and sanitized
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        File file = new File(uploadDir, filename);

        if (!file.exists() || file.isDirectory()) {
            String absoluteFilePath = getServletContext().getRealPath("images/page-404.jpeg");
            file = new File(absoluteFilePath);
        }

        if (!file.exists()) {
            // If the fallback file also does not exist, return 404 error
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        try {
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (NoSuchFileException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + filename);
        }
    }
}
