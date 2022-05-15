package com.dinogame.model;

import com.dinogame.Config;

//клас, представляющий нашего персонажа (динозаврика)
public class Hero extends GameObject {
    private HeroState heroState;

    public Hero() {
        super(Config.START_X, Config.GROUND_LEVEL, Config.HERO_WIDTH, Config.HERO_HEIGHT);
        heroState = HeroState.STOPPED;
        //устанавливаем размер коллайдера так, чтобы столкновения были реалистичными
        setCollider(16, 16, 41, 71);
    }

    public HeroState getHeroState() {
        return heroState;
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

    //перечисление для отслеживания состояния динозварика
    public enum HeroState {
        JUMPED, RUNNING, STOPPED
    }
}
