package test;

import main.MessagingSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessagingSystemTest{

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
    public void registerLoginKeyCheckCharactersFailMore(){
        assertEquals(false, ms.registerLoginKey("loginkeylongerthan10", "user123"));
    }

    @Test
    public void registerLoginKeyCheckCharactersFailLess(){
        assertEquals(false, ms.registerLoginKey("loginless", "user123"));
    }

    @Test
    public void registerLoginKeyCheckCharactersSuccess(){
        assertEquals(true, ms.registerLoginKey("loginkey10", "user123"));
    }

    @Test
    public void loginSuccess(){

    }

}