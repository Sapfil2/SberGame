package com.sapfil.sbergame.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Sapfil on 08.10.2017.
 */

public class PositionComponent implements Component{
    private float x,y;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }



    public void copy(PositionComponent coordinates){
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    public void add(PositionComponent coordinates){
        this.x += coordinates.getX();
        this.y += coordinates.getY();
    }

    public void subtract(PositionComponent coordinates){
        this.x -= coordinates.getX();
        this.y -= coordinates.getY();
    }

    public float Angle_Calculation(){
        return - MathUtils.atan2(x, y)
                * MathUtils.radiansToDegrees;
    }

    public float Distance_Calculation(){
        return (float) Math.hypot(x, y);
    }

}
