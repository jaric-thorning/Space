package redthorn.space;

import android.os.Handler;

import java.lang.reflect.Method;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by jaricthorning on 13/1/18.
 */
public class Ticker {
    public static Ticker ticker = new Ticker();

    private int tickRate = 10; //mS

    private List<Runnable> tickMethods = new ArrayList<>();

    public Ticker(){
        tickMethods = new ArrayList<>();
        setupOnTick();
    }

    private void setupOnTick(){
        final Handler handler = new Handler();
        Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        OnTick();
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, tickRate, tickRate); // every 10 mS

    }

    public int getTickRate() {
        return tickRate;
    }

    public int getTicksPerSecond(){
        return 1000 / tickRate;
    }

    private void OnTick(){
        invokeTickMethods();
    }

    public void addMethod(Runnable methodCall) {
        tickMethods.add(methodCall);
    }

    public void invokeTickMethods()
    {
        for(Runnable aMethod : tickMethods) {
            aMethod.run();
        }
    }


}
