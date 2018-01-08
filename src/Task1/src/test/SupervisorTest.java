package test;

import main.Agent;
import main.Supervisor;
import main.SupervisorStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SupervisorTest{
    private Supervisor s;
    private Agent a;

    @Before
    public void Before(){
        s = new SupervisorStub();
    }

    @Test
    public void LoginSuccess(){
        a = new Agent("123","name");
        assertEquals(true,s.getLoginKey(a));
    }

    @Test
    public void LoginFailure(){
        a = new Agent("123","name");
        assertEquals(false,s.getLoginKey(a));
    }
}