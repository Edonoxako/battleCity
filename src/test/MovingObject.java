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

    public static final int START_FRAME_COUNT = 40;
    public static final int END_FRAME_COUNT = 100;
    public static final int VELOCITY = 6;

    private Color color;
    private int frameCount;

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
        if (frameCount > START_FRAME_COUNT) {
            g.setColor(color);
            g.fillRect(getX(), getY(), 20, 20);
        }
    }

    @Override
    public void update() {
        if (frameCount > START_FRAME_COUNT) {
            setX(getX() + VELOCITY);

            if (frameCount > END_FRAME_COUNT) {
                objectManager.removeObject(getId());
            }
        }
        frameCount++;
    }
}
