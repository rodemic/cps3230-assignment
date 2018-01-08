package test;

import main.Agent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgentTest{
    private Agent a;

    @Before
    public void Before(){
        a = new Agent();
    }

    @Test
    public void LoginSuccess(){
        a.id = "123";
        assertEquals(true,a.login());
    }

    @Test
    public void LoginFailure(){
        a.id = "spy-123";
        assertEquals(false,a.login());
    }


}