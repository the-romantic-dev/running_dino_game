package com.dinogame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;

//представление model и controller
public class GameModel {
    //игровые сущности
    private Hero hero;
    private Ground ground;
    private CactusSpawner cactusSpawner;
    private Scores scores;
    //текущее время с начала игры.
    private float gameTime;
    private GameState gameState;

    public GameModel() {
        gameTime = 0;
        hero = new Hero(Config.HERO_SPRITE_NAME);
        ground = new Ground(Config.GROUND_SPRITE_NAME);
        cactusSpawner = new CactusSpawner();
        gameState = GameState.START;
        scores = new Scores();
    }

    //функция для управления. Т.к. игра управляется одним пробелом,
    //то нет смысла создавать отдельный класс под контроллер
    private void checkController() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            switch (gameState) {
                case START:
                    startGame();
                    hero.jump();
                    break;
                case RUN:
                    hero.jump();
                    break;
                case STOP:
                    restartGame();
                    break;
            }
        }
    }

    //обновляет состояние персонажа
    private void heroUpdate() {
        if (hero.checkCollision(ground) && hero.velocity.y < 0) {
            hero.run();
            hero.setVelocity(0, 0);
        }
        if (hero.getHeroState() == Hero.HeroState.JUMPED) {
            hero.accelerate(0, -Config.GRAVITY);
        }
        hero.move();
    }

    //обоновляет состояние генератора кактусов
    private void cactusSpawnerUpdate() {
        cactusSpawner.spawn(gameTime);
        cactusSpawner.checkInvisible();
        if (cactusSpawner.checkHeroCollision(hero)) {
            stopGame();
        }
        cactusSpawner.updatePosition();
    }

    //обновляет состояние игры
    public void update(float time) {
        checkController();
        if (gameState == GameState.RUN) {
            gameTime += time;
            heroUpdate();
            cactusSpawnerUpdate();
            ground.move();
        }
    }

    //отрисовываем все текстуры
    public void draw(SpriteBatch batch) {
        scores.draw(batch, gameTime);
        cactusSpawner.draw(batch);
        ground.draw(batch);
        hero.draw(batch, gameTime);
        if (gameState == GameState.STOP) {
            drawRestartButton(batch);
        }
    }

    //остановить игру (проигрыш)
    public void stopGame() {
        gameState = GameState.STOP;
        hero.stop();
    }

    //начинаем бежать
    public void startGame() {
        gameState = GameState.RUN;
    }

    //заново начинаем игру
    public void restartGame() {
        gameState = GameState.START;
        gameTime = 0;
        cactusSpawner.restart();
        hero.changePositon(Config.START_X, Config.GROUND_LEVEL);
    }

    //рисует кнопку при проигрыше
    private void drawRestartButton(SpriteBatch batch) {
        Texture button = new Texture(Config.BUTTON_SPRITE_NAME);
        int positonX = (Config.WINDOW_WIDTH - button.getWidth()) / 2;
        int positonY = (Config.WINDOW_HEIGHT - button.getHeight()) / 2;
        batch.draw(button, positonX, positonY - 50);
    }

    //очищаем память от лишних текстур.
    public void dispose() {
        hero.dispose();
        ground.dispose();
        scores.dispose();
    }

    private enum GameState {
        START, STOP, RUN
    }
}
