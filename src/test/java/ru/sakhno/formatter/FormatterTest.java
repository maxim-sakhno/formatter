package ru.sakhno.formatter;

import org.junit.Assert;
import org.junit.Test;
import ru.sakhno.reader.StringReader;
import ru.sakhno.tockenizer.ReaderTokenizer;
import ru.sakhno.writer.StringWriter;

public class FormatterTest {
    @Test
    public void testFormatter01() throws FormatterException {
        StringReader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        Assert.assertEquals("", writer.toString());
    }

    @Test
    public void testFormatter02() throws FormatterException {
        StringReader reader = new StringReader("hello(){hello(){}}hello(){}");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        Assert.assertEquals(
            "hello ( ) {\n" +
            "    hello ( ) {\n" +
            "    }\n" +
            "}\n" +
            "hello ( ) {\n" +
            "}\n", writer.toString());
    }

    @Test
    public void testFormatter03() throws FormatterException {
        StringReader reader = new StringReader("for(()();;;)");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        Assert.assertEquals("for ( ( ) ( );;; )", writer.toString());
    }

    @Test
    public void testFormatter04() throws FormatterException {
        StringReader reader = new StringReader("\";;;;{}{}{}hello\"");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        Assert.assertEquals("\";;;;{}{}{}hello\"", writer.toString());
    }

    @Test
    public void testFormatter05() throws FormatterException {
        StringReader reader = new StringReader("(1+2+3)(2<3)(4%150)");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        Assert.assertEquals("( 1 + 2 + 3 ) ( 2 < 3 ) ( 4 % 150 )", writer.toString());
    }

    @Test
    public void testFormatter06() throws FormatterException {
        StringReader reader = new StringReader("//hello{}{};;;\n");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        Assert.assertEquals("//hello{}{};;;\n", writer.toString());
    }

    @Test
    public void testFormatter07() throws FormatterException {
        StringReader reader = new StringReader("/*\nbla bla bla{}{}{};;;\n\n\n*/\n");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
        Assert.assertEquals(
            "/*\n" +
            "bla bla bla{}{}{};;;\n" +
            "\n" +
            "\n" +
            "*/", writer.toString());
    }

    @Test (expected = FormatterException.class)
    public void testFormatter08() throws FormatterException {
        StringReader reader = new StringReader("{}{}}}}}}}}");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
    }

    @Test (expected = FormatterException.class)
    public void testFormatter09() throws FormatterException {
        StringReader reader = new StringReader("{}{}}}}}}}}");
        StringWriter writer = new StringWriter();
        ReaderTokenizer tokenizer = new ReaderTokenizer(null, reader);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
    }

    @Test (expected = FormatterException.class)
    public void testFormatter10() throws FormatterException {
        StringReader reader = new StringReader("{}{}}}}}}}}");
        StringWriter writer = new StringWriter();
        String[] tokens = new String[] {
            "{", "}", ";", "\n", "\r", "\t", " ", "/*",
            "*/", "//", "\"", "\'", "for", "(", ")", "++", "--",
            "<=", ">=", "!=", "==", "+=", "-=", "*=", "/=", "%=",
            "+", "-", "*", "/", "%", "<", ">", "="
        };
        ReaderTokenizer tokenizer = new ReaderTokenizer(tokens, null);
        Formatter formatter = new Formatter();
        formatter.format(tokenizer, writer);
    }
}
