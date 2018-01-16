package com.agentsystem;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AgentTest{
    private Agent a;

    @Test
    public void LoginSuccess(){
        a = new Agent("123","name");
        InputStream in = new ByteArrayInputStream(a.getID().getBytes());
        a.setScanner(new Scanner(in));
        Assert.assertEquals(true,a.login());
    }

    @Test
    public void LoginFailure(){
        a = new Agent("spy-123","name");
        InputStream in = new ByteArrayInputStream(a.getID().getBytes());
        a.setScanner(new Scanner(in));
        Assert.assertEquals(false,a.login());
    }


}