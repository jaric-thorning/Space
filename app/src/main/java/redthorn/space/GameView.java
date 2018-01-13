package redthorn.space;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import redthorn.space.Systems.Shield;
import redthorn.space.Weapons.MissileLauncher;
import redthorn.space.Weapons.Weapon;

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
        Weapon weapon1 = gameScreen.getSpaceship().getWeapon1();


        int shieldLength = 1000 / (int) shield.getMaxCharge();
        int weapon1Length = 1000 / (int) weapon1.getMaxCharge();



        canvas.drawRect(backgroundRect, backgroundPaint);

        /* Draw shield meter outline */
        Paint shieldMeterPaint = new Paint();
        shieldMeterPaint.setStyle(Paint.Style.STROKE);
        shieldMeterPaint.setColor(Color.BLUE);
        shieldMeterPaint.setStrokeWidth(2);
        canvas.drawRect(new Rect(0, getHeight() - 50, 1000, getHeight() - 10), shieldMeterPaint);




        /* Define paint for outside shield meter */
        Paint internalShieldMeter = new Paint();
        internalShieldMeter.setColor(ContextCompat.getColor(getContext(), R.color.shieldInternal));

        /*Shield Meter */
        for(int i = 0; i < shield.getLevel(); i++) {
            for (int j = 1; j < shield.getMaxCharge() * shieldLength; j++) {
                    canvas.drawRect(new Rect(j - 1, getHeight() - 50, j, getHeight() - 10), internalShieldMeter);
            }
        }

        for (int j = 1; j < shield.getMaxCharge() * shieldLength; j++) {
            if (j < shield.getCharge() * shieldLength) {
                canvas.drawRect(new Rect(j - 1, getHeight() - 50, j, getHeight() - 10), internalShieldMeter);
            }
        }

        /* Draw weapon1 meter */
        Paint weaponMeterPaint = new Paint();
        weaponMeterPaint.setStyle(Paint.Style.STROKE);
        weaponMeterPaint.setColor(Color.RED);
        weaponMeterPaint.setStrokeWidth(2);
        canvas.drawRect(new Rect(0, getHeight() - 100, 1000, getHeight() - 60), weaponMeterPaint);



        Paint weapon1MeterInternalPaint = new Paint();
        weapon1MeterInternalPaint.setColor(ContextCompat.getColor(getContext(), R.color.weapon1MeterInternal));


        /* Weapon Meter */

        for (int j = 1; j < weapon1.getMaxCharge() * weapon1Length; j++) {
            if (j < weapon1.getCharge() * weapon1Length) {
                canvas.drawRect(new Rect(j - 1, getHeight() - 100, j, getHeight() - 50 - 10), weapon1MeterInternalPaint);
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
