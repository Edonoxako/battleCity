package game.ui.text;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

public class PauseText extends GameObject{
	private Image bodyImage;
	private Point position;
	private AffineTransform af;
	public PauseText(int id, int x, int y) {
		
		super(id, GameObjectCategory.UI);
		// TODO Auto-generated constructor stub
		bodyImage = ResourceLoader.loadImage("UI/pause.png");
		position = new Point(x, y);
		af = AffineTransform.getTranslateInstance(position.x - bodyImage.getWidth(null)/2
				, position.y - bodyImage.getHeight(null)/2);
		af.concatenate(AffineTransform.getRotateInstance(Math.toRadians(5)));
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(bodyImage, af, null);
	}

	@Override
	public void update() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision(int x, int y, GameObject obj) {
		// TODO Auto-generated method stub
		
	}
	
}
