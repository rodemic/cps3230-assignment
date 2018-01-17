package main.java.com.agentsystem;

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
}
