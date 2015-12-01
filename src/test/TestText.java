package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import core.model.GameObject;
import core.model.GameObjectType;
import core.utils.ResourņeLoader;

public class TestText extends GameObject{
	private Image bodyImage;
	private Point position;
	public TestText(int id, int x, int y) {
		super(id, GameObjectType.UI);
		// TODO Auto-generated constructor stub
		bodyImage = ResourņeLoader.loadImage("UI/pause.png");
		position = new Point(x, y);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(bodyImage, position.x-bodyImage.getWidth(null)/2, 
				position.y-bodyImage.getHeight(null)/2, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
