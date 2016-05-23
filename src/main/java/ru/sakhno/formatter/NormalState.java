package ru.sakhno.formatter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Normal formatter state.
 */
public class NormalState implements IFormatterState {
    private Map<String, String> tokenMapping;
    private Map<String, Integer> indentLevelMapping;
    private Map<String, String> ignoreStateTokens;
    private Set<String> newLineTokens;
    private Set<String> ignoreTokens;
    private Set<String> noSpaceTokens;
    private Formatter formatter;
    private boolean newLine;
    private static final int TAB_SIZE = 4;

    /**
     * Creates normal state for formatter.
     *
     * @param formatter the formatter for state.
     */
    public NormalState(final Formatter formatter) {
        this.formatter = formatter;

        tokenMapping = new HashMap<String, String>();

        tokenMapping.put(";", ";\n");
        tokenMapping.put("{", "{\n");
        tokenMapping.put("}", "}\n");

        ignoreTokens = new HashSet<String>();
        ignoreTokens.add("\n");
        ignoreTokens.add("\r");
        ignoreTokens.add("\t");
        ignoreTokens.add(" ");

        indentLevelMapping = new HashMap<String, Integer>();

        indentLevelMapping.put("{", 1);
        indentLevelMapping.put("}", -1);

        newLineTokens = new HashSet<String>();
        newLineTokens.add("{");
        newLineTokens.add("}");
        newLineTokens.add(";");

        noSpaceTokens = new HashSet<String>();
        noSpaceTokens.add(";");

        ignoreStateTokens = new HashMap<String, String>();
        ignoreStateTokens.put("\"", "\"");
        ignoreStateTokens.put("//", "\n");
        ignoreStateTokens.put("/*", "*/");
        ignoreStateTokens.put("\'", "\'");

        newLine = true;
    }
    /**
     * Returns string as a result handling the token.
     *
     * @param token the token for handle.
     * @return string as a result handling the token.
     * @throws FormatterException if some error occurs.
     */
    public String handleToken(final String token) throws FormatterException {
        if (ignoreTokens.contains(token)) {
            return "";
        }
        String result = "";
        String separator = "";
        if (tokenMapping.containsKey(token)) {
            result = tokenMapping.get(token);
        } else {
            result = token;
        }
        if (indentLevelMapping.containsKey(token)) {
            formatter.changeIndentLevel(indentLevelMapping.get(token));
        }
        if (newLine) {
            separator = getIndent();
            newLine = false;
        } else if (!noSpaceTokens.contains(token)) {
            separator = " ";
        }
        if (newLineTokens.contains(token)) {
            newLine = true;
        }
        return separator + result;
    }

    /**
     * Setts new state for formatter.
     *
     * @param token the token for choosing new state.
     */
    public IFormatterState getState(final String token) {
        if (ignoreStateTokens.containsKey(token)) {
            return new IgnoreState(formatter, ignoreStateTokens.get(token));
        }
        if (token.equals("for")) {
            return new InForState(formatter);
        }
        return this;
    }

    private String getIndent() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < formatter.getIndentLevel() * TAB_SIZE; ++i) {
            result.append(" ");
        }
        return result.toString();
    }
}
