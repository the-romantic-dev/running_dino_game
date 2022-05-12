package com.dinogame;

//хранит настройки игры
public class Config {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 480;
    public static final int GROUND_LEVEL = WINDOW_HEIGHT / 2;
    public static final int RUN_SPEED = 7;
    public static final float GRAVITY = 0.5f;
    public static final float JUMP_POWER = 12;
    public static final int START_X = 100;
    public static final int HERO_ANIMATION_FRAMES = 2;

    public static final String HERO_SPRITE_NAME = "dino_sprite.png";
    public static final String GROUND_SPRITE_NAME = "dino_ground.png";
    public static final String HERO_ANIMATION_NAME = "dino_states.png";
    public static final String CACTUS_1_SPRITE_NAME = "cactus_1.png";
    public static final String CACTUS_2_SPRITE_NAME = "cactus_2.png";
    public static final String NUMBERS_SPRITE_NAME = "numbers.png";
    public static final String BUTTON_SPRITE_NAME = "restart_button.png";
}
