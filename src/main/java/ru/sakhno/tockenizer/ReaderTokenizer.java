package ru.sakhno.tockenizer;


import ru.sakhno.reader.IReader;


/**
 * Tokenizer using reader interface to parsing stream.
 */
public class ReaderTokenizer implements ITokenizer {


    private IReader reader;
    private String separators;
    private StringBuilder token;


    /**
     * Creates tokenizer for reader.
     * Returns strings between separators, separators.
     *
     * @param reader - stream for parsing.
     * @param separators - separators for parsing.
     */
    public ReaderTokenizer(final IReader reader, final String separators) {
        this.reader = reader;
        this.separators = separators;
        token = new StringBuilder();
    }


    /**
     * Checks next token.
     *
     * @return true if has next token.
     * @throws TokenizerException if checking error occur.
     */
    public boolean hasNext() throws TokenizerException {
        boolean result;
        try {
            result = reader.hasNext() || token.length() != 0;
        } catch (Exception exception) {
            throw new TokenizerException(exception);
        }
        return result;
    }


    /**
     * Return next token if it exist.
     *
     * @return next token.
     * @throws TokenizerException if no tokens to return.
     */
    public Token getToken() throws TokenizerException {
        if (!hasNext() && token.length() == 0) {
            throw new TokenizerException("No token.");
        }
        if (token.length() != 0) {
            Token result = new Token(token.toString());
            token.delete(0, token.length());
            return result;
        }
        char simbol = 0;
        try {
            simbol = reader.read();
            while (reader.hasNext() && separators.indexOf(simbol) == -1) {
                token.append(simbol);
                simbol = reader.read();
            }
            if (separators.indexOf(simbol) == -1) {
                token.append(simbol);
            }
        } catch (Exception exception) {
            throw new TokenizerException(exception);
        }
        if (token.length() == 0) {
            return new Token(Character.toString(simbol));
        }
        if (separators.indexOf(simbol) != -1) {
            Token result = new Token(token.toString());
            token.delete(0, token.length());
            token.append(simbol);
            return result;
        }
        Token result = new Token(token.toString());
        token.delete(0, token.length());
        return result;
    }


}
