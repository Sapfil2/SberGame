package com.sapfil.sbergame.ashley.mappers;

import com.badlogic.ashley.core.ComponentMapper;
import com.sapfil.sbergame.ashley.components.DeformComponent;
import com.sapfil.sbergame.ashley.components.drops.DropComponent;
import com.sapfil.sbergame.ashley.components.GfxComponent;
import com.sapfil.sbergame.ashley.components.PositionComponent;
import com.sapfil.sbergame.ashley.components.VelocityXYComponent;
import com.sapfil.sbergame.ashley.components.ZComponent;

/**
 * Created by Sapfil on 08.10.2017.
 */

public class Mappers {

    public static final ComponentMapper<PositionComponent> positionMapper
            = ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<DeformComponent> deformMapper
            = ComponentMapper.getFor(DeformComponent.class);

    public static final ComponentMapper<VelocityXYComponent> velocityXYMapper
            = ComponentMapper.getFor(VelocityXYComponent.class);

    public static final ComponentMapper<ZComponent> zMapper
            = ComponentMapper.getFor(com.sapfil.sbergame.ashley.components.ZComponent.class);

    public static final ComponentMapper<GfxComponent> gfxMapper
            = ComponentMapper.getFor(com.sapfil.sbergame.ashley.components.GfxComponent.class);

    public static final ComponentMapper<DropComponent> dropMapper
            = ComponentMapper.getFor(DropComponent.class);
}
