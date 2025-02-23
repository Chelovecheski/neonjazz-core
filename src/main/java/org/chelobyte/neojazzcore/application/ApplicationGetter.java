package org.chelobyte.neojazzcore.application;

import org.chelobyte.neojazzcore.config.Config;
import org.chelobyte.neojazzcore.exception.ApplicationBuildException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class ApplicationGetter extends Application {
    private static final Logger LOGGER
        = LoggerFactory.getLogger(ApplicationGetter.class);

    public static Object getBean(String beanName, Class<?> clazz) {
        try {
            context = new AnnotationConfigApplicationContext(Config.class);
            return context.getBean(beanName, clazz);
        } catch (Exception e) {
            LOGGER.error("Error getting bean", e);
            throw new ApplicationBuildException("Error getting bean");
        }
    }

    public static void closeContext() {
        try {
            context.close();
        } catch (Exception e) {
            LOGGER.error("Error closing context", e);
        }
    }
}
