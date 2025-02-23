package org.chelobyte.neojazzcore.config;

import org.chelobyte.neojazzcore.util.EnvReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import net.dv8tion.jda.api.JDABuilder;

@Configuration
@Import(ListenersConfig.class)
@PropertySource("classpath:application.properties")
public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    @Value("${token}")
    private String tokenPropertyValue;

    @Bean("envReader")
    public EnvReader envReader() {
        try {
            return new EnvReader(tokenPropertyValue);
        } catch (Exception e) {
            LOGGER.error("Error creating EnvReader", e);
            throw new RuntimeException(e);
        }
    }

    @Bean("api")
    public JDABuilder jdaBuilder(EnvReader envReader) {
        try {
            return JDABuilder.createDefault(envReader.getTOKEN());
        } catch (Exception e) {
            LOGGER.error("Error creating JDA builder", e);
            throw new RuntimeException(e);
        }
    }
}