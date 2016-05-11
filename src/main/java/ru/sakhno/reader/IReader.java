package ru.sakhno.reader;


/**
 * Character input stream interface.
 */
public interface IReader {


    /**
     * Checks end of stream.
     *
     * @return true if stream has next character, false otherwise.
     * @throws ReaderException if something happens.
     */
    boolean hasNext() throws ReaderException;


    /**
     * Reads the next character from the stream.
     *
     * @return the next character from the stream.
     * @throws ReaderException if something happens.
     */
    char read() throws ReaderException;


}
