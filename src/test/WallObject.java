package test;

import core.model.GameObject;
import core.model.GameObjectType;

import java.awt.*;

/**
 * Created by Евгений on 24.11.2015.
 *
 * Объект тайла стены.
 */
public class WallObject extends GameObject {

    public static final int WALL_SIZE = 20;

    private int x;
    private int y;

    public WallObject(int id, int x, int y) {
        super(id, GameObjectType.WALL);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(x * WALL_SIZE, y * WALL_SIZE, WALL_SIZE, WALL_SIZE);
    }

    @Override
    public void update() {

    }
}
