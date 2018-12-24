package com.sapfil.sbergame.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GfxComponent extends Animation implements Component {

    private float lifeTime = 0.0f;
    private float width, height;



    public GfxComponent(Array keyFrames) {
        this(0, keyFrames, PlayMode.LOOP);
    }

    public GfxComponent(float frameDuration, Array keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
        this.width = ((TextureRegion) this.getKeyFrame(0)).getRegionWidth();
        this.height = ((TextureRegion) this.getKeyFrame(0)).getRegionHeight();
    }

    public void render(SpriteBatch pSB, PositionComponent component){
            pSB.draw((TextureRegion) this.getKeyFrame(this.lifeTime),                   //texture region
                    component.getX() - width/2,
                    component.getY() - height/2,
                    0,0,   //deform-center
                    width,              //width
                    height,             //height
                    1,     //stretching deform (default = 1)
                    1,
                    0
            );
    }

    public void update(float dt){
        lifeTime += dt;
    }

    public GfxComponent clone(){

        Array<TextureRegion> array = new Array<>();
        for (Object object : this.getKeyFrames()){
            array.add((TextureRegion) object);
        }

        return new GfxComponent(this.getFrameDuration(), array, this.getPlayMode());
    }
}
