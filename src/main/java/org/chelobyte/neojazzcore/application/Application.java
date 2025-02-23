package org.chelobyte.neojazzcore.application;

import org.chelobyte.neojazzcore.config.Config;
import org.chelobyte.neojazzcore.exception.ApplicationBuildException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

abstract class Application {
    private static final Logger LOGGER
        = LoggerFactory.getLogger(Application.class);

    protected static AnnotationConfigApplicationContext context;
    protected JDABuilder apiBuilder;

    public void enableIntents(GatewayIntent... intents) {
        try {
            context = new AnnotationConfigApplicationContext(Config.class);
            apiBuilder = context.getBean("api", JDABuilder.class);

            for (GatewayIntent intent : intents) {
                apiBuilder.enableIntents(intent);
            }

            LOGGER.debug("Intents have been successfully enabled");
        } catch (Exception e) {
            LOGGER.error("Error enabling intents", e);
            throw new ApplicationBuildException("Error enabling intents");
        } finally {
            context.close();
        }
    }

    public void addEventListeners(Object... listeners) {
        try {
            context = new AnnotationConfigApplicationContext(Config.class);
            apiBuilder = context.getBean("api", JDABuilder.class);

            for (Object listener : listeners) {
                apiBuilder.addEventListeners(listener);
            }

            LOGGER.debug("Event listeners have been successfully added");
        } catch (Exception e) {
            LOGGER.error("Error adding event listener", e);
            throw new ApplicationBuildException("Error adding event listener");
        } finally {
            context.close();
        }
    }
}
