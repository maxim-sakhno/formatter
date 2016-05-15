package ru.sakhno.tockenizer;


/**
 * Exception for tokenizer.
 */
public class TokenizerException extends Exception {


    /**
     * Creates TokenizerException with exception.
     *
     * @param exception - other exception.
     */
    public TokenizerException(final Exception exception) {
        super(exception);
    }


    /**
     * Creates TokenizerException with message.
     *
     * @param message - error description.
     */
    public TokenizerException(final String message) {
        super(message);
    }


}
