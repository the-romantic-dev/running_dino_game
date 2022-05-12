package com.dinogame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.model.GameModel;

//основное представление view. Просто делается отрисовка того, что выдает model.
public class DinoGame extends ApplicationAdapter {
    SpriteBatch batch;
    GameModel model;

    //делается перед запуском графики
    @Override
    public void create() {
        batch = new SpriteBatch();
        model = new GameModel();
    }

    //выполянется каждый кадр
    @Override
    public void render() {
        model.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        model.draw(batch);
        batch.end();
    }

    //после выключения удаляет все текстуры.
    @Override
    public void dispose() {
        batch.dispose();
        model.dispose();
    }
}
