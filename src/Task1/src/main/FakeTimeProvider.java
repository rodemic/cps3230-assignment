package main;

import static java.lang.System.currentTimeMillis;

/**
 * Created by rodemic on 08/01/2018.
 */
public class FakeTimeProvider implements TimeProvider {
    private long time;

    public FakeTimeProvider(long time){
        this.time = time;
    }

    public long getCurrTime() {
        return time;
    }
    public void setCurrTime(long t){
        time = t;
    }


}
