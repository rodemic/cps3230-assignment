package test;

import main.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
    public void addNewAgent(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
    }

    @Test
    public void registerLoginKeyCheckCharactersFailMore(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(false, ms.registerLoginKey("loginkeylongerthan10", "agent1"));
    }

    @Test
    public void registerLoginKeyCheckCharactersFailLess(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(false, ms.registerLoginKey("loginless", "agent1"));
    }

    @Test
    public void registerLoginKeyCheckCharactersSuccess(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(true, ms.registerLoginKey("loginkey10", "agent1"));
    }

    @Test
    public void loginSuccess(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        ms.registerLoginKey("loginkey10", "agent1");
        assertNotNull(ms.login("loginkey10", "agent1"));
    }

    @Test
    public void loginFail(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-60000));
        ms.registerLoginKey("loginkey11", "agent1");
        ms.setTimeProvider(new RealTimeProvider());
        assertNull(ms.login("loginkey11", "agent1"));
    }

    @Test
    public void loginFailDueToKey(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        ms.registerLoginKey("loginkey10", "agent1");
        assertNull(ms.login("loginkey12", "agent1"));
    }

    @Test
    public void loginFailDueToID(){
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        ms.registerLoginKey("loginkey10", "agent1");
        assertNull(ms.login("loginkey10", "agent2"));
    }

    @Test
    public void sendMessageSuccessful(){
        //Source Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        ms.registerLoginKey("loginkey10", "agent1");
        String sessionkey = ms.login("loginkey10","agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(true, ms.sendMessage(sessionkey, "agent1", "agent2","hello"));
    }

    @Test
    public void sendMessageFailTimeOut(){
        //Source Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-30000));
        ms.registerLoginKey("loginkey10", "agent1");
        String sessionkey = ms.login("loginkey10","agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-570000));
        assertEquals(false, ms.sendMessage(sessionkey, "agent1", "agent2","hello"));
    }

    @Test
    public void sendMessageFailChangedSessionKey(){
        //Source Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-30000));
        ms.registerLoginKey("loginkey10", "user123");
        String sessionkey = ms.login("loginkey10","user123") + "111"; //sessionkeygenerated

        //Target Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-30000));
        ms.registerLoginKey("loginkey11", "user124");
        String sessionkey2 = ms.login("loginkey11","user124"); //sessionkeygenerated

        //Source Agent sending Message to Target
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-570000));
        assertEquals(false, ms.sendMessage(sessionkey, "user123", "user124","hello"));
    }

    @Test
    public void sendMessageFailInvalidSourceAgent(){
        //Source Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-30000));
        ms.registerLoginKey("loginkey10", "user123");
        String sessionkey = ms.login("loginkey10","user123"); //sessionkeygenerated

        //Target Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-30000));
        ms.registerLoginKey("loginkey11", "user124");
        String sessionkey2 = ms.login("loginkey11","user124"); //sessionkeygenerated

        //Source Agent sending Message to Target
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-570000));
        assertEquals(false, ms.sendMessage(sessionkey, "user126", "user124","hello"));
    }

    @Test
    public void sendMessageFailInvalidTargetAgent(){
        //Source Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-30000));
        ms.registerLoginKey("loginkey10", "user123");
        String sessionkey = ms.login("loginkey10","user123"); //sessionkeygenerated

        //Target Agent
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-30000));
        ms.registerLoginKey("loginkey11", "user124");
        String sessionkey2 = ms.login("loginkey11","user124"); //sessionkeygenerated

        //Source Agent sending Message to Target
        ms.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-570000));
        assertEquals(false, ms.sendMessage(sessionkey, "user123", "user126","hello"));
    }

}