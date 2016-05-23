package ru.sakhno.tokenizer;

import org.junit.Assert;
import org.junit.Test;
import ru.sakhno.reader.StringReader;
import ru.sakhno.tockenizer.ReaderTokenizer;
import ru.sakhno.tockenizer.TokenizerException;
import java.util.LinkedList;
import java.util.List;

public class ReaderTokenizerTest {
    @Test (expected = TokenizerException.class)
    public void testReaderTokenizer01() throws TokenizerException {
        ReaderTokenizer readerTokenizer = new ReaderTokenizer(null, null);
        readerTokenizer.hasNext();
    }

    @Test (expected = TokenizerException.class)
    public void testReaderTokenizer02() throws TokenizerException {
        ReaderTokenizer readerTokenizer = new ReaderTokenizer(null, null);
        readerTokenizer.getToken();
    }

    @Test
    public void testReaderTokenizer03() throws TokenizerException {
        String[] tokens = new String[] {"/**", "/*", "*/"};
        StringReader stringReader = new StringReader("/**/*/**Comment*/*/*/");
        ReaderTokenizer readerTokenizer = new ReaderTokenizer(tokens, stringReader);
        List<String> result = new LinkedList<String>();
        while (readerTokenizer.hasNext()) {
            result.add(readerTokenizer.getToken());
        }
        String[] expected = new String[] {"/**", "/*", "/**", "Comment", "*/", "*/", "*/"};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void testReaderTokenizer04() throws TokenizerException {
        String[] tokens = new String[] {"for", "++", "(", ")", "{", "}", "=", "<", ";", " "};
        StringReader stringReader = new StringReader("for(int i=0;i<length;++i){}");
        ReaderTokenizer readerTokenizer = new ReaderTokenizer(tokens, stringReader);
        List<String> result = new LinkedList<String>();
        while (readerTokenizer.hasNext()) {
            result.add(readerTokenizer.getToken());
        }
        String[] expected = new String[] {
            "for", "(", "int", " ", "i", "=", "0",
            ";", "i", "<", "length", ";",
            "++", "i", ")", "{", "}"
        };
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void testReaderTokenizer05() throws TokenizerException {
        String[] tokens = new String[] {"for", "++", "(", ")", "{", "}", "=", "<", ";", " "};
        StringReader stringReader = new StringReader("");
        ReaderTokenizer readerTokenizer = new ReaderTokenizer(tokens, stringReader);
        List<String> result = new LinkedList<String>();
        while (readerTokenizer.hasNext()) {
            result.add(readerTokenizer.getToken());
        }
        String[] expected = new String[] {};
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test (expected = TokenizerException.class)
    public void testReaderTokenizer06() throws TokenizerException {
        String[] tokens = new String[] {"for", "++", "(", ")", "{", "}", "=", "<", ";", " "};
        StringReader stringReader = new StringReader("");
        ReaderTokenizer readerTokenizer = new ReaderTokenizer(tokens, stringReader);
        readerTokenizer.getToken();
    }
}
