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
    public void consumeMessageLate(){
        Message m = new Message("abc","def",(currentTimeMillis()-1800000),"hello");
        mb.addMessage(m);
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
    public void hasMessagesLate(){
        Message m = new Message("abc","def",(currentTimeMillis()-1800000),"hello");
        mb.addMessage(m);
        assertFalse(mb.hasMessages());
    }

    @Test
    public void hasMessagesSuccesful(){
        Message m = new Message("abc","def",currentTimeMillis(),"hello");
        mb.addMessage(m);
        assertTrue(mb.hasMessages());
    }
}