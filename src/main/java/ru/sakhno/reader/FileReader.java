package ru.sakhno.reader;


import java.io.*;


/**
 * Character file input stream.
 */
public class FileReader implements IReader {


    private InputStreamReader input;
    private int currentChar;


    /**
     * Open character input stream from file.
     *
     * @param file - input file.
     * @throws IOException if an I/O error occurs.
     */
    public FileReader(final File file) throws ReaderException {
        try {
            input = new InputStreamReader(new FileInputStream(file));
            currentChar = input.read();
        } catch (Exception exception) {
            throw new ReaderException(exception);
        }
    }


    /**
     * Checks end of stream.
     *
     * @return true if stream has next character, false otherwise.
     * @throws ReaderException if something happens.
     */
    public boolean hasNext() throws ReaderException {
        return currentChar != -1;
    }


    /**
     * Reads the next character from the stream.
     *
     * @return the next character from the stream.
     * @throws ReaderException if something happens.
     */
    public char read() throws ReaderException {
        char result = (char) currentChar;
        try {
            currentChar = input.read();
        } catch (Exception exception) {
            throw new ReaderException(exception);
        }
        return result;
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void close() throws ReaderException {
        try {
            input.close();
        } catch (Exception exception) {
            throw new ReaderException(exception);
        }
    }
}
