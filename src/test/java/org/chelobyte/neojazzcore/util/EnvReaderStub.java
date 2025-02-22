package org.chelobyte.neojazzcore.util;

import io.github.cdimascio.dotenv.Dotenv;

class EnvReaderStub {
    private final Dotenv DOTENV;

    EnvReaderStub(Dotenv dotenv) {
        this.DOTENV = dotenv;
    }

    String getTOKEN() {
        try {
            String token = DOTENV.get("someTokenValue");
            return token;
        } catch (Exception e) {
            return " ";
        }
    }
}
