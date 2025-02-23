package org.chelobyte.neojazzcore.application;

import org.chelobyte.neojazzcore.exception.ApplicationBuildException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.JDA;

public final class ApplicationStarter extends Application {
    private static final Logger LOGGER
        = LoggerFactory.getLogger(ApplicationStarter.class);

    public void start() {
        try {
            JDA api = apiBuilder.build();

            LOGGER.debug("JDA api has been successfully built");

            api.awaitReady();

            LOGGER.info("Application has been successfully started");
        } catch (Exception e) {
            LOGGER.error("Error starting application", e);
            throw new ApplicationBuildException("Error starting application");
        }
    }
}
