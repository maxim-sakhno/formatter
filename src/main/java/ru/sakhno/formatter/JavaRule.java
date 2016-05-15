package ru.sakhno.formatter;


import javafx.util.Pair;
import ru.sakhno.tockenizer.Token;

import java.util.*;


/**
 * Java rule for tokens.
 */
public class JavaRule implements IRule {


    private List<Pair<Pair<Token, Token>, Token>> mappingSeparator;
    private List<Pair<Pair<Token, Token>, Integer>> mappingIndent;
    private int indentLevel;


    public JavaRule(final List<Pair<Pair<Token, Token>, Token>> mapping, List<Pair<Pair<Token, Token>, Integer>> mappingIndent) {
        this.mappingSeparator = mapping;
        this.mappingIndent = mappingIndent;
        indentLevel = 0;
    }


    /**
     * Matching two tokens separator.
     *
     * @param first  first token.
     * @param second second token.
     * @return separator.
     */
    public Token getSeparator(final Token first, final Token second) {
        Iterator<Pair<Pair<Token, Token>, Token>> iterator = mappingSeparator.iterator();
        while (iterator.hasNext()) {
            Pair<Pair<Token, Token>, Token> entry = iterator.next();
            if (entry.getKey().getKey().equals(first) && entry.getKey().getValue().equals(second)) {
                return entry.getValue();
            }
        }
        return new Token("");
    }

    /**
     * Returns indent level.
     *
     * @param first  first token.
     * @param second second token.
     * @return indent level
     */
    public Integer getIndent(final Token first, final Token second) {
        Iterator<Pair<Pair<Token, Token>, Integer>> iterator = mappingIndent.iterator();
        while (iterator.hasNext()) {
            Pair<Pair<Token, Token>, Integer> entry = iterator.next();
            if (entry.getKey().getKey().equals(first) && entry.getKey().getValue().equals(second)) {
                indentLevel += entry.getValue();
                return indentLevel;
            }
        }
        return 0;
    }


}
