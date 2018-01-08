package test;

import main.Agent;
import main.Supervisor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SupervisorTest{
    private Agent a;

    @Test
    public void LoginSuccess(){
        a = new Agent("123","name");
        assertEquals(true,Supervisor.getLoginKey(a));
    }

    @Test
    public void LoginFailure(){
        a = new Agent("123","name");
        assertEquals(false,Supervisor.getLoginKey(a));
    }
}