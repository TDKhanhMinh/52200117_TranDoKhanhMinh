package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class appConfig {

    @Bean
    public TextWriter plainTextWriter() {
        return new PlainTextWriter();
    }

    @Bean
    public TextEditor textEditor() {
        return new TextEditor();
    }

    @Bean
    public TextWriter pdfTextWriter() {
        return new PDFTextWriter();
    }

}
