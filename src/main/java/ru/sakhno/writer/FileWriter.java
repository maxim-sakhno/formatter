package ru.sakhno.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Character file output stream.
 */
public class FileWriter implements IWriter {

    private OutputStreamWriter output;

    /**
     * Creates character output file stream.
     *
     * @param file - output file.
     * @throws WriterException if an error occurs.
     */
    public FileWriter(final File file) throws WriterException {
        try {
            output = new OutputStreamWriter(new FileOutputStream(file));
        } catch (Exception exception) {
            throw new WriterException(exception);
        }
    }

    /**
     * Writes string to stream.
     *
     * @param string output string.
     * @throws WriterException if something happens.
     */
    public void write(final String string) throws WriterException {
        try {
            output.write(string);
        } catch (Exception exception) {
            throw new WriterException(exception);
        }
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * @throws WriterException if an error occurs
     */
    public void close() throws WriterException {
        try {
            output.close();
        } catch (Exception exception) {
            throw new WriterException(exception);
        }
    }

    /**
     * Flushes this stream by writing any buffered output to the underlying
     * stream.
     *
     * @throws WriterException If an error occurs.
     */
    public void flush() throws WriterException {
        try {
            output.flush();
        } catch (Exception exception) {
            throw new WriterException(exception);
        }
    }

}
