package ru.sakhno.formatter;

/**
 * Describes formatter state.
 */
public interface IFormatterState {
    /**
     * Returns string as a result handling the token.
     *
     * @param token the token for handle.
     * @return string as a result handling the token.
     * @throws FormatterException if some error occurs.
     */
    String handleToken(final String token) throws FormatterException;

    /**
     * Returns new state for formatter.
     *
     * @param token the token for choosing new state.
     */
    IFormatterState getState(final String token);
}
