package ru.sakhno.formatter;


import ru.sakhno.reader.IReader;
import ru.sakhno.reader.ReaderException;
import ru.sakhno.writer.IWriter;
import ru.sakhno.writer.WriterException;


/**
 * Formats java code.
 */
public class JavaFormatter implements IFormatter {


    private static final int TAB_SIZE = 4;


    /**
     * Formats input stream to output stream.
     *
     * @param input  - input stream.
     * @param output - output stream.
     * @throws FormatterException if something happens.
     */
    public void format(final IReader input, final IWriter output) throws FormatterException {
        try {
            StringBuilder line = new StringBuilder();
            int nestingLevel = 0;
            boolean isSpace = false;
            while (input.hasNext()) {
                char symbol = input.read();
                switch (symbol) {
                    case '{':
                        output.write(getIndent(nestingLevel) + line + " {\n");
                        ++nestingLevel;
                        line.delete(0, line.length());
                        isSpace = false;
                        break;
                    case '}':
                        --nestingLevel;
                        if (nestingLevel < 0) {
                            throw new FormatterException("Infringed balance brackets.");
                        }
                        output.write(getIndent(nestingLevel) + "}\n");
                        line.delete(0, line.length());
                        isSpace = false;
                        break;
                    case ';':
                        output.write(getIndent(nestingLevel) + line + ";\n");
                        line.delete(0, line.length());
                        isSpace = false;
                        break;
                    case ' ':
                        if (line.length() != 0) {
                            isSpace = true;
                        }
                        break;
                    case 10:
                    case 13:
                        break;
                    case '+':
                    case '-':
                    case '/':
                    case '*':
                    case '%':
                    case '&':
                    case '|':
                    case '(':
                        if (line.length() != 0) {
                            line.append(" ");
                        }
                        line.append(symbol);
                        isSpace = true;
                        break;
                    case ')':
                        line.append(") ");
                        break;
                    default:
                        if (isSpace) {
                            line.append(' ');
                            isSpace = false;
                        }
                        line.append(symbol);
                }
            }
            if (nestingLevel > 0) {
                throw new FormatterException("Infringed balance brackets.");
            }
        } catch (ReaderException exception) {
            throw new FormatterException(exception);
        } catch (WriterException exception) {
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
