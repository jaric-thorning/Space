package redthorn.space.Systems;

import android.util.Log;

/**
 * Created by jaricthorning on 9/1/18.
 */
public class Shield {
    private float regen;
    private float charge;
    private float maxCharge;
    private int level;
    private int maxLevel;

    private static final String TAG = "SpaceGame";


    public Shield(int maxLevel, float maxCharge, float regen){
        this.maxLevel = maxLevel;
        this.level = this.maxLevel;
        this.maxCharge = maxCharge;
        this.regen = regen;
        this.charge = maxCharge;
    }

    public void takeDamage(float damage){

        this.charge = this.charge - damage;
        Log.d(TAG, "Hit shield. Shield at " + this.charge);
        if(this.charge < 0){
            this.level--;
            if(this.level <= 0){
                this.level = 0;
                this.charge = 0;
            } else {
                Log.d(TAG, "Shield lost level.");
                this.charge = 0;
            }
        }
    }

    public float getMaxCharge() {
        return maxCharge;
    }

    public void doRegen(){
        this.charge = this.charge + regen;
        if(this.charge >= maxCharge){
            this.level++;
            if(this.level >= maxLevel){
                this.level = maxLevel;
                this.charge = maxCharge;
            } else {
                Log.d(TAG, "Shield level up.");
                this.charge = 0;
            }
        }
    }

    public float getCharge() {
        return charge;
    }

    public int getLevel() {
        return level;
    }
}
