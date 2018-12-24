package com.sapfil.sbergame.devopsIcons;

import com.badlogic.ashley.core.Engine;
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

public class DevOpsHolder {

    private Array<TextureRegion> array0 = new Array<>();
    private Array<TextureRegion> array1 = new Array<>();
    private Array<TextureRegion> array2 = new Array<>();
    private Array<TextureRegion> array3 = new Array<>();
    private Array<TextureRegion> array4 = new Array<>();
    private Array<TextureRegion> array5 = new Array<>();
    private Array<TextureRegion> array6 = new Array<>();
    private Array<TextureRegion> array7 = new Array<>();

    private GfxComponent c0;
    private GfxComponent c1;
    private GfxComponent c2;
    private GfxComponent c3;
    private GfxComponent c4;
    private GfxComponent c5;
    private GfxComponent c6;
    private GfxComponent c7;

    private int type;

    public DevOpsHolder(String textureName, int type) {

        this.type = type;

        Texture texture = new Texture(textureName);

        array0.add(new TextureRegion(texture, 0, 0, 200, 100));
        array1.add(new TextureRegion(texture, 100, 100, 100, 50));
        array2.add(new TextureRegion(texture, 0, 100, 50, 100));
        array3.add(new TextureRegion(texture, 100, 150, 100, 50));
        array4.add(new TextureRegion(texture, 200, 0, 100, 100));
        array5.add(new TextureRegion(texture, 200, 100, 100, 50));
        array6.add(new TextureRegion(texture, 50, 100, 50, 100));
        array7.add(new TextureRegion(texture, 200, 150, 100, 50));

        c0 = new GfxComponent(array0);
        c1 = new GfxComponent(array1);
        c2 = new GfxComponent(array2);
        c3 = new GfxComponent(array3);
        c4 = new GfxComponent(array4);
        c5 = new GfxComponent(array5);
        c6 = new GfxComponent(array6);
        c7 = new GfxComponent(array7);
    }

    private int coins;
    private List<Entity> list = new LinkedList<>();

    public int addCoin() {
        Entity entity = new Entity();

        entity.add(new BackComponent());

        switch (coins++) {
            case 0: {
                entity.add(c0.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2));
                break;
            }
            case 1: {
                entity.add(c1.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2 - 70, SCREEN_HEIGHT / 2 + 42.5f));
                break;
            }
            case 2: {
                entity.add(c2.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2 - 125, SCREEN_HEIGHT / 2));
                break;
            }
            case 3: {
                entity.add(c3.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2 - 75, SCREEN_HEIGHT / 2 - 47.5f));
                break;
            }
            case 4: {
                entity.add(c4.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2));
                break;
            }
            case 5: {
                entity.add(c5.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2 + 75, SCREEN_HEIGHT / 2 + 42.5f));
                break;
            }
            case 6: {
                entity.add(c6.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2 + 120, SCREEN_HEIGHT / 2 - 2.5f));
                break;
            }
            case 7: {
                entity.add(c7.clone());
                entity.add(new PositionComponent(SCREEN_WIDTH / 2 + 70, SCREEN_HEIGHT / 2 - 47.5f));
                break;
            }
            case 8:
                cancel();
                return -1;
        }

        list.add(entity);
        SberGame.engine.addEntity(entity);

        return coins;
    }

    public void cancel() {
        coins = 0;
        for (Entity entity : list) {
            SberGame.engine.removeEntity(entity);
        }
    }

    public int getType() {
        return type;
    }
}
