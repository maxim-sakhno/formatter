package ru.sakhno.tockenizer;


/**
 * Interface describes parser into words.
 */
public interface ITokenizer {


    /**
     * Checks next token.
     *
     * @return true if has next token.
     * @throws TokenizerException if checking error occur.
     */
    boolean hasNext() throws TokenizerException;


    /**
     * Return next token.
     *
     * @return next token.
     * @throws TokenizerException if no tokens to return.
     */
    Token getToken() throws TokenizerException;


}
