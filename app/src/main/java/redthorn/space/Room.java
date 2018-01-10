package redthorn.space;

import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by jaricthorning on 3/1/18.
 */
public class Room {

    private String name;
    private float relativeX;
    private float relativeY;
    private float length;
    private float width;
    private int color;


    public Room(String name, float relativeX, float relativeY, float width, float length, int color){
        this.name = name;
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.length = length;
        this.width = width;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRelativeX() {
        return relativeX;
    }

    public float getRelativeY() {
        return relativeY;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public int getColor(){
        return color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room " + this.name + ": ");
        sb.append("+" + relativeX + " ");
        sb.append("+" + relativeY + " ");
        sb.append("len " + length + " ");
        sb.append("width" + width + " ");
        sb.append("color" + color);
        return sb.toString();
    }
}
