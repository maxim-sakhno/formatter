package ru.sakhno.tockenizer;

/**
 * Describe tokenizer that breaks input stream into tokens.
 */
public interface ITokenizer {
    /**
     * Test if token is available.
     *
     * @return true if token is available and false otherwise.
     * @throws TokenizerException if some error occurs.
     */
    boolean hasNext() throws TokenizerException;

    /**
     * Returns next token if he available and throw TokenizerException
     * if some error occurs or no tokens in the input stream.
     *
     * @return next token if he available.
     * @throws TokenizerException if some error occurs or no tokens
     * in the input stream.
     */
    String getToken() throws TokenizerException;
}
