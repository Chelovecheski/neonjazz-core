package org.chelobyte.neojazzcore.config;

import org.chelobyte.neojazzcore.util.EnvReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {
    @Value("${token}")
    private String tokenPropertyValue;

    @Bean
    public EnvReader envReader() {
        return new EnvReader(tokenPropertyValue);
    }
}