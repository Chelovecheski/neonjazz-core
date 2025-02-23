package org.chelobyte.neojazzcore.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.chelobyte.neojazzcore.annotation.IsBean;
import org.chelobyte.neojazzcore.listener.command.slash.SlashCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@IsBean
public final class OnSlashCommandListener extends ListenerAdapter {
    private static final Logger LOGGER
        = LoggerFactory.getLogger(OnSlashCommandListener.class);

    private Map<String, SlashCommand> commands = new HashMap<>();

    public OnSlashCommandListener(Set<SlashCommand> slashCommands) {
        slashCommands.forEach(
                command -> this.commands.put(command.getName(), command));
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        LOGGER.debug(
                "Slash command has been triggered /{} <{}>. User: {}#{} ({}). Channel: {} ({})",
                event.getName(), event.getOptions(), event.getUser().getName(),
                event.getUser().getDiscriminator(), event.getUser().getId(),
                event.getChannel().getName(), event.getChannel().getId());

        try {
            final SlashCommand COMMAND = commands.get(event.getName());

            if (COMMAND != null) {
                COMMAND.execute(event);
            }
        } catch (Exception e) {
            LOGGER.error("Error executing slash command", e);
        }
    }
}
