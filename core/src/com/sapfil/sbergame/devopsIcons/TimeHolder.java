package com.sapfil.sbergame.devopsIcons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sapfil.sbergame.SberGame;
import com.sapfil.sbergame.ashley.components.BackComponent;
import com.sapfil.sbergame.ashley.components.GfxComponent;
import com.sapfil.sbergame.ashley.components.PositionComponent;

import java.util.LinkedList;
import java.util.List;

import static com.sapfil.sbergame.SberGame.SCREEN_HEIGHT;
import static com.sapfil.sbergame.SberGame.SCREEN_WIDTH;

public class TimeHolder {

    private Array<TextureRegion> array0 = new Array<>();
    private Array<TextureRegion> array1 = new Array<>();

    private GfxComponent c0;
    private GfxComponent c1;

    private List<Entity> list = new LinkedList<>();

    public TimeHolder() {
        Texture texture0 = new Texture("time-line.png");
        Texture texture1 = new Texture("time-point.png");

        array0.add(new TextureRegion(texture0));
        array1.add(new TextureRegion(texture1));

        c0 = new GfxComponent(array0);
        c1 = new GfxComponent(array1);

        Entity lineEntity = new Entity();
        lineEntity.add(new PositionComponent(SCREEN_WIDTH/2, SCREEN_HEIGHT - 24));
        lineEntity.add(new GfxComponent(array0));
        lineEntity.add(new BackComponent());

        SberGame.engine.addEntity(lineEntity);
    }

    public int addPoint(){
        Entity pointEntity = new Entity();
        pointEntity.add(new PositionComponent(80 + list.size()*36, SCREEN_HEIGHT - 24));
        pointEntity.add(new GfxComponent(array1));
        pointEntity.add(new BackComponent());
        SberGame.engine.addEntity(pointEntity);
        list.add(pointEntity);
        return list.size();
    }

    public int getPointsCount(){
        return list.size();
    }
}
