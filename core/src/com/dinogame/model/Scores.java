package com.dinogame.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dinogame.Config;

//класс создает игровые очки в углу экрана.
public class Scores {
    //массив, где будут храниться спрайты цифр
    private final TextureRegion[] numbers;
    //ширина одной цифры
    private final int numberWidth;
    //местоположение на экране
    private final Vector2 position;
    //текстура со спрайтами цифр
    Texture sprite;

    public Scores() {
        sprite = new Texture(Config.NUMBERS_SPRITE_NAME);
        position = new Vector2(Config.WINDOW_WIDTH - 50, Config.WINDOW_HEIGHT - 50);
        numberWidth = sprite.getWidth() / 10;
        //делим изначальную текстуру на части шириной в одну цифру и заносим из в массив
        TextureRegion[][] splitted = TextureRegion.split(sprite, numberWidth, sprite.getHeight());
        numbers = new TextureRegion[10];
        for (int i = 0; i < splitted[0].length; i++) {
            numbers[i] = splitted[0][i];
        }
    }

    //отрисовываем очки равные пройденному игровому времени. Вызывается каждый кадр.
    public void draw(SpriteBatch batch, float gameTime) {
        int time = (int) gameTime;
        int k = 0;
        if (time == 0) batch.draw(numbers[0], position.x, position.y);
        while (time > 0) {
            int num = time % 10;
            batch.draw(numbers[num], position.x - numberWidth * k, position.y);
            time /= 10;
            k++;
        }

    }

    public void dispose() {
        sprite.dispose();
    }

}
