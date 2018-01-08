package main;

import java.sql.CallableStatement;
import java.util.Scanner;

public class Agent{

    private String ID;
    private String name;
    private String sessionKey;
    private Scanner s;

    public Agent(String ID, String name) {
        this.ID = ID;
        this.name = name;
        s = new Scanner(System.in);
    }

    public boolean login() {
        String loginKey = Supervisor.getLoginKey(this);
        if(loginKey == null)return false;
        System.out.print("Enter your ID:");
        String inputID = s.next();
        sessionKey = MessagingSystem.login(loginKey,inputID);
        return sessionKey != null;
    }

    public void setScanner(Scanner s){
        this.s = s;
    }

    public String getID() {
        return ID;
    }
}