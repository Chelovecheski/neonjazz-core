package org.chelobyte.neojazzcore.listener.factory;

import java.util.HashSet;
import java.util.Set;

import org.chelobyte.neojazzcore.listener.command.slash.SlashCommand;

public final class SlashCommandsFactory {
    private static Set<SlashCommand> slashCommands = new HashSet<>();

    public static void addSlashCommands(SlashCommand... commands) {
        for (SlashCommand command : commands) {
            slashCommands.add(command);
        }
    }

    public static Set<SlashCommand> getSlashCommands() {
        return slashCommands;
    }
}
