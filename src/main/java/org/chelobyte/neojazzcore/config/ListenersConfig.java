package org.chelobyte.neojazzcore.config;

import org.chelobyte.neojazzcore.listener.OnSlashCommandListener;
import org.chelobyte.neojazzcore.listener.factory.SlashCommandsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenersConfig {
    private static final Logger LOGGER
        = LoggerFactory.getLogger(ListenersConfig.class);

    @Value("${guild.id}")
    private String guildID;

    @Bean("slashCommandsListener")
    public OnSlashCommandListener slashCommandsListener() {
        try {
            return new OnSlashCommandListener(guildID,
                    SlashCommandsFactory.getSlashCommands());
        } catch (Exception e) {
            LOGGER.error("Error creating OnSlashCommandListener", e);
            throw new RuntimeException(e);
        }
    }
}
