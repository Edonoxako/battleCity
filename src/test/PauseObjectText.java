package test;

import java.awt.Graphics2D;

import core.managers.ObjectManager;
import core.model.GameObject;
import core.model.GameObjectType;

public class PauseObjectText extends GameObject{

	public PauseObjectText(int id, GameObjectType type,ObjectManager obm) {
		super(id, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawString("PAUSE", 100, 100);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
