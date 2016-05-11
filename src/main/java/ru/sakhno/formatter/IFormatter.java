package ru.sakhno.formatter;


import ru.sakhno.reader.IReader;
import ru.sakhno.writer.IWriter;


/**
 * Format stream.
 */
public interface IFormatter {


    /**
     * Formats input stream to output stream.
     *
     * @param input - input stream.
     * @param output - output stream.
     * @throws FormatterException if something happens.
     */
    void format(final IReader input, final IWriter output) throws FormatterException;


}
