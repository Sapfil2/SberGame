package com.sapfil.sbergame.ashley.components.drops;

import com.badlogic.ashley.core.Component;
import com.sapfil.sbergame.ashley.components.PositionComponent;
import com.sapfil.sbergame.ashley.components.VelocityXYComponent;

public class DropComponent implements Component {

    private int type;

    public DropComponent(int type){
        this.type = type;
    }

    public int update(float dt, PositionComponent positionComponent, VelocityXYComponent velocityXYComponent){
        if (positionComponent.getY() < 0) {
            return -1;
        }

        positionComponent.setX(positionComponent.getX() + velocityXYComponent.getSpeedX() * dt);
        positionComponent.setY(positionComponent.getY() + velocityXYComponent.getSpeedY() * dt);
        return 0;
    }

    public int getType() {
        return type;
    }
}
