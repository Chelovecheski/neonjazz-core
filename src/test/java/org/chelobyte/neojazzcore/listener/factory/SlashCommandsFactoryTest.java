package org.chelobyte.neojazzcore.listener.factory;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.chelobyte.neojazzcore.stubcommand.SomeSlashCommandStub;
import org.junit.jupiter.api.Test;

class SlashCommandsFactoryTest {
    @Test
    void shouldAddSlashCommandsToSet() {
        // given
        SomeSlashCommandStub slashCommandStub = new SomeSlashCommandStub();
        SlashCommandsFactory.addSlashCommands(slashCommandStub);

        // when
        // then
        assertThat(SlashCommandsFactory.getSlashCommands(), is(not(empty())));
    }
}
