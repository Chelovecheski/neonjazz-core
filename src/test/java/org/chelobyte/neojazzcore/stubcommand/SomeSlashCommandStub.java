package org.chelobyte.neojazzcore.stubcommand;

import java.util.List;

import org.chelobyte.neojazzcore.listener.command.slash.SlashCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SomeSlashCommandStub extends SlashCommand {
    public SomeSlashCommandStub() {
        super("someCommandStub", "Some command stub", List.of(), List.of());
    }

    @Override
    public void execute(SlashCommandInteractionEvent event, long requestId) {
        return;
    }
}
