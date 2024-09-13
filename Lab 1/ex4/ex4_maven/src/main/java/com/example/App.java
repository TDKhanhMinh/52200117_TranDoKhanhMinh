package com.example;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify an URL to a file");
            return;
        }

        String urlString = args[0];
        UrlValidator urlValidator = new UrlValidator();

        if (!urlValidator.isValid(urlString)) {
            System.out.println("This is not a valid URL");
            return;
        }

        try {
            URL url = new URL(urlString);
            String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
            File destination = new File(fileName);
            FileUtils.copyURLToFile(url, destination);
            System.out.println("File downloaded successfully: " + fileName);
        } catch (Exception e) {
            System.out.println("An error occurred while downloading the file: " + e.getMessage());
        }
    }
}
