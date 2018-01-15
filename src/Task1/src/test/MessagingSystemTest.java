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
    
    @Before
    public void setup(){
        MessagingSystem.populateAgentList();
    }

    @Test
    public void addNewAgent(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(true,MessagingSystem.addAgent("agent5"));
    }

    @Test
    public void addExistingAgent(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(false,MessagingSystem.addAgent("agent1"));
    }

    @Test
    public void runPopulateAgentList(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(true,MessagingSystem.populateAgentList());
    }

    @Test
    public void registerLoginKeyCheckCharactersFailMore(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(false, MessagingSystem.registerLoginKey("loginkeylongerthan10", "agent1"));
    }

    @Test
    public void registerLoginKeyCheckCharactersFailLess(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(false, MessagingSystem.registerLoginKey("loginless", "agent1"));
    }

    @Test
    public void registerLoginKeyCheckCharactersSuccess(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(true, MessagingSystem.registerLoginKey("loginkey10", "agent1"));
    }

    @Test
    public void loginSuccess(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        assertNotNull(MessagingSystem.login("loginkey10", "agent1"));
    }

    @Test
    public void loginFail(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-60000));
        MessagingSystem.registerLoginKey("loginkey11", "agent1");
        MessagingSystem.setTimeProvider(new RealTimeProvider());
        assertNull(MessagingSystem.login("loginkey11", "agent1"));
    }

    @Test
    public void loginFailDueToKey(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        assertNull(MessagingSystem.login("loginkey12", "agent1"));
    }

    @Test
    public void loginFailDueToID(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        assertNull(MessagingSystem.login("loginkey10", "agent2"));
    }

    @Test
    public void sendMessageSuccessful(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        String sessionkey = MessagingSystem.login("loginkey10","agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(true, MessagingSystem.sendMessage(sessionkey, "agent1", "agent2","hello"));
    }

    @Test
    public void sendMessageFailTimeOut(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        String sessionkey = MessagingSystem.login("loginkey10","agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+600000));
        assertEquals(false, MessagingSystem.sendMessage(sessionkey, "agent1", "agent2","hello"));
    }

    @Test
    public void sendMessageFailChangedSessionKey(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        String sessionkey = MessagingSystem.login("loginkey10","agent1") + "111"; //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+600000));
        assertEquals(false, MessagingSystem.sendMessage(sessionkey, "agent1", "agent2","hello"));
    }

    @Test
    public void sendMessageFailInvalidTargetAgent(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        String sessionkey = MessagingSystem.login("loginkey10","agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+600000));
        assertEquals(false, MessagingSystem.sendMessage(sessionkey, "agent1", "agent7","hello"));
    }

}