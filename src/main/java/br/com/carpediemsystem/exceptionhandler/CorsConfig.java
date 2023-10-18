package br.com.carpediemsystem.exceptionhandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite CORS para todos os endpoints
                .allowedOrigins("http://localhost:4200") // origens permitidas
                .allowedMethods("GET", "POST", "PUT", "DELETE") // métodos HTTP permitidos
                .allowedHeaders("*") // todos os headers são permitidos
                .allowCredentials(true); // permite o envio de credenciais
    }
}

