package com.sapfil.sbergame;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sapfil.sbergame.ashley.components.GfxComponent;
import com.sapfil.sbergame.ashley.components.KeyboardCtrlComponent;
import com.sapfil.sbergame.ashley.components.PositionComponent;
import com.sapfil.sbergame.ashley.components.VelocityComponent;

public class Hero extends Entity {

    public final static float SPEED = 1000;

    public Hero(){
        this.add(new PositionComponent(40,40));
        this.add(new VelocityComponent());
        this.add(new KeyboardCtrlComponent());
        Array<TextureRegion> array = new Array<>();
        array.add(new TextureRegion(new Texture("hero-fur.png")));

        this.add(new GfxComponent(0, array, Animation.PlayMode.NORMAL));
    }
}
