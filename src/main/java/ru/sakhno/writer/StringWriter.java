package ru.sakhno.writer;


/**
 * Character output stream for string.
 */
public class StringWriter implements IWriter {


    private StringBuilder stringBuilder;


    /**
     * Default constructor.
     */
    public StringWriter() {
        stringBuilder = new StringBuilder();
    }


    /**
     * Writes string to stream.
     *
     * @param string output string.
     * @throws WriterException if something happens.
     */
    public void write(final String string) throws WriterException {
        if (string == null) {
            throw new WriterException(new NullPointerException("Argument is null."));
        }
        try {
            stringBuilder.append(string);
        } catch (Exception exception) {
            throw new WriterException(exception);
        }
    }


    /**
     * Returns result string.
     * @return resutl string.
     */
    public String toString() {
        return stringBuilder.toString();
    }


}
