package ru.sakhno.tockenizer;


/**
 * This token equal any other token.
 */
public class AnyToken extends Token {


    /**
     * Default constructor for token.
     *
     * @param string string for token.
     */
    public AnyToken(final String string) {
        super(string);
    }


    /**
     * Returns true if argument is token.
     *
     * @param other other token.
     * @return true if argument is token.
     */
    public boolean equals(final Object other) {
        if (other instanceof Token) {
            return true;
        }
        return false;
    }


}
