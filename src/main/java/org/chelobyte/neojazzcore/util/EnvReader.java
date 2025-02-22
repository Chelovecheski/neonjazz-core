package org.chelobyte.neojazzcore.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import java.util.Optional;
import org.chelobyte.neojazzcore.annotation.IsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@IsBean
public final class EnvReader {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(EnvReader.class);
    private static final Dotenv DOTENV = Dotenv.load();

    private final String tokenPropertyValue;

    public EnvReader(String tokenPropertyValue) {
        this.tokenPropertyValue = tokenPropertyValue;
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("EnvReader has been successfully initialized");
    }

    public Optional<String> getTOKEN() {
        LOGGER.debug("Attempting to get token from property {}", tokenPropertyValue);

        try {
            return Optional.ofNullable(DOTENV.get(tokenPropertyValue));
        } catch (Exception e) {
            LOGGER.error("Error getting token: {}", e);
            return Optional.empty();
        }
    }
}