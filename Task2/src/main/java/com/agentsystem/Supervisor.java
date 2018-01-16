package com.agentsystem;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.currentTimeMillis;

public interface Supervisor {

    static LoginToken getLoginKey(Agent a){
        if (a.getID().contains("spy-")){
            return null;
        }else {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
            String hash = Long.toString(currentTimeMillis())+a.getID();
            md.update(hash.getBytes(),0,hash.length());
            String loginKey = new BigInteger(md.digest()).toString().substring(0,10);
            System.out.println(loginKey);
                return MessagingSystem.registerLoginKey(loginKey,a.getID());
        }
    }
}
