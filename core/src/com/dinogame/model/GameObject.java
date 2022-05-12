package com.dinogame.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

//абстракция под большинство объектов в игре
public class GameObject {
    //у любого объекта есть текстура (изображение)
    protected Texture sprite;
    //прямоугольник, отображающий положение текстуры на экране
    protected Rectangle spriteRectangle;
    //у любого объекта есть "коллайдер" - прямоугольник для отслеживания столкновений.
    //можно отследить столкновение с другим объектом, и сделать что-то.
    protected Rectangle collider;
    //ускорение объекта
    protected Vector2 velocity;

    public GameObject(String spriteInternalPath, float positionX, float positionY) {
        sprite = new Texture(spriteInternalPath);
        spriteRectangle = new Rectangle(positionX, positionY, sprite.getWidth(), sprite.getHeight());
        collider = new Rectangle(spriteRectangle);
        velocity = new Vector2(0, 0);

    }

    //установить коллайдер
    //x и y задаются относительно положения текстуры.
    public void setCollider(float x, float y, float width, float height) {
        collider.x = spriteRectangle.x + x;
        collider.y = spriteRectangle.y + y;
        collider.width = width;
        collider.height = height;
    }

    //функция для "телепортации" объекта на новое место в обход ускорения.
    public void changePositon(float newX, float newY) {
        float distanceX = newX - spriteRectangle.x;
        float distanceY = newY - spriteRectangle.y;
        collider.x += distanceX;
        collider.y += distanceY;
        spriteRectangle.x += distanceX;
        spriteRectangle.y += distanceY;

    }

    //функция для движения объекта в соответствии со скоростью. Вызывается каждый кадр.
    public void move() {
        spriteRectangle.x += velocity.x;
        spriteRectangle.y += velocity.y;
        collider.x += velocity.x;
        collider.y += velocity.y;
    }

    //придать объекту ускорение
    public void accelerate(float velocityX, float velocityY) {
        velocity.x += velocityX;
        velocity.y += velocityY;
    }

    //установить ускорение объекта
    public void setVelocity(float velocityX, float velocityY) {
        velocity.x = velocityX;
        velocity.y = velocityY;
    }

    //проверка на столкновение
    public boolean checkCollision(GameObject object) {
        return this.collider.overlaps(object.collider);
    }

    //функция для отрисовки на экране
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, spriteRectangle.x, spriteRectangle.y);
    }

    //функция для удаления после использования, чтобы не захламлять память.
    public void dispose() {
        sprite.dispose();
    }
}
