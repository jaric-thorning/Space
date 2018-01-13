package redthorn.space;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.util.Log;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import redthorn.space.Systems.Shield;
import redthorn.space.Weapons.MissileLauncher;
import redthorn.space.Weapons.Weapon;

/**
 * Created by jaricthorning on 3/1/18.
 */
public class Spaceship extends Entity {

    private static final String TAG = "SpaceGame";

    List<Room> rooms = new ArrayList<>();

    private Shield shield;

    private float x;
    private float y;
    private float centerOffsetX;
    private float centerOffsetY;
    private float minShieldRadius;

    private List<Weapon> availableWeapons = new ArrayList<>();


    public Spaceship(int x, int y){

        super(x, y);
        this.x = x;
        this.y = y;

        this.shield = new Shield(3, 1000, 2);

        addRoom(new Room("Left", 0, 0, 200, 150, Color.RED));
        addRoom(new Room("Forward", 200, 50, 250, 225, Color.BLACK));
        addRoom(new Room("Right", 0, 175, 200, 150, Color.RED));

        this.centerOffsetX = 200;
        this.centerOffsetY = 150 + 12;
        this.minShieldRadius = 300;


        availableWeapons.add(new MissileLauncher());
        updateLayoutRects();

        /* Activate ticker */
        Ticker.ticker.addMethod(this.tick);
    }

    void addRoom(Room room){
        Log.d(TAG, "addingRoom: " + room.toString());
        rooms.add(room);
    }

    public Room getRoomAt(int x, int y){
        for(Room room : rooms){
            if((x > this.x + room.getRelativeX()) && (x < this.x + room.getRelativeX() + room.getWidth())){
                if((y > this.y + room.getRelativeY()) && (y < this.y + room.getRelativeY() + room.getLength())){
                 return room;
                }
            }
        }
        return null;
    }

    public void draw(View view, Canvas canvas){
        for(Room room : rooms){
            int x1 = (int) (this.x + room.getRelativeX());
            int y1 = (int) (this.y + room.getRelativeY());
            int x2 = (int) (this.x + room.getRelativeX() + room.getWidth());
            int y2 = (int) (this.y + room.getRelativeY() + room.getLength());

            Paint roomPaint = new Paint();
            roomPaint.setColor(room.getColor());

            canvas.drawRect(new Rect(x1, y1, x2, y2), roomPaint);

        }

        Paint shieldPaint = new Paint();
        shieldPaint.setColor(Color.BLUE);
        shieldPaint.setStyle(Paint.Style.STROKE);
        shieldPaint.setStrokeWidth(2);

        Paint shieldInternalPaint = new Paint();
        shieldInternalPaint.setColor(view.getResources().getColor(R.color.shieldInternal));

        if(this.shield.getLevel() > 0) {
            canvas.drawCircle(this.x + centerOffsetX, this.y + this.centerOffsetY,
                    this.minShieldRadius, shieldPaint);

        }

        for(int i = 0; i < this.shield.getLevel(); i++) {
            canvas.drawCircle(this.x + centerOffsetX, this.y + this.centerOffsetY,
                    this.minShieldRadius, shieldInternalPaint);
        }
    }
    void updateLayoutRects(){
        Log.d(TAG, "updatingLayoutRects");
        clearLayoutRects();
        for(Room room : rooms){
            int x1 = (int) (this.x + room.getRelativeX());
            int y1 = (int) (this.y + room.getRelativeY());
            int x2 = (int) (this.x + room.getRelativeX() + room.getWidth());
            int y2 = (int) (this.y + room.getRelativeY() + room.getLength());

            addLayoutRect(new Rect(x1, y1, x2, y2));

        }
    }

    public void regenShield(){
        this.shield.doRegen();
    }

    public void objectHit(){
        if(this.shield.getLevel() > 0){
            this.shield.takeDamage(400);
        } else {
            Log.d(TAG, "Ship damaged.");
        }
    }
    public void moveTo(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Shield getShield(){
        return shield;
    }

    public Weapon getWeapon1(){
        if(availableWeapons.size() >= 1){
            return availableWeapons.get(0);
        }
        return null;
    }


    public void onTick(){

        for(Weapon weapon : availableWeapons){
            if(weapon.isReadyToFire()){
                weapon.fire();
            }
        }
    }

    final Runnable tick = new Runnable() {
        @Override
        public void run() {
            onTick();
        }
    };



}
