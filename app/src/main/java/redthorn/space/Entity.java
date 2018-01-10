package redthorn.space;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaricthorning on 3/1/18.
 */
public abstract class Entity {

    private float x; //x coordinate
    private float y; //y coordinate

    List<Rect> layoutRects = new ArrayList<>();

    public Entity(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    void addLayoutRect(Rect rect){
        layoutRects.add(rect);
    }

    void clearLayoutRects(){
        layoutRects = new ArrayList<>();
    }

    List<Rect> getLayoutRects(){
        return this.layoutRects;
    }

}
