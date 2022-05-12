package com.dinogame.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dinogame.Config;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//класс для генерации препятствий (кактусов) на пути динозаврика.
public class CactusSpawner{
    //место создания кактусов
    private Vector2 spawnPosition;
    //список со спрайтами кактусов. Для разнообразия.
    private ArrayList<String> sprites;
    //список с отображаемыми на экране кактусами.
    private ArrayList<GameObject> cacti;
    //промежуток времени между кактусами. Задается рандомно после каждого создания.
    private double spawnTime;


    public CactusSpawner() {
        spawnPosition = new Vector2(Config.WINDOW_WIDTH, Config.GROUND_LEVEL);
        sprites = new ArrayList<>();
        sprites.add(Config.CACTUS_1_SPRITE_NAME);
        sprites.add(Config.CACTUS_2_SPRITE_NAME);
        cacti = new ArrayList<>();
        spawnTime = 0;
    }

    //создает кактусы. Вызывается каждый кадр.
    public void spawn(float gameTime) {
        if (spawnTime < gameTime) {
            int spriteNumber = ThreadLocalRandom.current().nextInt(0,  2);
            cacti.add(createCactus(spriteNumber));
            spawnTime = ThreadLocalRandom.current().nextDouble(gameTime + 0.8,gameTime + 2);
        }
    }

    //создаем объект кактуса
    private GameObject createCactus(int spriteNumber) {
        GameObject cactus = new GameObject(sprites.get(spriteNumber), spawnPosition.x, spawnPosition.y);
        //все кактусы двигаются в направлении игрока
        cactus.setVelocity(-Config.RUN_SPEED, 0);
        //делаем поменьше коллайдер кактуса, чтобы удобнее играть было
        cactus.setCollider(0.2f * cactus.spriteRectangle.width, 0, 0.6f * cactus.spriteRectangle.width, cactus.spriteRectangle.height);
        return cactus;
    }

    //отрисовываем все кактусы. Вызывается каждый кадр.
    public void draw(SpriteBatch batch) {
        for (GameObject cactus : cacti) {
            cactus.draw(batch);
        }
    }

    //двигает все кактусы. Вызывается каждый кадр.
    public void updatePosition() {
        for (GameObject cactus : cacti) {
            cactus.move();
        }
    }

    //удаляет кактус, если он ушел за пределы экрана
    public void checkInvisible() {
        if (!cacti.isEmpty() && cacti.get(0).spriteRectangle.x < -cacti.get(0).spriteRectangle.width) {
            cacti.get(0).dispose();
            cacti.remove(0);
        }
    }

    //проверяет столкновение кактуса с персонажем
    public boolean checkHeroCollision(Hero hero) {
        for (GameObject cactus : cacti) {
            if (cactus.checkCollision(hero)) return true;
        }
        return false;
    }

    //удаляет все кактусы. Вызывается при рестарте игры
    private void removeAll() {
        if (!cacti.isEmpty()) {
            for (int i = 0; i < cacti.size(); i++) {
                cacti.get(i).dispose();
            }
            cacti.clear();
        }
    }

    public void restart() {
        removeAll();
        spawnTime = 0;
    }
}
