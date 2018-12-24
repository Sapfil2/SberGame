package com.sapfil.sbergame.ashley.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Sapfil on 08.10.2017.
 */

public class VelocityComponent implements Component {

    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================

    // "All Coordinates" field group
    private float vABS, rotationSpeed;

    // ===========================================================
    // Constructors
    // ===========================================================

    // Using private constructor and inner Builder (see below)
    // UPD wtf - changed to public
    public VelocityComponent(){}

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public void setSpeedV(float pV) {
        vABS = pV;
    }

    public float getSpeedV() {
        return vABS;
    }
    public float getRotationSpeed() {
        return rotationSpeed;
    }
    public void setRotationSpeed(float pVR) {
        this.rotationSpeed = pVR;
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
