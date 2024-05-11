package com.example.boardbackend.config;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
