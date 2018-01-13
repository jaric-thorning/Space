package redthorn.space.Weapons;

import android.util.Log;

import redthorn.space.Ticker;

/**
 * Created by jaricthorning on 13/1/18.
 */
public abstract class Weapon {

    private static final String TAG = "SpaceGame:Weapon";

    protected String name;
    public float damage = 1; //default 1 damage
    protected float rechargeTime = 10; //default 10 seconds recharge
    protected boolean readyToFire = false;
    protected boolean autoFire = false;
    protected String target = null;

    protected double charge = 0;

    protected double maxCharge = 1000;


    public Weapon(String name){
        this.name = name;
        Ticker.ticker.addMethod(this.tick);

    }

    public String getName() {
        return name;
    }

    public boolean isReadyToFire(){return readyToFire;};


    public float getDamage() {
        return damage;
    }

    public float getRechargeTime() {
        return rechargeTime;
    }

    public String getTarget() {
        return target;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAutoFire(boolean autoFire) {
        this.autoFire = autoFire;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }

    public void setRechargeTime(float rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void fire(){
        if(this.target != null){
            Log.d(TAG, this.name + " firing at " + this.target);

        }
        else {
            Log.d(TAG,"No target set, firing into the void.");
        }

        this.charge = 0;
    }

    public double getCharge() {
        return charge;
    }

    public double getMaxCharge() {
        return maxCharge;
    }

    private void onTick(){

        /*Charge weapon and check if ready to fire */

        this.charge += (double) maxCharge / (rechargeTime * Ticker.ticker.getTicksPerSecond());
        if(this.charge >= maxCharge){
            readyToFire = true;
            this.charge = maxCharge;
        } else{
            readyToFire = false;
        }


        //Log.d(TAG,this.name + "charged to " + this.charge);


    }

    final Runnable tick = new Runnable() {
        @Override
        public void run() {
            onTick();
        }
    };

}
