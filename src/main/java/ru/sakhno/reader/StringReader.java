package ru.sakhno.reader;


/**
 * Character input stream from string.
 */
public class StringReader implements IReader {


    private String string;
    private int currentIndex;


    /**
     * Creates stream for the string.
     *
     * @param string - input string.
     */
    public StringReader(final String string) {
        this.string = string;
        currentIndex = 0;
    }


    /**
     * Checks end of stream.
     *
     * @return true if stream has next character, false otherwise.
     * @throws ReaderException if something happens.
     */
    public boolean hasNext() throws ReaderException {
        try {
            return currentIndex != string.length();
        } catch (Exception exception) {
            throw new ReaderException(exception);
        }
    }


    /**
     * Reads the next character from the stream.
     *
     * @return the next character from the stream.
     * @throws ReaderException if something happens.
     */
    public char read() throws ReaderException {
        char result;
        try {
            result = string.charAt(currentIndex);
        } catch (Exception exception) {
            throw new ReaderException(exception);
        }
        ++currentIndex;
        return result;
    }


}
