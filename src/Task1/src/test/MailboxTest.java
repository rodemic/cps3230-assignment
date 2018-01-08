package test;

import main.Mailbox;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MailboxTest{
    Mailbox m;

    @Before
    public void Before(){
        m = new Mailbox();
    }

    @Test
    public void consumeMessageEmpty(){
        assertEquals('h',m.consumeNextMessage());
    }
}