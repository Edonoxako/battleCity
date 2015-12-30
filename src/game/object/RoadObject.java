
package game.object;

import java.awt.Graphics2D;
import java.awt.Image;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

public class RoadObject extends GameObject {
	
    public static final int WALL_SIZE = 48;

    //public static final int LEFT_UP_CORNER_BRICK_WALL = 0;
    public static final int ROAD_VERT = 1;
    public static final int ROAD_GOR = 2;
    public static final int ROAD_LEFT_UP = 3;
    public static final int ROAD_LEFT_DOWN= 4;
    public static final int ROAD_RIGHT_UP = 5;
    public static final int ROAD_RIGHT_DOWN = 6;
    public static final int ROAD_TURN_UP = 7;
    public static final int ROAD_TURN_DOWN= 8;
    public static final int ROAD_TURN_LEFT = 9;
    public static final int ROAD_TURN_RIGHT = 10;
    
    private Image wallImage;

    public RoadObject(int id, int subtype) {
        super(id, GameObjectCategory.Background);
        switch (subtype) {
            case ROAD_VERT:
                wallImage = ResourceLoader.loadImage("bg\\road.png");
                break;
            case ROAD_GOR:
            	wallImage = ResourceLoader.loadImage("bg\\road_gor.png");
            	break;
            case ROAD_LEFT_UP:
            	wallImage = ResourceLoader.loadImage("bg\\road_turn_up.png");
            	break;
            case ROAD_LEFT_DOWN:
            	wallImage = ResourceLoader.loadImage("bg\\road_turn_left.png");
            	break;
            case ROAD_RIGHT_UP:
            	wallImage = ResourceLoader.loadImage("bg\\road_turn_down.png");
            	break;
            case ROAD_RIGHT_DOWN:
            	wallImage = ResourceLoader.loadImage("bg\\road_turn_right.png");
            	break;
            case ROAD_TURN_UP:
            	wallImage = ResourceLoader.loadImage("bg\\turn_up.png");
            	break;
            case ROAD_TURN_DOWN:
            	wallImage = ResourceLoader.loadImage("bg\\turn_down.png");
            	break;
            case ROAD_TURN_LEFT:
            	wallImage = ResourceLoader.loadImage("bg\\turn_left.png");
            	break;
            case ROAD_TURN_RIGHT:
            	wallImage = ResourceLoader.loadImage("bg\\turn_right.png");
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

