package com.harbaoui.thesis_survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import jakarta.annotation.PostConstruct;
import java.io.File;


@SpringBootApplication
public class ThesisSurveyApplication {

    public static void main(String[] args) {
/*
    Dotenv dotenv = Dotenv.load();
    System.setProperty("spring.datasource.url", dotenv.get("SPRING_DATASOURCE_URL"));
    System.setProperty("spring.datasource.username", dotenv.get("SPRING_DATASOURCE_USERNAME"));
    System.setProperty("spring.datasource.password", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
    */
    SpringApplication.run(ThesisSurveyApplication.class, args);
}


    @PostConstruct
    public void init() {
        // Create audio directory if it doesn't exist
        File audioDir = new File("src/main/resources/static/audio");
        if (!audioDir.exists()) {
            audioDir.mkdirs();
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
