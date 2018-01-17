package main.java.com.agentsystem;

import static java.lang.System.currentTimeMillis;

public class RealTimeProvider implements TimeProvider {
    public long getCurrTime(){
        return currentTimeMillis();
    }
}
