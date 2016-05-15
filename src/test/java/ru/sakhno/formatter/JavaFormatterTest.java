package ru.sakhno.formatter;


import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.sakhno.reader.StringReader;
import ru.sakhno.tockenizer.AnyToken;
import ru.sakhno.tockenizer.Token;
import ru.sakhno.writer.StringWriter;

import java.util.LinkedList;
import java.util.List;


public class JavaFormatterTest {


    private JavaFormatter javaFormatter;


    @Before
    public void setUp() {
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


        //Для круглых скобок
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("if"), new Token("(")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("for"), new Token("(")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("while"), new Token("(")), new Token(" ")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token("("), new AnyToken("")), new Token("")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token("(")), new Token("")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new Token(")"), new AnyToken("")), new Token("")));
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new Token(")")), new Token("")));

        //По умолчанию
        mappingSeparator.add(new Pair<Pair<Token, Token>, Token>(new Pair<Token, Token>(new AnyToken(""), new AnyToken("")), new Token(" ")));


        //Задаем правила для уровня вложенности
        List<Pair<Pair<Token, Token>, Integer>> mappingIndent = new LinkedList<Pair<Pair<Token, Token>, Integer>>();
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new Token("{"), new AnyToken("")), 1));
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new AnyToken(""), new Token("}")), -1));
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new Token("}"), new AnyToken("")), 0));
        mappingIndent.add(new Pair<Pair<Token, Token>, Integer>(new Pair<Token, Token>(new Token(";"), new AnyToken("")), 0));



        IRule rule = new JavaRule(mappingSeparator, mappingIndent);
        javaFormatter = new JavaFormatter(rule);
    }


    @Test
    public void testJavaFormatter01() throws FormatterException {
        StringReader input = new StringReader("{hello;}");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("{\n    hello;\n}", output.toString());
    }


    @Test
    public void testJavaFormatter02() throws FormatterException {
        StringReader input = new StringReader("he   ll   o;");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("he ll o;", output.toString());
    }


    @Test
    public void testJavaFormatter03() throws FormatterException {
        StringReader input = new StringReader("hello;hello;   hello;");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("hello;\nhello;\nhello;", output.toString());
    }


    @Test (expected = FormatterException.class)
    public void testJavaFormatter04() throws FormatterException {
        StringReader input = new StringReader("{{}");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
    }


    @Test (expected = FormatterException.class)
    public void testJavaFormatter05() throws FormatterException {
        StringReader input = new StringReader("}}}");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
    }


    @Test
    public void testJavaFormatter06() throws FormatterException {
        StringReader input = new StringReader("(1\n+  2+3)   +4+ (5  )+6+7");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("(1 + 2 + 3) + 4 + (5) + 6 + 7", output.toString());
    }


    @Test
    public void testJavaFormatter07() throws FormatterException {
        StringReader input = new StringReader("{hello;{hello;{hello;}}}");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("{\n" +
            "    hello;\n" +
            "    {\n" +
            "        hello;\n" +
            "        {\n" +
            "            hello;\n" +
            "        }\n" +
            "    }\n" +
            "}",
            output.toString());
    }


}
