package org.chelobyte.neojazzcore.util;

import org.chelobyte.neojazzcore.annotation.IsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@IsBean
public final class EnvReader {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(EnvReader.class);

    @Value("${token}")
    private String tokenPropertyValue;

    @PostConstruct
    public void init() {
        LOGGER.debug("EnvReader has been successfully initialized");
    }

    public String getTOKEN() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get(tokenPropertyValue);
    }
}