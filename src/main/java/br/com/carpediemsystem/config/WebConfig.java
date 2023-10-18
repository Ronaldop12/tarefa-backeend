package br.com.carpediemsystem.config;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.carpediemsystem.deserializer.CustomBigDecimalDeserializer;

@Configuration
public class WebConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BigDecimal.class, new CustomBigDecimalDeserializer());
        builder.modulesToInstall(module);
        return builder;
    }
}
