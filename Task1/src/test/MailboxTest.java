package test;

import main.Mailbox;
import main.Message;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.*;

public class MailboxTest{
    private Mailbox mb;

    @Before
    public void Before(){
        mb = new Mailbox();
    }

    @Test
    public void consumeMessageEmpty(){
        assertNull(mb.consumeNextMessage());
    }

    @Test
    public void consumeMessageSuccesful(){
        Message m = new Message("abc","def",currentTimeMillis(),"hello");
        mb.addMessage(m);
        assertEquals(m,mb.consumeNextMessage());
    }

    @Test
    public void hasMessagesEmpty(){
        assertFalse(mb.hasMessages());
    }

    @Test
    public void hasMessagesSuccesful(){
        Message m = new Message("abc","def",currentTimeMillis(),"hello");
        mb.addMessage(m);
        assertTrue(mb.hasMessages());
    }
}