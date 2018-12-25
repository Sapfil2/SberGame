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
import com.sapfil.sbergame.devopsIcons.PromHolder;
import com.sapfil.sbergame.devopsIcons.TimeHolder;
import com.sapfil.sbergame.drops.Drop;
import com.sapfil.sbergame.screens.Screen;

public class SberGame extends ApplicationAdapter {

    private enum State{
        START, GAME, LOSE, WIN
    }

    public static Engine engine;

    public static final float DROP_PREIOD_DEFAULT = 0.5f;
    public static final float DROP_SPEED_DEFAULT = -100.0f;
    public static final float DROP_CATCH_HEIGHT = 80;
    public static final float DROP_CATCH_WIDTH = 40;

    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = 480;
    public static final float CLOCK_POINT_SECONDS_DEFAULT = 32.0f;

    SpriteBatch batch;
    Hero hero;

    DevOpsHolder redHolder, yellowHolder, greenHolder, blueHolder, cyanHolder, purpleHolder, currentHolder;
    PromHolder promHolder;
    TimeHolder timeHolder;

    Family gfxActiveFamily = Family.all(GfxComponent.class, PositionComponent.class)
            .exclude(BackComponent.class)
            .get();

    Family gfxBackFamily = Family.all(GfxComponent.class, PositionComponent.class, BackComponent.class).get();

    Family dropFamily = Family.all(DropComponent.class, PositionComponent.class).get();

    float dropCounter = 0.0f, timeCounter = 0.0f;

    int[] levelMap = {0,4,2,5,3,1,0};
    int difficulty = 3;
    float dropSpeed = DROP_SPEED_DEFAULT;
    float dropPeriod = DROP_PREIOD_DEFAULT;
    float clockTime = CLOCK_POINT_SECONDS_DEFAULT;

    Screen startScreen;
    Screen winScreen;
    Screen loseScreen;

    State state;

    @Override
    public void create() {
            engine = new Engine();
            batch = new SpriteBatch();

            startScreen = new Screen("Start-screen.png");
            winScreen = new Screen("win-screen.png");
            loseScreen = new Screen("lose-screen.jpg");
            initState(State.START);
    }

    private void initState(State state){

        this.state = state;

        difficulty = 3;
        dropSpeed = DROP_SPEED_DEFAULT;
        dropPeriod = DROP_PREIOD_DEFAULT;
        clockTime = CLOCK_POINT_SECONDS_DEFAULT;

        engine.removeAllEntities();
        switch (state){
            case START:{
                engine.addEntity(startScreen);
                break;
            }
            case GAME: {
                hero = new Hero();

                redHolder = new DevOpsHolder("devops-red.png", 0);
                yellowHolder = new DevOpsHolder("devops-yellow.png", 5);
                greenHolder = new DevOpsHolder("devops-green.png", 4);
                blueHolder = new DevOpsHolder("devops-blue.png", 3);
                cyanHolder = new DevOpsHolder("devops-cyan.png", 2);
                purpleHolder = new DevOpsHolder("devops-purple.png", 1);

                promHolder = new PromHolder();
                timeHolder = new TimeHolder();

                currentHolder = greenHolder;

                engine.addEntity(hero);
                break;
            }
            case LOSE:{
                engine.addEntity(loseScreen);
                break;
            }
            case WIN:{
                engine.addEntity(winScreen);
                break;
            }

        }
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

        switch (state) {
            case GAME: {

                PositionComponent heroPosition = Mappers.positionMapper.get(hero);

                timeCounter += dt;

                if (timeCounter > clockTime) {
                    timeCounter = 0;
                    timePointGot();
                }

                dropCounter += dt;

                if (dropCounter >= dropPeriod) {
                    dropCounter = 0;
                    int nextDrop = levelMap[(int) (Math.random() * difficulty)];

                    engine.addEntity(new Drop(
                                    (float) (Math.random() * SCREEN_WIDTH),
                                    SCREEN_HEIGHT,
                                    0,
                                    (float) (Math.random() / 2 * dropSpeed + dropSpeed),
                                    nextDrop
                            )
                    );
                }

                for (Entity entity : engine.getEntitiesFor(dropFamily)) {
                    PositionComponent positionComponent = Mappers.positionMapper.get(entity);
                    DropComponent dropComponent = Mappers.dropMapper.get(entity);
                    if (dropComponent.update(dt,
                            positionComponent,
                            Mappers.velocityXYMapper.get(entity)
                    ) != 0) {
                        engine.removeEntity(entity);
                    } else {
                        if (positionComponent.getY() < DROP_CATCH_HEIGHT && Math.abs(positionComponent.getX() - heroPosition.getX()) < DROP_CATCH_WIDTH) {
                            updateDevOpsIcon(dropComponent.getType());
                            engine.removeEntity(entity);
                        }
                    }
                }

                if (isKeyPressed()) {

                    switch (getKeyPressed()) {
                        case Input.Keys.LEFT: {
                            moveHeroLeft(dt, heroPosition);
                            break;
                        }
                        case Input.Keys.RIGHT: {
                            moveHeroRight(dt, heroPosition);
                            break;
                        }

                        default: {
                        }
                    }
                }

                inputHandler(dt);

                break;
            }
            case WIN:
            case LOSE:
            case START:{
                if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                    initState(State.GAME);
                }
                break;
            }
        }

    }

    private void moveHeroRight(float dt, PositionComponent heroPosition) {
        heroPosition.setX(heroPosition.getX() + Hero.SPEED * dt);
        if (heroPosition.getX() > SCREEN_WIDTH - 40) {
            heroPosition.setX(SCREEN_WIDTH - 40);
        }
    }

    private void moveHeroLeft(float dt, PositionComponent heroPosition) {
        heroPosition.setX(heroPosition.getX() - Hero.SPEED * dt);
        if (heroPosition.getX() < 40) {
            heroPosition.setX(40);
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

        if (currentHolder.addCoin() == -1){
            winPointGot(currentHolder.getType());
        }
    }

    private void winPointGot(int type){
        int points = promHolder.addPoint();

        if (points == 16){
            winGame();
        }

        if (points > timeHolder.getPointsCount()) {
            clockTime -= 1 ;
        } else {
            clockTime += 2;
        }

        if (points%4 == 0){
            difficulty++;
            dropSpeed = DROP_SPEED_DEFAULT * (1 + (difficulty - 3)* 0.2f);
        } else {
            dropSpeed *= 1.1f;
            dropPeriod = DROP_PREIOD_DEFAULT/(-dropSpeed/100);
        }
    }

    private void timePointGot(){
        int timePoints = timeHolder.addPoint();

        if (timeHolder.getPointsCount() > promHolder.getPointsCount()) {
            clockTime += 1;
        }

        if (timePoints == 12){
            hero.wearHat();
        }

        if (timePoints == 16){
            looseGame();
        }


    }

    private void looseGame(){
        initState(State.LOSE);
    }

    private void winGame(){
        initState(State.WIN);
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



    private void inputHandler(float dt) {

        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            PositionComponent heroPosition =  Mappers.positionMapper.get(hero);
            if (Math.abs(x - heroPosition.getX()) > 10){
               if (x < heroPosition.getX()) {
                   moveHeroLeft(dt, heroPosition);
               } else{
                   moveHeroRight(dt, heroPosition);
               }
            }
        }
    }

}
