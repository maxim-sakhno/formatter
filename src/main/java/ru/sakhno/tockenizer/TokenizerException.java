package ru.sakhno.tockenizer;

/**
 * The exception for tokenizer.
 */
public class TokenizerException extends Exception {

    /**
     * Constructs new tokenizer exception with
     * the other exception.
     *
     * @param exception the other exception.
     */
    TokenizerException(final Exception exception) {
        super(exception);
    }

    /**
     * Constructs new tokenizer exception with
     * the specified detail message.
     *
     * @param message the message specified detail.
     */
    TokenizerException(final String message) {
        super(message);
    }

}
