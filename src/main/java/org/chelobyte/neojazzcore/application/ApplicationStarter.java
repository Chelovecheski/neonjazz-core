package org.chelobyte.neojazzcore.application;

import org.chelobyte.neojazzcore.config.Config;
import org.chelobyte.neojazzcore.exception.ApplicationBuildException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.dv8tion.jda.api.JDABuilder;

public final class ApplicationStarter extends Application {
    private static final Logger LOGGER
        = LoggerFactory.getLogger(ApplicationStarter.class);

    public void start() {
        try {
            context = new AnnotationConfigApplicationContext(Config.class);
            apiBuilder = context.getBean("api", JDABuilder.class);

            apiBuilder.build();

            LOGGER.info("Application has been successfully started");
        } catch (Exception e) {
            LOGGER.error("Error starting application", e);
            throw new ApplicationBuildException("Error starting application");
        } finally {
            context.close();
        }
    }
}
