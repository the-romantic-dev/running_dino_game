package com.dinogame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {

    public static boolean isSpacePressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }
}
