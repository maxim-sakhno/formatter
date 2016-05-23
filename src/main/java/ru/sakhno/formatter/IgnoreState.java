package ru.sakhno.formatter;

/**
 * No handling token in this state.
 */
public class IgnoreState implements IFormatterState {
    private Formatter formatter;
    private String endToken;

    public IgnoreState(final Formatter formatter, final String endToken) {
        this.formatter = formatter;
        this.endToken = endToken;
    }

    /**
     * Returns string as a result handling the token.
     *
     * @param token the token for handle.
     * @return string as a result handling the token.
     * @throws FormatterException if some error occurs.
     */
    public String handleToken(final String token) throws FormatterException {
        return token;
    }

    /**
     * Returns new state for formatter.
     *
     * @param token the token for choosing new state.
     */
    public IFormatterState getState(final String token) {
        if (token.equals(endToken)) {
            return new NormalState(formatter);
        }
        return this;
    }
}
