package game.object;

import java.awt.Graphics2D;
import java.awt.Image;

import core.App;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.GameObjectType;
import core.utils.ResourceLoader;

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
    
    public int up,down,left,right,leftup,leftdown,rightup,rightdown;

    private Image wallImage;

    public WallObject(int id, int subtype) {
        super(id, GameObjectCategory.Environment);
        switch (subtype) {
            case LEFT_UP_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerup_left_tr_shadow.png");
                left = 19; 
                right = 28;
                down = 43;
                up = 0;
                rightdown = 7;
                break;

            case UP_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/brick wall_gorizontal_tr.png");
                left = 0; 
                right = 0;
                down = 43;
                up = 34;
                break;

            case RIGHT_UP_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerup_right_tr_shadow.png");
                left = 0; 
                right = 28;
                down = 0;
                up = 34;
                leftdown = 43;
                break;

            case RIGHT_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/wallvert_left_tr(26).png");
                left = 19; 
                right = 28;
                down = 0;
                up = 0;
                break;

            case RIGHT_DOWN_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerdown_right_tr.png");
                left = 0; 
                right = 28;
                down = 43;
                up = 34;
                leftup = 19;
                break;

            case DOWN_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/brick wall_gorizontal_tr.png");
                left = 0; 
                right = 0;
                down = 43;
                up = 34;
                break;

            case LEFT_DOWN_CORNER_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/cornerdown_ left_tr.png");
                left = 19; 
                right = 0;
                down = 43;
                up = 0;
                rightup = 28;
                break;

            case LEFT_BRICK_WALL:
                wallImage = ResourceLoader.loadImage("blocks/wallvert_left_tr(26).png");
                left = 19; 
                right = 28;
                down = 0;
                up = 0;
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

	@Override
	public void collision(int x, int y, GameObject obj) {
		if(obj.getType() == GameObjectType.SHELL){
			int splash_delay = 10;
//    		App.objectManager.addObject(new SplashEfect(x*48, 24+y*48, dmx, dmy, splash_delay));
//    		App.objectManager.addObject(new SplashEfect(x*48, y*48, dmx, dmy, splash_delay));
//    		App.objectManager.addObject(new SplashEfect(x*48+24, y*48, dmx, dmy, splash_delay));
//    		App.objectManager.addObject(new SplashEfect(x*48-24, y*48, dmx, dmy, splash_delay));
//    		App.objectManager.addObject(new SplashEfect(x*48, y*48-24, dmx, dmy, splash_delay));
    		App.objectManager.removeObject(getId(), getCategory());
		}
		
	}
}
