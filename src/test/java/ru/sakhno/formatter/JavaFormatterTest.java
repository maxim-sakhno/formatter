package ru.sakhno.formatter;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.sakhno.reader.StringReader;
import ru.sakhno.writer.StringWriter;


public class JavaFormatterTest {


    private JavaFormatter javaFormatter;


    @Before
    public void setUp() {
        javaFormatter = new JavaFormatter();
    }


    @Test
    public void testJavaFormatter01() throws FormatterException {
        StringReader input = new StringReader(";");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals(";\n", output.toString());
    }


    @Test (expected = FormatterException.class)
    public void testJavaFormatter02() throws FormatterException {
        StringReader input = new StringReader("{");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
    }


    @Test (expected = FormatterException.class)
    public void testJavaFormatter03() throws FormatterException {
        StringReader input = new StringReader("}");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
    }


    @Test
    public void testJavaFormatter04() throws FormatterException {
        StringReader input = new StringReader("word    word    { word word { word;} word;} word word;");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("word word {\n    word word {\n        word;\n    }\n    word;\n}\nword word;\n", output.toString());
    }


    @Test
    public void testJavaFormatter05() throws FormatterException {
        StringReader input = new StringReader("word\n\n\n    word    { word\n\n word { word;\n\n} word;}\n\n\n word word;");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("word word {\n    word word {\n        word;\n    }\n    word;\n}\nword word;\n", output.toString());
    }


    @Test
    public void testJavaFormatter06() throws FormatterException {
        StringReader input = new StringReader("+-/           *;");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("+ - / *;\n", output.toString());
    }


    @Test
    public void testJavaFormatter08() throws FormatterException {
        StringReader input = new StringReader("1+2   + 3+(4 +   5 +  6  + ( 7 +8 + 9)   );");
        StringWriter output = new StringWriter();
        javaFormatter.format(input, output);
        Assert.assertEquals("1 + 2 + 3 + ( 4 + 5 + 6 + ( 7 + 8 + 9) ) ;\n", output.toString());
    }


}
