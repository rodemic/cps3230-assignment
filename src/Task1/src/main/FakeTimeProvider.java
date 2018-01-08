package main;

import static java.lang.System.currentTimeMillis;

/**
 * Created by rodemic on 08/01/2018.
 */
public class FakeTimeProvider implements TimeProvider {
    private long time = 0;
    public long getCurrTime() {
        return time;
    }
    public void setCurrTime(long t){
        time = t;
    }
}
