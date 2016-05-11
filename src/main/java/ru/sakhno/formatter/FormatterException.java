package ru.sakhno.formatter;


/**
 * Exception for formatter.
 */
public class FormatterException extends Exception {


    /**
     * Creates FormatterException with other exception.
     *
     * @param exception - other exception.
     */
    public FormatterException(final Exception exception) {
        super(exception);
    }


    /**
     * Creates FormatterException with message.
     *
     * @param message - exception description.
     */
    public FormatterException(final String message) {
        super(message);
    }


}
