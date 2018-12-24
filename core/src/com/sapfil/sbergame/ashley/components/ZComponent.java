package com.sapfil.sbergame.ashley.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Sapfil on 08.10.2017.
 */

public class ZComponent implements Comparable<ZComponent>, Component {

    private float z;

    ZComponent(){}
    ZComponent(float z){
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public int compareTo(ZComponent other) {
        if (this.z == other.getZ())
            return 0;
        else
        if (this.z > other.getZ())
            return 1;
        else return  -1;
    }
}
