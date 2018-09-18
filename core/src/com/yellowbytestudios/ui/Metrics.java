package com.yellowbytestudios.ui;

import static com.yellowbytestudios.MainGame.HEIGHT;
import static com.yellowbytestudios.MainGame.WIDTH;

public class Metrics {
    public static final float CENTER_X = WIDTH / 2;
    public static final float CENTER_Y = HEIGHT / 2;

    public static float marginX(int size) {
        return (WIDTH/40) * size;
    }

    public static float marginY(int size) {
        return (HEIGHT/80) * size;
    }
}
