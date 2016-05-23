package ru.sakhno.tockenizer;

import ru.sakhno.reader.IReader;

/**
 * The tokenizer for reader input stream.
 */
public class ReaderTokenizer implements ITokenizer {
    private String[] tokens;
    private IReader reader;
    private String buffer;
    private static final int BUFFER_STRING_LENGTH = 256;

    /**
     * Creates new reader tokenizer.
     *
     * @param tokens the tokens for finding in the input stream.
     * @param reader the input stream for breaking into the tokens.
     */
    public ReaderTokenizer(final String[] tokens, final IReader reader) {
        this.tokens = tokens;
        this.reader = reader;
        buffer = "";
    }

    /**
     * Test if token is available.
     *
     * @return true if token is available and false otherwise.
     * @throws TokenizerException if some error occurs.
     */
    public boolean hasNext() throws TokenizerException {
        boolean result;
        try {
            result = reader.hasNext() || buffer.length() != 0;
        } catch (Exception exception) {
            throw new TokenizerException(exception);
        }
        return result;
    }

    /**
     * Returns next token if he available and throw TokenizerException
     * if some error occurs or no tokens in the input stream.
     *
     * @return next token if he available.
     * @throws TokenizerException if some error occurs or no tokens
     *                            in the input stream.
     */
    public String getToken() throws TokenizerException {
        if (!hasNext()) {
            throw new TokenizerException("No tokens in the stream.");
        }
        if (buffer.length() < BUFFER_STRING_LENGTH) {
            buffer += readBufferString();
        }
        int tokenIndex = -1;
        int tokenLength = -1;
        try {
            for (int i = 0; i < tokens.length; ++i) {
                int tempIndex = buffer.indexOf(tokens[i]);
                if (tempIndex != -1 && (tokenIndex == -1 || tempIndex < tokenIndex)) {
                    tokenIndex = tempIndex;
                    tokenLength = tokens[i].length();
                }
            }
        } catch (Exception exception) {
            throw new TokenizerException(exception);
        }
        if (tokenIndex == -1) {
            String result = buffer;
            buffer = "";
            return result;
        }
        if (tokenIndex == 0) {
            String result = buffer.substring(0, tokenLength);
            buffer = buffer.substring(tokenLength, buffer.length());
            return result;
        }
        String result = buffer.substring(0, tokenIndex);
        buffer = buffer.substring(tokenIndex, buffer.length());
        return result;
    }

    private String readBufferString() throws TokenizerException {
        StringBuilder result = new StringBuilder();
        int readedCharCounter = 0;
        try {
            while (reader.hasNext() && readedCharCounter < BUFFER_STRING_LENGTH) {
                char temp = reader.read();
                result.append(temp);
                ++readedCharCounter;
            }
        } catch (Exception exception) {
            throw new TokenizerException(exception);
        }
        return result.toString();
    }
}
