package ru.sakhno.formatter;

/**
 * Exception for formatter.
 */
public class FormatterException extends Exception {
    /**
     * Creates exception with message.
     *
     * @param message the message describing the error.
     */
    public FormatterException(final String message) {
        super(message);
    }

    /**
     * Creates exception based other exception.
     *
     * @param exception the other exception describing the error.
     */
    public FormatterException(final Exception exception) {
        super(exception);
    }
}
