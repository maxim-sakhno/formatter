package ru.sakhno.tokenizer;


import org.junit.Assert;
import org.junit.Test;
import ru.sakhno.tockenizer.AnyToken;
import ru.sakhno.tockenizer.Token;

import java.util.HashSet;
import java.util.Set;

public class TokenTest {


    @Test
    public void testToken01() {
        Token first = new Token("Hello");
        Token second = new Token("Hello");
        Assert.assertEquals(true, first.equals(second));
    }


    @Test
    public void testToken02() {
        Token first = new Token("Hello");
        Token second = new Token("World");
        Assert.assertEquals(false, first.equals(second));
    }


    @Test
    public void testToken03() {
        Token first = new AnyToken("");
        Token second = new Token("Hello");
        Assert.assertEquals(true, first.equals(second));
    }


    @Test
    public void testToken04() {
        Token first = new Token("Hello");
        Token second = new AnyToken("");
        Assert.assertEquals(false, first.equals(second));
    }


}
