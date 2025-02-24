package org.chelobyte.neojazzcore.exception;

public class CommandNotFound extends RuntimeException {
    public CommandNotFound() {
        super();
    }

    public CommandNotFound(String message) {
        super(message);
    }
}
