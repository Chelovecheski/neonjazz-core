package org.chelobyte.neojazzcore.util;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.cdimascio.dotenv.Dotenv;

@ExtendWith(MockitoExtension.class)
class EnvReaderTest {
    private EnvReaderStub envReaderStub;

    @Mock
    private Dotenv dotenvMock;

    @BeforeEach
    void setUp() {
        envReaderStub = new EnvReaderStub(dotenvMock);
    }

    @Test
    void shouldReturnTOKEN() {
        // given
        when(dotenvMock.get("someTokenValue")).thenReturn("someTOKEN");

        // when
        String result = envReaderStub.getTOKEN();

        // then
        assertThat(result, is("someTOKEN"));
    }

    @Test
    void shouldReturnEmptyStringWhenGetTokenFails() {
        // given
        when(dotenvMock.get("someTokenValue")).thenThrow(new RuntimeException());

        // when
        String result = envReaderStub.getTOKEN();

        // then
        assertThat(result, is(" "));
    }
}