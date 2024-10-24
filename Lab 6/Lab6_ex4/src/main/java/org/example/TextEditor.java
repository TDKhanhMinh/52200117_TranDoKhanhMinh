package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TextEditor {
    private TextWriter textWriter;
    private String inputText;

    @Autowired
    @Qualifier("pdfTextWriter")
    public void setTextWriter(TextWriter textWriter) {
        this.textWriter = textWriter;
    }

    @Autowired
    @Qualifier("plainTextWriter")
    public void SetTextWriter(TextWriter textWriter) {
        this.textWriter = textWriter;
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to write:");
        inputText = scanner.nextLine();
    }

    public void save(String fileName) {
        textWriter.write(fileName, inputText);

    }
}
