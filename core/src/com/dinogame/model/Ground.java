package com.dinogame.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;

//класс, представлюящий землю (беговую дорожку)
//генерируем как бы "две" земли, чтобы когда одна уходила за экран, другая ее заменяла
public class Ground extends GameObject {
    public Ground(String spritePath) {
        super(spritePath, 0, Config.WINDOW_HEIGHT / 2);
        setVelocity(-Config.RUN_SPEED, 0);
        setCollider(0, 0, collider.width * 2, collider.height);
    }

    //отрисовываем две земли друг за другом. Вызывается каждый кадр.
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, spriteRectangle.x, spriteRectangle.y);
        batch.draw(sprite, spriteRectangle.x + spriteRectangle.width, spriteRectangle.y);
    }

    //двигаем землю. Если первая земля ушла за экран, перемещаем земли на изначальные координаты. Вызывается каждый кадр.
    @Override
    public void move() {
        super.move();
        if (spriteRectangle.x <= -spriteRectangle.width) {
            changePositon(0, spriteRectangle.y);
        }
    }
}
