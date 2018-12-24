package com.sapfil.sbergame.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sapfil.sbergame.SberGame;
import com.sapfil.sbergame.ashley.components.GfxComponent;
import com.sapfil.sbergame.ashley.components.PositionComponent;

public class Screen extends Entity {

    public Screen(String image){
        Array<TextureRegion> array = new Array<>();
        array.add(new TextureRegion(new Texture(image)));
        this.add( new GfxComponent(0, array, Animation.PlayMode.NORMAL));
        this.add( new PositionComponent(SberGame.SCREEN_WIDTH/2, SberGame.SCREEN_HEIGHT/2));
    }

}
