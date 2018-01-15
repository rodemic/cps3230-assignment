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
        assertNull(MessagingSystem.registerLoginKey("loginkeylongerthan10", "agent1"));
    }

    @Test
    public void registerLoginKeyCheckCharactersFailLess(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertNull(MessagingSystem.registerLoginKey("loginless", "agent1"));
    }

    @Test
    public void registerLoginKeyCheckCharactersSuccess(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertNotNull(MessagingSystem.registerLoginKey("loginkey10", "agent1"));
    }

    @Test
    public void loginSuccess(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        assertNotNull(lt);
    }

    @Test
    public void loginFailDueToTime(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()-60000));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey11", "agent1");
        MessagingSystem.setTimeProvider(new RealTimeProvider());
        assertNull(MessagingSystem.login(lt,"agent1"));
    }

    @Test
    public void loginFailDueToKeyTooLong(){
        MessagingSystem.setTimeProvider(new RealTimeProvider());
        LoginToken lt = MessagingSystem.registerLoginKey("01234567890", "agent1");
        assertNull(lt);
    }

    @Test
    public void loginFailDueToKeyTooShort(){
        MessagingSystem.setTimeProvider(new RealTimeProvider());
        LoginToken lt = MessagingSystem.registerLoginKey("123456789", "agent1");
        assertNull(lt);
    }

    @Test
    public void sendMessageSuccessful(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        assertEquals(true, MessagingSystem.sendMessage(st, "agent2","hello"));
    }

    @Test
    public void sendMessageFailTimeOut(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+600000));
        assertEquals(false, MessagingSystem.sendMessage(st, "agent2","hello"));
    }

    @Test
    public void sendMessageFailChangedSessionToken(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1");//sessionkeygenerated
        st = new SessionToken("12345678901234567890123456789012345678901234567890",currentTimeMillis(),"agent1");

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+600000));
        assertEquals(false, MessagingSystem.sendMessage(st, "agent2","hello"));
    }

    @Test
    public void sendMessageFailInvalidTargetAgent(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+600000));
        assertEquals(false, MessagingSystem.sendMessage(st,"agent7","hello"));
    }

    @Test
    public void sendMessageFailMessageTooLong(){
        String message = "This is a very long message as it will be longer than 140 characters. This message is being written to test the issue of sending a very long message.";
        LoginToken lt = new LoginToken("loginkey10", "agent1", currentTimeMillis());

        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+1000));
        MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt, "agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target but message too long
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+30000));
        assertEquals(false, MessagingSystem.sendMessage(st, "agent7",message));
    }

    @Test


}