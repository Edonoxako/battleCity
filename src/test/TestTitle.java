package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

public class TestTitle extends GameObject{
	private double nx;
	private double ny;
	private double dx = 0.2;
	private double dy = 0.1;
	private Image bodyImage;
	private Point position;
	public TestTitle(int id, int x, int y) {
		super(id, GameObjectCategory.UI);
		// TODO Auto-generated constructor stub
		bodyImage = ResourceLoader.loadImage("UI/logo_sample.png");
		position = new Point(x, y);
		setX(x);
		setY(y);
		nx = x;
		ny = y;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(bodyImage, position.x-bodyImage.getWidth(null)/2, 
		position.y, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(this.getX() - nx >= 0){
			if(this.getX() - nx > 5){
				dx = -dx;
			}
			nx += dx;
		}else{
			if(this.getX() - nx < -5){
				dx = -dx;
			}
			nx += dx;
		}
		if(this.getY() - ny >= 0){
			if(this.getY() - ny > 5){
				dy = -dy;
			}
			ny +=dy;
		}else{
			if(this.getY() - ny < -5){
				dy = -dy;
			}
			ny += dy;
		}
		position.setLocation(nx, ny);
	}
}
