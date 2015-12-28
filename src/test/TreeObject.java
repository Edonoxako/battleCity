package test;

import java.awt.Graphics2D;
import java.awt.Image;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

public class TreeObject extends GameObject {
    public static final int WALL_SIZE = 48;
    
	public static final int TREE = 0;
	public static final int SHADOW_TREE = 1;
    public static final int SNOW_TREE = 2;

    private Image wallImage;

    public TreeObject(int id, int subtype) {
        super(id, GameObjectCategory.Environment);
        switch (subtype) {
            case TREE:
                wallImage = ResourceLoader.loadImage("object\\tree.png");
                break;
            case SHADOW_TREE:
            	wallImage = ResourceLoader.loadImage("object\\tree_shadow.png");
            	break;
            case SNOW_TREE:
            	wallImage = ResourceLoader.loadImage("object\\tree_snow.png");
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

