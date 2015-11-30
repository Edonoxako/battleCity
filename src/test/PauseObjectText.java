package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import core.graphics.Scene;
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
		int width, height;
		width = Scene.getSize().width;
		height = Scene.getSize().height;
		g.setColor(new Color(0xffffffff));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Verdana", Font.BOLD, 48));
		g.setColor(Color.red);
		g.drawString("PAUSE", width/2-100, height/2);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
