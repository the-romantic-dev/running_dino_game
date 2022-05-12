package com.dinogame.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dinogame.Config;

//клас, представляющий нашего персонажа (динозаврика)
public class Hero extends GameObject {
    //анимация для динозварика
    private Animation<TextureRegion> runAnimation;
    private HeroState heroState;

    public Hero(String spritePath) {
        super(spritePath, Config.START_X, Config.GROUND_LEVEL);
        heroState = HeroState.STOPPED;
        //устанавливаем размер коллайдера так, чтобы столкновения были реалистичными
        setCollider(16, 16, 41, 71);
        setRunAnimation();
    }

    public HeroState getHeroState() {
        return heroState;
    }

    //функция для отрисовки. В зависимости от состояния рисует стоящего, или бегущего динозаврика
    public void draw(SpriteBatch batch, float time) {
        if (heroState == HeroState.RUNNING) {
            TextureRegion region = runAnimation.getKeyFrame(time, true);
            batch.draw(region, spriteRectangle.x, spriteRectangle.y);
        } else {
            batch.draw(sprite, spriteRectangle.x, spriteRectangle.y);
        }
    }

    //вызываем, когда динозаврик прыгает
    public void jump() {
        if (heroState != HeroState.JUMPED) {
            setVelocity(0, Config.JUMP_POWER);
            heroState = HeroState.JUMPED;
        }
    }

    //вызываем, когда динозаврик приземляется и начинает бежать
    public void run() {
        heroState = HeroState.RUNNING;
    }

    //вызываем, когда динозаврик сталкивается с препятствием и останавливается.
    //изначальное состяние динозаврика до старта тоже STOPPED
    public void stop() {
        heroState = HeroState.STOPPED;
    }

    //устанавливаем анимацию бега
    private void setRunAnimation() {
        Texture animation = new Texture(Config.HERO_ANIMATION_NAME);
        TextureRegion[][] running = TextureRegion.split(animation, animation.getWidth() / Config.HERO_ANIMATION_FRAMES, animation.getHeight());
        TextureRegion[] frames = new TextureRegion[Config.HERO_ANIMATION_FRAMES];

        for (int i = 0; i < Config.HERO_ANIMATION_FRAMES; i++) {
            frames[i] = running[0][i];
        }

        runAnimation = new Animation<>(1f / Config.RUN_SPEED, frames);
    }

    //перечисление для отслеживания состояния динозварика
    public enum HeroState {
        JUMPED, RUNNING, STOPPED
    }
}
