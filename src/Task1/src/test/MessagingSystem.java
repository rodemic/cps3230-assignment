package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessagingSystem {

    MessagingSystem ms;
    @Before
    void setup(){
        ms = new MessagingSystem();
    }

    @After
    void teardown(){
        ms = null;
    }

    @Test
    public void registerLoginKeyCheckCharacters(){

        assertEquals(false, ms.registerLoginKey("loginkeylongerthan10", "user123"));
    }

}