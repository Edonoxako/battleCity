package game.object;

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

    public static final int SAND = 0;
    public static final int SNOW = 1;
    public static final int STONE = 2;

    private Image wallImage;

    public BgObject(int id, int subtype) {
        super(id, GameObjectCategory.Background);
        switch (subtype) {
            case SAND:
                wallImage = ResourceLoader.loadImage("bg\\main_sand.png");
                break;
            case SNOW:
            	wallImage = ResourceLoader.loadImage("bg\\snow.png");
            	break;
            case STONE:
            	wallImage = ResourceLoader.loadImage("bg\\stone_road.png");
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

		g.drawImage(wallImage, dmx + x * WALL_SIZE, dmy + y * WALL_SIZE, WALL_SIZE, WALL_SIZE, null);
        
    }

    @Override
    public void update() {

    }
}
