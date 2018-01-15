package main;

import java.sql.CallableStatement;
import java.util.Scanner;

public class Agent{

    private String ID;
    private String name;
    private SessionToken st;
    private Scanner s;

    public Agent(String ID, String name) {
        this.ID = ID;
        this.name = name;
        s = new Scanner(System.in);
    }

    public boolean login() {
        LoginToken lt = Supervisor.getLoginKey(this);
        if(lt == null)return false;
        System.out.print("Enter your ID:");
        String inputID = s.next();
        st = MessagingSystem.login(lt,inputID);
        return st != null;
    }

    public void setScanner(Scanner s){
        this.s = s;
    }

    public String getID() {
        return ID;
    }
}