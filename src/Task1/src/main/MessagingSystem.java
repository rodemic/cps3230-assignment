package main;

import java.util.List;

import static java.lang.System.currentTimeMillis;

public class MessagingSystem {

    private List<LoginToken> lts;

    public MessagingSystem(){}

    public boolean registerLoginKey(String loginkey, String agentId){
        if(loginkey.length() == 10){
            LoginToken lt = new LoginToken(loginkey,agentId,currentTimeMillis());
            lts.add(lt);
            return true;
        }
        else return false;
    }

}
