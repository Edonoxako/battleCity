package test;

import core.managers.ObjectManager;
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
    private int frameCount;
    private int dx = 1;
    private ObjectManager objectManager;

    public MovingObject(int id, GameObjectType type, ObjectManager objectManager) {
        super(id, type);
        frameCount = 0;

        setX(10);
        setY(10);

        this.color = new Color(100, 200, 128);
        this.objectManager = objectManager;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(getX(), getY(), 20, 20);
    }

    @Override
    public void update() {
        setX(getX() + dx);

        frameCount++;
        if (frameCount == 360) {
           frameCount=0;
           dx = -dx;
        }
    }
}
