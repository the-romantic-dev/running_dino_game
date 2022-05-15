package com.dinogame.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;
import com.dinogame.model.Cactus;
import com.dinogame.model.CactusSpawner;
import com.dinogame.model.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CactiView {

    Map<Integer, Integer> spritesById;
    private CactusSpawner spawner;
    //список со спрайтами кактусов. Для разнообразия.
    private ArrayList<Texture> sprites;
    private ArrayList<CactiView> views;
    private int firstId;

    public CactiView(CactusSpawner spawner) {
        this.spawner = spawner;
        sprites = new ArrayList<>();
        sprites.add(new Texture(Config.CACTUS_1_SPRITE_NAME));
        sprites.add(new Texture(Config.CACTUS_2_SPRITE_NAME));
        spritesById = new HashMap<>();
    }

    public void drawCactus(SpriteBatch batch, Cactus cactus) {
        int id = cactus.getId();
        if (spritesById.get(id) == null) {
            int randIndex = ThreadLocalRandom.current().nextInt(0, sprites.size());
            spritesById.put(id, randIndex);
        }
        Texture sprite = sprites.get(spritesById.get(id));
        batch.draw(sprite, cactus.getSpriteRectangle().x, cactus.getSpriteRectangle().y);

    }

    //отрисовываем все кактусы. Вызывается каждый кадр.
    public void drawAll(SpriteBatch batch) {
        upddateFirstId();
        for (Cactus cactus : spawner.getCacti()) {
            drawCactus(batch, cactus);
        }
    }

    private void upddateFirstId() {
        if (spawner.getCacti().size() > 0) {
            int lastIndex = spawner.getCacti().size() - 1;
            int lastId = spawner.getCacti().get(lastIndex).getId();
            if (lastId - lastIndex != firstId) {
                spritesById.remove(firstId);
                firstId = lastId - lastIndex;
            }
        }
    }

    public void dispose() {
        sprites.forEach(Texture :: dispose);
    }
}
