package com.sapfil.sbergame.ashley.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Sapfil on 08.10.2017.
 */

public class DeformComponent implements Component {

    private float origin_x, origin_y, scaleX = 1.0f, scaleY = 1.0f;

    public void setOriginXY(float pX, float pY) {
        this.origin_x = pX;
        this.origin_y = pY;
    }
    public float getOriginX() {     return origin_x;      }
    public float getOriginY() {     return origin_y;      }

    public void setScale(float pSX, float pSY) {
        this.scaleX = pSX;
        this.scaleY = pSY;
    }

}
