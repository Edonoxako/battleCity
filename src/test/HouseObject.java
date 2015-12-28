package test;

import java.awt.Graphics2D;
import java.awt.Image;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

public class HouseObject extends GameObject{
	 public static final int WALL_SIZE = 48;
	    
		public static final int DOOR = 0;
		public static final int HOME_LEFT = 1;
	    public static final int HOME_RIGHT = 2;
	    public static final int HOME_MIDDLE = 3;
	    public static final int ROOF_LEFT = 4;
	    public static final int ROOF_RIGHT = 5;
	    public static final int ROOF_MIDDLE = 6;
	    public static final int ROOF_CORNER = 7;
	    public static final int ROOF_DOWN = 8;
	    public static final int HOME_REAR = 9;
	    private Image wallImage;

	    public HouseObject(int id, int subtype) {
	        super(id, GameObjectCategory.Environment);
	        switch (subtype) {
	            case DOOR:
	                wallImage = ResourceLoader.loadImage("object\\home_door.png");
	                break;
	            case HOME_LEFT:
	            	wallImage = ResourceLoader.loadImage("object\\home_left.png");
	            	break;
	            case HOME_RIGHT:
	            	wallImage = ResourceLoader.loadImage("object\\home_right.png");
	            	break;
	            case HOME_MIDDLE:
	            	wallImage = ResourceLoader.loadImage("object\\home_middle.png");
	            	break;
	            case ROOF_LEFT:
	                wallImage = ResourceLoader.loadImage("object\\roof.png");
	                break;
	            case ROOF_RIGHT:
	            	wallImage = ResourceLoader.loadImage("object\\roof_right.png");
	            	break;
	            case ROOF_MIDDLE:
	            	wallImage = ResourceLoader.loadImage("object\\roof_middle.png");
	            	break;
	            case ROOF_DOWN:
	            	wallImage = ResourceLoader.loadImage("object\\roof1.png");
	            	break;
	            case ROOF_CORNER:
	            	wallImage = ResourceLoader.loadImage("object\\roof_corner_1.png");
	            	break;
	            case HOME_REAR:
	            	wallImage = ResourceLoader.loadImage("object\\home_rear.png");
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
