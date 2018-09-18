package com.yellowbytestudios.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class DashLines {

    private Player player;
    private ArrayList<DashLine> lines;
    private static final float fadeSpeed = 0.03f;
    private static final float scaleSpeed = 0.4f;

    DashLines(Player player) {
        this.player = player;
        lines = new ArrayList<DashLine>();
    }

    public void addDashLine() {
        lines.add(new DashLine(player.getCenterX(), player.getCenterY()));
    }

    public void update() {
        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i) != null) {
                    DashLine line = lines.get(i);
                    if (i == lines.size() - 1 && player.isMoving()) {
                        line.setX2(player.getCenterX());
                        line.setY2(player.getCenterY());
                    } else {
                        if (line.getOpacity() == 1f) {
                            line.setX2(player.getCenterX());
                            line.setY2(player.getCenterY());
                            line.setOpacity(line.getOpacity() - fadeSpeed);
                        }

                        line.setOpacity(line.getOpacity() - fadeSpeed);
                        line.setWidth(line.getWidth() - scaleSpeed);
                        if (line.getOpacity() <= 0f) {
                            lines.remove(line);
                        }
                    }
                }
            }
        }
    }

    public void render(ShapeRenderer sr) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.GOLD);

        for (int i = 0; i < lines.size(); i++) {
            DashLine line = lines.get(i);
            sr.getColor().a = line.getOpacity();
            sr.rectLine(line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getWidth());
        }
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    private class DashLine {

        private float x1, y1, x2, y2;
        private float opacity;
        private float width;

        DashLine(float x1, float y1) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1;
            this.y2 = y1;
            opacity = 1f;
            width = 20f;
        }

        float getX1() {
            return x1;
        }

        float getY1() {
            return y1;
        }

        float getX2() {
            return x2;
        }

        void setX2(float x2) {
            this.x2 = x2;
        }

        float getY2() {
            return y2;
        }

        void setY2(float y2) {
            this.y2 = y2;
        }

        float getOpacity() {
            return opacity;
        }

        void setOpacity(float opacity) {
            this.opacity = opacity;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }
    }
}
