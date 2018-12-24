package com.sapfil.sbergame.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyboardCtrlComponent implements Component {

    public boolean isKeyPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.ANY_KEY);
    }

    public int getKeyPressed(){

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
