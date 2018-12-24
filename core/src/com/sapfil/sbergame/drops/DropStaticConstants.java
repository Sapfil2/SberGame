package com.sapfil.sbergame.drops;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sapfil.sbergame.ashley.components.GfxComponent;

public class DropStaticConstants {

    private static Array<TextureRegion> array0 = new Array<>();
    private static Array<TextureRegion> array1 = new Array<>();
    private static Array<TextureRegion> array2 = new Array<>();
    private static Array<TextureRegion> array3 = new Array<>();
    private static Array<TextureRegion> array4 = new Array<>();
    private static Array<TextureRegion> array5 = new Array<>();
    private static Texture dropsTeture = new Texture("coin.png");

    static {
        for (int i = 0; i < 23; i++) {
            array0.add(new TextureRegion(dropsTeture, i * 32, 0, 32, 32));
            array1.add(new TextureRegion(dropsTeture, i * 32, 32, 32, 32));
            array2.add(new TextureRegion(dropsTeture, i * 32, 64, 32, 32));
            array3.add(new TextureRegion(dropsTeture, i * 32, 96, 32, 32));
            array4.add(new TextureRegion(dropsTeture, i * 32, 128, 32, 32));
            array5.add(new TextureRegion(dropsTeture, i * 32, 160, 32, 32));
        }
    }


    public static GfxComponent redGfxComponent = new GfxComponent(0.05f, array0, Animation.PlayMode.LOOP);
    public static GfxComponent purplefxComponent = new GfxComponent(0.05f, array1, Animation.PlayMode.LOOP);
    public static GfxComponent cyanGfxComponent = new GfxComponent(0.05f, array2, Animation.PlayMode.LOOP);
    public static GfxComponent blueGfxComponent = new GfxComponent(0.05f, array3, Animation.PlayMode.LOOP);
    public static GfxComponent greenGfxComponent = new GfxComponent(0.05f, array4, Animation.PlayMode.LOOP);
    public static GfxComponent yellowGfxComponent = new GfxComponent(0.05f, array5, Animation.PlayMode.LOOP);

}
