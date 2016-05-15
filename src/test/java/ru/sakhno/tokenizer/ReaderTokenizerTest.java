package ru.sakhno.tokenizer;


import org.junit.Assert;
import org.junit.Test;
import ru.sakhno.reader.IReader;
import ru.sakhno.reader.StringReader;
import ru.sakhno.tockenizer.ReaderTokenizer;
import ru.sakhno.tockenizer.Token;
import ru.sakhno.tockenizer.TokenizerException;
import java.util.LinkedList;
import java.util.List;


public class ReaderTokenizerTest {


    @Test
    public void testReaderTokenizer01() throws TokenizerException {
        IReader reader = new StringReader("bla{ }; \rbla");
        ReaderTokenizer tokenizer = new ReaderTokenizer(reader, "{}; \n\r");
        List<Token> result = new LinkedList<Token>();
        while (tokenizer.hasNext()) {
            result.add(tokenizer.getToken());
        }
        List<Token> expected = new LinkedList<Token>();
        expected.add(new Token("bla"));
        expected.add(new Token("{"));
        expected.add(new Token(" "));
        expected.add(new Token("}"));
        expected.add(new Token(";"));
        expected.add(new Token(" "));
        expected.add(new Token("\r"));
        expected.add(new Token("bla"));
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }


    @Test (expected = TokenizerException.class)
    public void testReaderTokenizer02() throws TokenizerException {
        IReader reader = new StringReader("");
        ReaderTokenizer tokenizer = new ReaderTokenizer(reader, "{}; \n\r");
        tokenizer.getToken();
    }


    @Test (expected = TokenizerException.class)
    public void testReaderTokenizer03() throws TokenizerException {
        ReaderTokenizer tokenizer = new ReaderTokenizer(null, "{}; \n\r");
        tokenizer.hasNext();
    }


    @Test (expected = TokenizerException.class)
    public void testReaderTokenizer04() throws TokenizerException {
        ReaderTokenizer tokenizer = new ReaderTokenizer(null, "{}; \n\r");
        tokenizer.getToken();
    }


    @Test (expected = TokenizerException.class)
    public void testReaderTokenizer05() throws TokenizerException {
        IReader reader = new StringReader("{}bla  bla {}; ;bla ");
        ReaderTokenizer tokenizer = new ReaderTokenizer(reader, null);
        tokenizer.getToken();
    }


}
