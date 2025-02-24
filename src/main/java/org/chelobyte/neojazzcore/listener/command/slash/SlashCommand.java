package org.chelobyte.neojazzcore.listener.command.slash;

import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public abstract class SlashCommand {
    protected final String name;
    protected final String description;
    protected final List<OptionData> options;
    protected final List<Permission> permissions;

    public SlashCommand(String name, String description,
            List<OptionData> options, List<Permission> permissions) {
        this.name = name;
        this.description = description;
        this.options = options;
        this.permissions = permissions;
    }

    public abstract void execute(SlashCommandInteractionEvent event,
            long requestId);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<OptionData> getOptions() {
        return options;
    }

    public Collection<Permission> getPermissions() {
        return permissions;
    }
}
