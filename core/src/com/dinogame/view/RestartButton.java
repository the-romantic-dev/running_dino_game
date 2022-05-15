package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;

public class RestartButton {
    private Texture sprite;
    private int x;
    private int y;

    public RestartButton() {
        sprite = new Texture(Config.BUTTON_SPRITE_NAME);
        x = (Config.WINDOW_WIDTH - sprite.getWidth()) / 2;
        y = (Config.WINDOW_HEIGHT - sprite.getHeight()) / 2 - 50;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, x, y);
    }
}

