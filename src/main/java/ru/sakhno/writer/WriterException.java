package ru.sakhno.writer;


/**
 * Exception for Writer.
 */
public class WriterException extends Exception {


    /**
     * Creates WriterExcecption with other exception.
     *
     * @param exception - other exception.
     */
    public WriterException(final Exception exception) {
        super(exception);
    }


}
