package test;

import java.awt.Graphics2D;
import java.awt.Image;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

/**
 * Created by Евгений on 24.11.2015.
 *
 * Объект тайла стены.
 */
public class BgObject extends GameObject {

    public static final int WALL_SIZE = 48;

    //public static final int LEFT_UP_CORNER_BRICK_WALL = 0;
    public static final int UP_BRICK_WALL = 1;
    public static final int RIGHT_UP_CORNER_BRICK_WALL = 2;
    public static final int RIGHT_BRICK_WALL = 3;
    public static final int RIGHT_DOWN_CORNER_BRICK_WALL = 4;
    public static final int DOWN_BRICK_WALL = 5;
    public static final int LEFT_DOWN_CORNER_BRICK_WALL = 6;
    public static final int LEFT_BRICK_WALL = 7;
    public static final int SAND = 0;

    private Image wallImage;

    public BgObject(int id, int subtype) {
        super(id, GameObjectCategory.Background);
        switch (subtype) {
            case SAND:
                wallImage = ResourceLoader.loadImage("bg\\main_sand.png");
                break;
        }
    }

    @Override
    public void draw(Graphics2D g) {
    	/*AlphaComposite ac =
				  AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.7);
		AlphaComposite tc = (AlphaComposite) g.getComposite();
		g.setComposite(ac);
		g.drawImage(wallImage, x * WALL_SIZE, y * WALL_SIZE, WALL_SIZE, WALL_SIZE, null);
		g.setComposite(tc);*/

		g.drawImage(wallImage, x * WALL_SIZE, y * WALL_SIZE, WALL_SIZE, WALL_SIZE, null);
        
    }

    @Override
    public void update() {

    }
}
