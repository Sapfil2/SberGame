package com.sapfil.sbergame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sapfil.sbergame.ashley.components.BackComponent;
import com.sapfil.sbergame.ashley.components.drops.DropComponent;
import com.sapfil.sbergame.ashley.components.GfxComponent;
import com.sapfil.sbergame.ashley.components.PositionComponent;
import com.sapfil.sbergame.ashley.mappers.Mappers;
import com.sapfil.sbergame.devopsIcons.DevOpsHolder;
import com.sapfil.sbergame.drops.Drop;

public class SberGame extends ApplicationAdapter {

    public static Engine engine;

    public static final float DROP_PREIOD = 1.0f;
    public static final float DROP_SPEED = -100.0f;
    public static final float DROP_CATCH_HEIGHT = 80;
    public static final float DROP_CATCH_WIDTH = 40;

    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = 480;

    SpriteBatch batch;
    Hero hero;

    DevOpsHolder redHolder, yellowHolder, greenHolder, blueHolder, cyanHolder, purpleHolder, currentHolder;

    Family gfxActiveFamily = Family.all(GfxComponent.class, PositionComponent.class)
            .exclude(BackComponent.class)
            .get();

    Family gfxBackFamily = Family.all(GfxComponent.class, PositionComponent.class, BackComponent.class).get();

    Family dropFamily = Family.all(DropComponent.class, PositionComponent.class).get();

    float dropCounter = 0.0f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        engine = new Engine();
        hero = new Hero();

        redHolder = new DevOpsHolder("devops-red.png", 0);
        yellowHolder = new DevOpsHolder("devops-yellow.png", 5);
        greenHolder = new DevOpsHolder("devops-green.png", 4);
        blueHolder = new DevOpsHolder("devops-blue.png", 3);
        cyanHolder = new DevOpsHolder("devops-cyan.png", 2);
        purpleHolder = new DevOpsHolder("devops-purple.png", 1);

        currentHolder = greenHolder;

        engine.addEntity(hero);
    }

    @Override
    public void render() {

        this.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        for (Entity entity : engine.getEntitiesFor(gfxBackFamily)) {
            GfxComponent gfxComponent = Mappers.gfxMapper.get(entity);
            gfxComponent.render(batch, Mappers.positionMapper.get(entity));
        }

        for (Entity entity : engine.getEntitiesFor(gfxActiveFamily)) {
            GfxComponent gfxComponent = Mappers.gfxMapper.get(entity);
            gfxComponent.update(Gdx.graphics.getDeltaTime());
            gfxComponent.render(batch, Mappers.positionMapper.get(entity));
        }

        batch.end();
    }

    private void update(float dt) {

        PositionComponent heroPosition = Mappers.positionMapper.get(hero);

        dropCounter += dt;

        if (dropCounter >= DROP_PREIOD){
            dropCounter = 0;
            engine.addEntity(new Drop(
                    (float)(Math.random() * SCREEN_HEIGHT),
                    SCREEN_HEIGHT,
                    0,
                    (float)(Math.random() / 2 * DROP_SPEED + DROP_SPEED),
                    (int)(Math.random() * 6)
                    )
            );
        }

        for (Entity entity : engine.getEntitiesFor(dropFamily)) {
            PositionComponent positionComponent = Mappers.positionMapper.get(entity);
            DropComponent dropComponent = Mappers.dropMapper.get(entity);
            if (dropComponent.update(dt,
                    positionComponent,
                    Mappers.velocityXYMapper.get(entity)
                    ) != 0){
                engine.removeEntity(entity);
            } else {
                if (positionComponent.getY() < DROP_CATCH_HEIGHT && Math.abs(positionComponent.getX() - heroPosition.getX()) < DROP_CATCH_WIDTH){
                    updateDevOpsIcon(dropComponent.getType());
                    engine.removeEntity(entity);
                }
            }
        }

        if (isKeyPressed()) {

            switch (getKeyPressed()) {
                case Input.Keys.LEFT: {
                    heroPosition.setX(heroPosition.getX() - Hero.SPEED * dt);
                    if (heroPosition.getX() < 40){
                        heroPosition.setX(40);
                    }
                    break;
                }
                case Input.Keys.RIGHT: {
                    heroPosition.setX(heroPosition.getX() + Hero.SPEED * dt);
                    if (heroPosition.getX() > SCREEN_WIDTH - 40){
                        heroPosition.setX(SCREEN_WIDTH - 40);
                    }
                    break;
                }

                default: {}
            }
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void updateDevOpsIcon(int type){

        if (currentHolder.getType() != type){
            currentHolder.cancel();
        }

        switch (type){
            case 0: currentHolder = redHolder; break;
            case 1: currentHolder = purpleHolder; break;
            case 2: currentHolder = cyanHolder; break;
            case 3: currentHolder = blueHolder; break;
            case 4: currentHolder = greenHolder; break;
            case 5: currentHolder = yellowHolder; break;
        }

        currentHolder.addCoin();
    }

    private boolean isKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.ANY_KEY);
    }

    private int getKeyPressed() {

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {

            if (Gdx.input.isKeyPressed(Input.Keys.UP))
                return Input.Keys.UP;

            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
                return Input.Keys.DOWN;

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                return Input.Keys.LEFT;

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                return Input.Keys.RIGHT;
        }

        // -1 is reserved for anykey, 0 for unknown key
        return -2;
    }


}
