package ru.sakhno.formatter;


import ru.sakhno.tockenizer.Token;


/**
 * Rule matching two tokens separator.
 */
public interface IRule {


    /**
     * Matching two tokens separator.
     *
     * @param first first token.
     * @param second second token.
     * @return separator.
     */
    Token getSeparator(final Token first, final Token second);


    /**
     * Returns indent level.
     *
     * @param first first token.
     * @param second second token.
     * @return indent level
     */
    Integer getIndent(final Token first, final Token second);


}
