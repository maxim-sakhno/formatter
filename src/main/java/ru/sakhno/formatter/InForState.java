package ru.sakhno.formatter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The state for formatter inside for.
 */
public class InForState implements IFormatterState {
    private Map<String, Integer> bracketBalanceTokens;
    private Set<String> ignoreTokens;
    private Set<String> noSpaceTokens;
    private Formatter formatter;
    private int bracketBalance;
    private boolean inside;

    /**
     * Creates normal state for formatter.
     *
     * @param formatter the formatter for state.
     */
    public InForState(final Formatter formatter) {
        this.formatter = formatter;

        bracketBalanceTokens = new HashMap<String, Integer>();
        bracketBalanceTokens.put("(", 1);
        bracketBalanceTokens.put(")", -1);

        ignoreTokens = new HashSet<String>();
        ignoreTokens.add("\n");
        ignoreTokens.add("\r");
        ignoreTokens.add("\t");
        ignoreTokens.add(" ");

        noSpaceTokens = new HashSet<String>();
        noSpaceTokens.add(";");
        bracketBalance = 0;
        inside = false;
    }
    /**
     * Returns string as a result handling the token.
     *
     * @param token the token for handle.
     * @return string as a result handling the token.
     * @throws FormatterException if some error occurs.
     */
    public String handleToken(final String token) throws FormatterException {
        if (token.equals("(")) {
            inside = true;
        }
        if (ignoreTokens.contains(token)) {
            return "";
        }
        String result = token;
        String separator = " ";
        if (noSpaceTokens.contains(token)) {
            separator = "";
        }
        if (bracketBalanceTokens.containsKey(token)) {
            bracketBalance += bracketBalanceTokens.get(token);
            if (bracketBalance < 0) {
                throw new FormatterException("Bracket balance error inside for.");
            }
        }
        return separator + result;
    }

    /**
     * Setts new state for formatter.
     *
     * @param token the token for choosing new state.
     */
    public IFormatterState getState(final String token) {
        if (bracketBalance == 0 && inside) {
            return new NormalState(formatter);
        }
        return this;
    }
}
