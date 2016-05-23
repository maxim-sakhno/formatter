package ru.sakhno.formatter;

import ru.sakhno.tockenizer.ITokenizer;
import ru.sakhno.writer.IWriter;

/**
 * Describes formatter which format input stream
 * from the tokenizer to output stream from the writer.
 */
public interface IFormatter {
    /**
     * Formats input stream from the tokenizer
     * to output stream from the writer.
     *
     * @param tokenizer the tokenizer to input.
     * @param writer the writer to output.
     * @throws FormatterException if some error occur.
     */
    void format(final ITokenizer tokenizer, IWriter writer) throws FormatterException;
}
