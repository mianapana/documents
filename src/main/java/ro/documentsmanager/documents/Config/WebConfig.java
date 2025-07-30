package ro.documentsmanager.documents.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // toate endpoint-urile
                        .allowedOrigins("http://localhost:5173") // frontend vite
                        .allowedMethods("*") // GET, POST, PUT, DELETE etc.
                        .allowedHeaders("*");
            }
        };
    }
}