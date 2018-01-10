package redthorn.space;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jaricthorning on 3/1/18.
 */
public class GameScreen extends Activity {

    private static final String TAG = "SpaceGame";
    private GameView gameView;

    private Spaceship spaceship;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Starting game screen");

        setupGame();


        gameView = new GameView(this);
        setContentView(gameView);
        gameView.requestFocus();
    }

    private void setupGame(){
        Log.d(TAG, "setting up game");
        spaceship = new Spaceship(200, 400);
        setupOnTick();
    }

    public Spaceship getSpaceship() {
        return spaceship;
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

        timer.scheduleAtFixedRate(timerTask, 10, 10); // every 10 mS
    }

    private void OnTick(){
        this.spaceship.regenShield();
        this.gameView.invalidate();
    }
}
