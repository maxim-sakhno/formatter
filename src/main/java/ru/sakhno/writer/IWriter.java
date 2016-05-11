package ru.sakhno.writer;


/**
 * Interface for character output stream.
 */
public interface IWriter {


    /**
     * Writes string to stream.
     *
     * @param string output string.
     * @throws WriterException if something happens.
     */
    void write(final String string) throws WriterException;


}
