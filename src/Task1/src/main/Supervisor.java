package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static java.lang.System.currentTimeMillis;

public interface Supervisor {

    static String getLoginKey(Agent a){
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
            byte[] loginKeyBytes = md.digest(hash.getBytes());
            String loginKey = loginKeyBytes.toString().substring(1,11);
            System.out.println(loginKey);
            if(MessagingSystem.registerLoginKey(loginKey,a.getID()))
                return loginKey;
            else return null;
        }
    }
}
