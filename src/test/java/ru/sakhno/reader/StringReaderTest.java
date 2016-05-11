package ru.sakhno.reader;


import org.junit.Assert;
import org.junit.Test;


public class StringReaderTest {


    @Test (expected = ReaderException.class)
    public void testStringReader01() throws ReaderException {
        StringReader stringReader = new StringReader(null);
        stringReader.hasNext();
    }


    @Test (expected = ReaderException.class)
    public void testStringReader02() throws ReaderException {
        StringReader stringReader = new StringReader(null);
        stringReader.read();
    }


    @Test
    public void testStringReader03() throws ReaderException {
        StringReader stringReader = new StringReader("");
        boolean result = stringReader.hasNext();
        Assert.assertEquals(false, result);
    }


    @Test (expected = ReaderException.class)
    public void testStringReader04() throws ReaderException {
        StringReader stringReader = new StringReader("");
        stringReader.read();
    }


    @Test
    public void testStringReader05() throws ReaderException {
        StringReader stringReader = new StringReader("Not empty string");
        boolean result = stringReader.hasNext();
        Assert.assertEquals(true, result);
    }


    @Test
    public void testStringReader06() throws ReaderException {
        StringReader stringReader = new StringReader("Not empty string");
        while (stringReader.hasNext()) {
            stringReader.read();
        }
        boolean result = stringReader.hasNext();
        Assert.assertEquals(false, result);
    }


    @Test
    public void testStringReader07() throws ReaderException {
        StringReader stringReader = new StringReader("Not empty string");
        StringBuilder stringBuilder = new StringBuilder();
        while (stringReader.hasNext()) {
            stringBuilder.append(stringReader.read());
        }
        Assert.assertEquals("Not empty string", stringBuilder.toString());
    }


}
