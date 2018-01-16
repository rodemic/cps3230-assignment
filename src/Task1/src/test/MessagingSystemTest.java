package test;

import main.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.*;

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
    public void sendMessageSuccessfulToAgentWithMailbox(){
        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        MessagingSystem.sendMessage(st, "agent2","Hello.");

        //Source Agent sending second Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 1000));
        assertEquals(true, MessagingSystem.sendMessage(st, "agent2","Hello again, just checking."));
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
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()+30000));
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
    public void sendMailBoxSuccess(){
        List<Mailbox> mbs;
        List<Message> ms = new ArrayList<>();

        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 500));
        MessagingSystem.sendMessage(st, "agent2","Hello.");

        //Source Agent sending second Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 1000));
        MessagingSystem.sendMessage(st, "agent2","Hello again.");

        mbs = MessagingSystem.getMailbox();
        for(Mailbox mb: mbs){
            if(mb.getOwnerId().equals("agent2")){
                ms = mb.getMessages();
                break;
            }
        }

        //Target Agent login
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 2000));
        lt = MessagingSystem.registerLoginKey("loginkey11", "agent2");
        st = MessagingSystem.login(lt,"agent2"); //sessionkeygenerated

        //Target gets Mailbox from system
        assertEquals(ms, MessagingSystem.sendMailBox(st).getMessages());
    }

    @Test
    public void sendMailBoxSuccessWithRemovedMessages(){
        List<Mailbox> mbs;
        List<Message> ms = new ArrayList<>();

        //Source Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1"); //sessionkeygenerated

        //Source Agent sending Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 500));
        MessagingSystem.sendMessage(st, "agent2","Hello.");

        //Source Agent sending second Message to Target
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 1000));
        MessagingSystem.sendMessage(st, "agent2","Hello again.");

        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 2000));
        MessagingSystem.sendMessage(st, "agent2","Hello again 2.");

        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 3000));
        MessagingSystem.sendMessage(st, "agent2","Hello again 3.");

        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 10000));
        MessagingSystem.sendMessage(st, "agent2","Hello again 4.");

        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 50000));
        MessagingSystem.sendMessage(st, "agent2","Hello again 5.");

        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 100000));
        MessagingSystem.sendMessage(st, "agent2","Hello again 6.");

        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 1800000));
        MessagingSystem.sendMessage(st, "agent2","Hello again 7.");

        mbs = MessagingSystem.getMailbox();
        for(Mailbox mb: mbs){
            if(mb.getOwnerId().equals("agent2")){
                ms = mb.getMessages();
                break;
            }
        }

        //Target Agent login
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis() + 2000000));
        lt = MessagingSystem.registerLoginKey("loginkey11", "agent2");
        st = MessagingSystem.login(lt,"agent2"); //sessionkeygenerated

        //Target gets Mailbox from system
        assertEquals(ms, MessagingSystem.sendMailBox(st).getMessages());
    }

    @Test
    public void readMessagesNullWithNoMailbox(){
        //Agent
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1"); //sessionkeygenerated

        //Agent tries getting messages
        assertNull(MessagingSystem.sendMailBox(st));
    }

    @Test
    public void readMessagesNullNonExistantSessionKey(){
        MessagingSystem.setTimeProvider(new FakeTimeProvider(currentTimeMillis()));
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10", "agent1");

        SessionToken st = new SessionToken("000000000000000000000000000000000000000000000000000000000000001111",currentTimeMillis()+100000, "agent1");

        assertNull(MessagingSystem.sendMailBox(st));
    }

    @Test
    public void sendMessageLimitReach(){
        MessagingSystem.setTimeProvider(new RealTimeProvider());
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10","agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1");
        LoginToken lt2 = MessagingSystem.registerLoginKey("loginkey11","agent2");
        SessionToken st2 = MessagingSystem.login(lt,"agent2");

        MessagingSystem.sendMessage(st,"agent2","hello1");
        MessagingSystem.sendMessage(st,"agent2","hello2");
        MessagingSystem.sendMessage(st,"agent2","hello3");
        MessagingSystem.sendMessage(st,"agent2","hello4");
        MessagingSystem.sendMessage(st,"agent2","hello5");
        MessagingSystem.sendMessage(st,"agent2","hello6");
        MessagingSystem.sendMessage(st,"agent2","hello7");
        MessagingSystem.sendMessage(st,"agent2","hello8");
        MessagingSystem.sendMessage(st,"agent2","hello9");
        MessagingSystem.sendMessage(st,"agent2","hello10");
        MessagingSystem.sendMessage(st,"agent2","hello11");
        MessagingSystem.sendMessage(st,"agent2","hello12");
        MessagingSystem.sendMessage(st,"agent2","hello13");
        MessagingSystem.sendMessage(st,"agent2","hello14");
        MessagingSystem.sendMessage(st,"agent2","hello15");
        MessagingSystem.sendMessage(st,"agent2","hello16");
        MessagingSystem.sendMessage(st,"agent2","hello17");
        MessagingSystem.sendMessage(st,"agent2","hello18");
        MessagingSystem.sendMessage(st,"agent2","hello19");
        MessagingSystem.sendMessage(st,"agent2","hello20");
        MessagingSystem.sendMessage(st,"agent2","hello21");
        MessagingSystem.sendMessage(st,"agent2","hello22");
        MessagingSystem.sendMessage(st,"agent2","hello23");
        MessagingSystem.sendMessage(st,"agent2","hello24");
        MessagingSystem.sendMessage(st,"agent2","hello25");
        assertFalse(MessagingSystem.sendMessage(st,"agent2","helloF"));
    }

    @Test
    public void receiveMessageLimitReach(){
        MessagingSystem.setTimeProvider(new RealTimeProvider());
        LoginToken lt = MessagingSystem.registerLoginKey("loginkey10","agent1");
        SessionToken st = MessagingSystem.login(lt,"agent1");
        LoginToken lt2 = MessagingSystem.registerLoginKey("loginkey11","agent2");
        SessionToken st2 = MessagingSystem.login(lt2,"agent2");

        MessagingSystem.sendMessage(st,"agent2","hello1");
        MessagingSystem.sendMessage(st,"agent2","hello2");
        MessagingSystem.sendMessage(st,"agent2","hello3");
        MessagingSystem.sendMessage(st,"agent2","hello4");
        MessagingSystem.sendMessage(st,"agent2","hello5");
        MessagingSystem.sendMessage(st,"agent2","hello6");
        MessagingSystem.sendMessage(st,"agent2","hello7");
        MessagingSystem.sendMessage(st,"agent2","hello8");
        MessagingSystem.sendMessage(st,"agent2","hello9");
        MessagingSystem.sendMessage(st,"agent2","hello10");
        MessagingSystem.sendMessage(st,"agent2","hello11");
        MessagingSystem.sendMessage(st,"agent2","hello12");
        MessagingSystem.sendMessage(st,"agent2","hello13");
        MessagingSystem.sendMessage(st,"agent2","hello14");
        MessagingSystem.sendMessage(st,"agent2","hello15");
        MessagingSystem.sendMessage(st,"agent2","hello16");
        MessagingSystem.sendMessage(st,"agent2","hello17");
        MessagingSystem.sendMessage(st,"agent2","hello18");
        MessagingSystem.sendMessage(st,"agent2","hello19");
        MessagingSystem.sendMessage(st,"agent2","hello20");
        MessagingSystem.sendMessage(st,"agent2","hello21");
        MessagingSystem.sendMessage(st,"agent2","hello22");
        MessagingSystem.sendMessage(st,"agent2","hello23");
        MessagingSystem.sendMessage(st,"agent2","hello24");
        MessagingSystem.sendMessage(st,"agent2","hello25");
        assertFalse(MessagingSystem.sendMessage(st2,"agent1","helloF"));
    }

}