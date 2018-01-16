package com.agentsystem;

import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SupervisorTest{
    private Agent a;

    @Test
    public void LoginSuccess(){
        a = new Agent("123","name");
        assertNotNull(Supervisor.getLoginKey(a));
    }

    @Test
    public void LoginFailure(){
        a = new Agent("spy-123","name");
        assertNull(Supervisor.getLoginKey(a));
    }
}