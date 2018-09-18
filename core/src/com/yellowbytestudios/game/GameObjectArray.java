package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class GameObjectArray {

    private ArrayList<GameObject> objects;
    private ArrayList<Integer> objectsToRemove;

    public GameObjectArray() {
        objects = new ArrayList<GameObject>();
        objectsToRemove = new ArrayList<Integer>();
    }

    public void render(SpriteBatch sb) {
        for (GameObject gameObject : objects) {
            gameObject.render(sb);
        }
    }

    public void add(GameObject gameObject) {
        objects.add(gameObject);
    }

    public GameObject get(int index) {
        return objects.get(index);
    }

    public int size() {
        return objects.size();
    }

    public void remove(int index) {
        objectsToRemove.add(index);
    }

    public void doRemovals() {
        for(Integer index : objectsToRemove) {
            objects.remove((int) index);
        }
        objectsToRemove.clear();
    }
}
