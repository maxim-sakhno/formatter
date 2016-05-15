package ru.sakhno.formatter;


import ru.sakhno.reader.IReader;
import ru.sakhno.reader.ReaderException;
import ru.sakhno.tockenizer.ITokenizer;
import ru.sakhno.tockenizer.ReaderTokenizer;
import ru.sakhno.tockenizer.Token;
import ru.sakhno.writer.IWriter;
import ru.sakhno.writer.WriterException;
import java.util.*;


/**
 * Formats java code.
 */
public class JavaFormatter implements IFormatter {


    private static final int TAB_SIZE = 4;


    private IRule rule;


    public JavaFormatter(final IRule rule) {
        this.rule = rule;
    }


    /**
     * Formats java code from input stream to output stream.
     *
     * @param reader  - input stream.
     * @param writer - output stream.
     * @throws FormatterException if something happens.
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        ReaderTokenizer tokenizer = new ReaderTokenizer(reader, "\n\r\t {};()+-*/%<>=");
        Set<String> ignoreTokens = new HashSet<String>();
        ignoreTokens.add(" ");
        ignoreTokens.add("\n");
        ignoreTokens.add("\r");
        ignoreTokens.add("\t");
        Token first = new Token("");
        Token second = new Token("");
        int indentLevel = 0;
        try {
            if (tokenizer.hasNext()) {
                first = tokenizer.getToken();
            }
            while (tokenizer.hasNext()) {
                second = tokenizer.getToken();
                if (!ignoreTokens.contains(second.toString())) {
                    writer.write(first.toString());
                    Token separator = rule.getSeparator(first, second);
                    writer.write(separator.toString());
                    indentLevel = rule.getIndent(first, second);
                    if (indentLevel < 0) {
                        throw new FormatterException("Balance brakets exception.");
                    }
                    writer.write(getIndent(indentLevel));
                    first = second;
                }
            }
            writer.write(first.toString());
            indentLevel = rule.getIndent(first, second);
            if (indentLevel > 0) {
                throw new FormatterException("Balance brakets exception.");
            }
        } catch (Exception exception) {
            throw new FormatterException(exception);
        }
    }


    private String getIndent(final int nestringLevel) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nestringLevel * TAB_SIZE; ++i) {
            result.append(' ');
        }
        return result.toString();
    }
}
