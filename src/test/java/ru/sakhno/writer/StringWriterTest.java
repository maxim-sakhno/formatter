package ru.sakhno.writer;


import org.junit.Test;
import org.junit.Assert;


public class StringWriterTest {


    @Test (expected = WriterException.class)
    public void testStringWriter01() throws WriterException {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write(null);
    }


    @Test
    public void testStringWriter02() throws WriterException {
        StringWriter stringWriter = new StringWriter();
        Assert.assertEquals("", stringWriter.toString());
    }


    @Test
    public void testStringWriter03() throws WriterException {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write("This ");
        stringWriter.write("is ");
        stringWriter.write("not ");
        stringWriter.write("empty ");
        stringWriter.write("string.");
        Assert.assertEquals("This is not empty string.", stringWriter.toString());
    }


}
