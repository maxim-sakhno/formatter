package ru.sakhno.bootstrap;

import ru.sakhno.formatter.Formatter;
import ru.sakhno.reader.FileReader;
import ru.sakhno.tockenizer.ReaderTokenizer;
import ru.sakhno.writer.FileWriter;

import java.io.File;

/**
 * Formatter demonstration.
 */
final class Demonstration {

    private Demonstration() {}

    /**
     * Main method.
     * @param args - command line arguments.
     */
    public static void main(final String[] args) throws Exception {
        File input = new File("input.txt");
        File output = new File("output.txt");
        FileReader reader = new FileReader(input);
        FileWriter writer = new FileWriter(output);
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        writer.flush();
    }

}
