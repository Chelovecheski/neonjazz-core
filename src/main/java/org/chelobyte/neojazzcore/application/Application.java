package org.chelobyte.neojazzcore.application;

import org.chelobyte.neojazzcore.config.Config;
import org.chelobyte.neojazzcore.exception.ApplicationBuildException;
import org.chelobyte.neojazzcore.util.EnvReader;
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

    public Application() {
        context = new AnnotationConfigApplicationContext(Config.class);
        EnvReader envReader = context.getBean("envReader", EnvReader.class);

        apiBuilder = JDABuilder.createDefault(envReader.getTOKEN());
        context.close();
    }

    public void enableIntents(GatewayIntent... intents) {
        try {
            for (GatewayIntent intent : intents) {
                apiBuilder.enableIntents(intent);
            }

            LOGGER.debug("Intents have been successfully enabled");
        } catch (Exception e) {
            LOGGER.error("Error enabling intents", e);
            throw new ApplicationBuildException("Error enabling intents");
        }
    }

    public void addEventListeners(Object... listeners) {
        try {
            for (Object listener : listeners) {
                apiBuilder.addEventListeners(listener);
            }

            LOGGER.debug("Event listeners have been successfully added");
        } catch (Exception e) {
            LOGGER.error("Error adding event listener", e);
            throw new ApplicationBuildException("Error adding event listener");
        }
    }
}
