package org.chelobyte.neojazzcore.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.chelobyte.neojazzcore.annotation.IsBean;
import org.chelobyte.neojazzcore.exception.ApplicationBuildException;
import org.chelobyte.neojazzcore.listener.command.slash.SlashCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

@IsBean
public final class OnSlashCommandListener extends ListenerAdapter {
    private static final Logger LOGGER
        = LoggerFactory.getLogger(OnSlashCommandListener.class);

    private final String GUILD_ID;

    private Map<String, SlashCommand> commands = new HashMap<>();

    public OnSlashCommandListener(String guildID,
            Set<SlashCommand> slashCommands) {
        this.GUILD_ID = guildID;

        slashCommands.forEach(
                command -> this.commands.put(command.getName(), command));
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("OnSlashCommandListener bean is initialized");
    }

    @Override
    public void onReady(ReadyEvent event) {
        try {
            final Guild GUILD = event.getJDA().getGuildById(this.GUILD_ID);

            updateCommands(GUILD);
        } catch (Exception e) {
            LOGGER.error("Error updating slash commands", e);
            throw new ApplicationBuildException("Error with being ready");
        }
    }

    private void updateCommands(Guild guild) {
        guild.updateCommands().addCommands(getListOfCommands())
                .queue(success -> {
                    LOGGER.debug("Slash commands are added");
                }, error -> {
                    LOGGER.error("Error adding slash commands: "
                            + error.getMessage());
                });
    }

    private List<CommandData> getListOfCommands() {
        return new ArrayList<>(commands.values()).stream()
                .map(cmd -> Commands.slash(cmd.getName(), cmd.getDescription())
                        .addOptions(cmd.getOptions())
                        .setDefaultPermissions(DefaultMemberPermissions
                                .enabledFor(cmd.getPermissions())))
                .collect(Collectors.toList());
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
