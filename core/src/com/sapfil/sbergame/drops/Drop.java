package com.sapfil.sbergame.drops;

import com.badlogic.ashley.core.Entity;
import com.sapfil.sbergame.ashley.components.drops.DropComponent;
import com.sapfil.sbergame.ashley.components.PositionComponent;
import com.sapfil.sbergame.ashley.components.VelocityXYComponent;

public class Drop extends Entity {

    private int type;

    public Drop(float x, float y, float speedX, float speedY, int type) {

        this.type = type;
        switch (type) {
            case 0:
                this.add(DropStaticConstants.redGfxComponent.clone());
                break;
            case 1:
                this.add(DropStaticConstants.purplefxComponent.clone());
                break;
            case 2:
                this.add(DropStaticConstants.cyanGfxComponent.clone());
                break;
            case 3:
                this.add(DropStaticConstants.blueGfxComponent.clone());
                break;
            case 4:
                this.add(DropStaticConstants.greenGfxComponent.clone());
                break;
            case 5:
                this.add(DropStaticConstants.yellowGfxComponent.clone());
                break;
        }

        this.add(new PositionComponent(x, y));
        this.add(new VelocityXYComponent(speedX, speedY));
        this.add(new DropComponent(type));
    }

    public int getType() {
        return type;
    }
}


