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
        Ticker.ticker.addMethod(tick);

    }

    public Spaceship getSpaceship() {
        return spaceship;
    }


   public void onTick(){
       this.spaceship.regenShield();
       this.gameView.invalidate();
   }

    final Runnable tick = new Runnable() {
        @Override
        public void run() {
            onTick();
        }
    };


}
