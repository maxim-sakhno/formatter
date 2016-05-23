package ru.sakhno.formatter;

import ru.sakhno.tockenizer.ITokenizer;
import ru.sakhno.writer.IWriter;

/**
 * Formats java code.
 */
public class Formatter implements IFormatter {
    private IFormatterState currentState;
    private int indentLevel;

    public Formatter() {
        currentState = new NormalState(this);
        indentLevel = 0;
    }
    /**
     * Formats input stream from the tokenizer
     * to output stream from the writer.
     *
     * @param tokenizer the tokenizer to input.
     * @param writer    the writer to output.
     * @throws FormatterException if some error occur.
     */
    public void format(final ITokenizer tokenizer, final IWriter writer) throws FormatterException {
        try {
            while (tokenizer.hasNext()) {
                String token = tokenizer.getToken();
                String result = currentState.handleToken(token);
                writer.write(result);
                currentState = currentState.getState(token);
            }
        } catch (Exception exception) {
            throw new FormatterException(exception);
        }
    }

    public void setState(final IFormatterState newState) {
        currentState = newState;
    }

    public int getIndentLevel() {
        return indentLevel;
    }

    public void changeIndentLevel(final int delta) throws FormatterException {
        indentLevel += delta;
        if (indentLevel < 0) {
            throw new FormatterException("Balance brackets error.");
        }
    }
}
