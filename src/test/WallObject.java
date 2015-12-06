package test;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

import java.awt.*;

/**
 * Created by Евгений on 24.11.2015.
 *
 * Объект тайла стены.
 */
public class WallObject extends GameObject {

    public static final int WALL_SIZE = 48;

    public static final int LEFT_UP_CORNER_BRICK_WALL = 0;
    public static final int UP_BRICK_WALL = 1;
    public static final int RIGHT_UP_CORNER_BRICK_WALL = 2;
    public static final int RIGHT_BRICK_WALL = 3;
    public static final int RIGHT_DOWN_CORNER_BRICK_WALL = 4;
    public static final int DOWN_BRICK_WALL = 5;
    public static final int LEFT_DOWN_CORNER_BRICK_WALL = 6;
    public static final int LEFT_BRICK_WALL = 7;


    private Image wallImage;

    public WallObject(int id, int subtype) {
        super(id, GameObjectCategory.Environment);
        switch (subtype) {
            case LEFT_UP_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerup_left_tr_shadow.png");
                break;

            case UP_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/brick wall_gorizontal_tr.png");
                break;

            case RIGHT_UP_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerup_right_tr_shadow.png");
                break;

            case RIGHT_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/wallvert_left_tr(26).png");
                break;

            case RIGHT_DOWN_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerdown_right_tr.png");
                break;

            case DOWN_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/brick wall_gorizontal_tr.png");
                break;

            case LEFT_DOWN_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerdown_ left_tr.png");
                break;

            case LEFT_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/wallvert_left_tr(26).png");
                break;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(wallImage, x * WALL_SIZE, y * WALL_SIZE, WALL_SIZE, WALL_SIZE, null);
    }

    @Override
    public void update() {

    }
}
