package ru.sakhno.reader;


/**
 * Exception for reader.
 */
public class ReaderException extends Exception {


    /**
     * Creates ReaderException with other exception.
     *
     * @param exception - other exception.
     */
    public ReaderException(final Exception exception) {
        super(exception);
    }


}
