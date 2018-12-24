package com.sapfil.sbergame.ashley.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Sapfil on 08.10.2017.
 */

public class VelocityXYComponent implements Component {

    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================

    private float speedX, speedY;

    // ===========================================================
    // Constructors
    // ===========================================================

    // Using private constructor and inner Builder (see below)
    // UPD wtf - changed to public
    public VelocityXYComponent(float sppedX, float speedY){
        this.speedX = sppedX;
        this.speedY = speedY;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces (U/R/D)
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
