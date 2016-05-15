package ru.sakhno.bootstrap;


import javafx.util.Pair;
import ru.sakhno.formatter.*;
import ru.sakhno.reader.FileReader;
import ru.sakhno.reader.IReader;
import ru.sakhno.reader.ReaderException;
import ru.sakhno.tockenizer.AnyToken;
import ru.sakhno.tockenizer.Token;
import ru.sakhno.writer.FileWriter;
import ru.sakhno.writer.IWriter;
import ru.sakhno.writer.StringWriter;
import ru.sakhno.writer.WriterException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Formatter demonstration.
 */
final class Demonstration {


    private Demonstration() {}


    /**
     * Main method.
     * @param args - command line arguments.
     */
    public static void main(final String[] args) throws WriterException, ReaderException, FormatterException {
        FileReader reader = new FileReader(new File("input.txt"));
        FileWriter writer = new FileWriter(new File("output.txt"));
        List<Pair<Pair<Token, Token>, Token>> mappingSeparator = new LinkedList<Pair<Pair<Token, Token>, Token>>();


        //Задаем правила
        //Для точки с запятой
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token(";"), new AnyToken("")), new Token("\n")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token(";")), new Token("")));


        //Для Фигурных скобок
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("{"), new AnyToken("")), new Token("\n")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("}"), new Token("else")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("}"), new AnyToken("")), new Token("\n")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("else"), new Token("{")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("{")), new Token(" ")));


        //Для знаков арифметических операций
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("+")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("+"), new AnyToken("")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("*")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("*"), new AnyToken("")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("-")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("-"), new AnyToken("")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("/")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("/"), new AnyToken("")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("%")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("%"), new AnyToken("")), new Token(" ")));


        //Для логических операций
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("<")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("<"), new AnyToken("")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token(">")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token(">"), new AnyToken("")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("==")), new Token(" ")));


        //Для круглых скобок
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("if"), new Token("(")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("for"), new Token("(")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("while"), new Token("(")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("("), new AnyToken("")), new Token("")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("(")), new Token("")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token(")"), new AnyToken("")), new Token("")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token(")")), new Token("")));


        //Для присваивания
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("=")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("="), new AnyToken("")), new Token(" ")));


        //По умолчанию
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new AnyToken("")), new Token(" ")));


        //Задаем правила для уровня вложенности
        List<Pair<Pair<Token, Token>, Integer>> mappingIndent = new LinkedList<Pair<Pair<Token, Token>, Integer>>();
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new Token("{"), new AnyToken("")), 1));
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new AnyToken(""), new Token("}")), -1));
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new Token("}"), new AnyToken("")), 0));
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new Token(";"), new AnyToken("")), 0));



        IRule rule = new JavaRule(mappingSeparator, mappingIndent);
        IFormatter formatter = new JavaFormatter(rule);
        formatter.format(reader, writer);
        writer.flush();
        writer.close();
    }


}
