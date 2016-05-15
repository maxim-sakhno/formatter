package ru.sakhno.writer;


import java.io.*;


/**
 * Character file output stream.
 */
public class FileWriter implements IWriter, Closeable, Flushable {


    private OutputStreamWriter output;


    /**
     * Creates character output file stream.
     *
     * @param file - output file.
     * @throws IOException if an I/O error occurs.
     */
    public FileWriter(final File file) throws IOException {
        output = new OutputStreamWriter(new FileOutputStream(file));
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
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
        output.close();
    }


    /**
     * Flushes this stream by writing any buffered output to the underlying
     * stream.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void flush() throws IOException {
        output.flush();
    }
}
