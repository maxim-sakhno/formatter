package ru.sakhno.tockenizer;

/**
 * What returns tokenizer.
 * Wrapper class for string.
 */
public class Token {


    private String string;


    /**
     * Creates Token with string.
     *
     * @param string - string for Token.
     */
    public Token(final String string) {
        this.string = string;
    }


    /**
     * Returns string containing Token.
     *
     * @return string containing Token.
     */
    public String toString() {
        return string;
    }


    /**
     * Compare this token with other token.
     *
     * @param other other token.
     * @return true if token are equals.
     */
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Token) {
            return string.equals(((Token) other).string);
        }
        return false;
    }
}
