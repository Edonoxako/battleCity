package test;

import core.model.GameObject;
import core.model.GameObjectType;

import java.awt.*;

/**
 * Created by Евгений on 10.11.2015.
 *
 * Просто движущийся квадрат
 */
public class MovingObject extends GameObject {

    private Color color;

    public MovingObject(int id, GameObjectType type) {
        super(id, type);

        setX(10);
        setY(10);

        color = new Color(100, 200, 128);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(getX(), getY(), 20, 20);
    }

    @Override
    public void update() {
        setX(getX() + 3);
    }
}
