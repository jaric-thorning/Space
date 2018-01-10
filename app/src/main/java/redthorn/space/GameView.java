package redthorn.space;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.sql.Ref;

/**
 * Created by jaricthorning on 3/1/18.
 */
public class GameView extends View {

    private static final String TAG = "SpaceGame";
    GameScreen gameScreen;


    public GameView(Context context){
        super(context);
        this.gameScreen = (GameScreen) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.gameBackground));
        Rect backgroundRect = new Rect(0, 0, getWidth(), getHeight());



        Shield shield = gameScreen.getSpaceship().getShield();


        canvas.drawRect(backgroundRect, backgroundPaint);

        Paint shieldMeterPaint = new Paint();
        shieldMeterPaint.setStyle(Paint.Style.STROKE);
        shieldMeterPaint.setColor(Color.BLUE);
        shieldMeterPaint.setStrokeWidth(2);
        canvas.drawRect(new Rect(0, getHeight() - 50, (int) shield.getMaxCharge(), getHeight() - 10), shieldMeterPaint);


        Paint internalShieldMeter = new Paint();
        internalShieldMeter.setColor(ContextCompat.getColor(getContext(), R.color.shieldInternal));





        for(int i = 0; i < shield.getLevel(); i++) {
            for (int j = 1; j < shield.getMaxCharge(); j++) {
                    canvas.drawRect(new Rect(j - 1, getHeight() - 50, j, getHeight() - 10), internalShieldMeter);
            }
        }

        for (int j = 1; j < shield.getMaxCharge(); j++) {
            if (j < shield.getCharge()) {
                canvas.drawRect(new Rect(j - 1, getHeight() - 50, j, getHeight() - 10), internalShieldMeter);
            }
        }

        gameScreen.getSpaceship().draw(this, canvas);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN){
            return super.onTouchEvent(event);
        }
        Spaceship spaceship = gameScreen.getSpaceship();
        Room room = spaceship.getRoomAt((int) event.getX(), (int) event.getY());
        if(room != null) {
            Log.d(TAG, "Room " + room.getName() + " selected.");
        } else {
            spaceship.objectHit();
        }
        /* Move spaceship to location */
        /*spaceship.moveTo(event.getX(), event.getY());*/

        invalidate();
        return true;
    }


    public void updateShieldMeter(){


    }

}
