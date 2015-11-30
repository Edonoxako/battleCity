package test;

import java.awt.Graphics2D;
import java.awt.Image;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectType;
import core.utils.ResourñeLoader;

public class TestButton extends GameObject{

	/**
	 *
	 * 
	 */
	private Image body;
	private Image body_deactive;
	private Image body_active;
	private boolean active;
	public TestButton(int id) {
		super(id, GameObjectType.UI);
		body_deactive = ResourñeLoader.loadImage("UI/options.png");
		body_active = ResourñeLoader.loadImage("UI/options_active.png");
		setActive(false);
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
			g.drawImage(body, (Scene.getSize().width/2)-(body.getWidth(null)/2), this.getY(), null);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	public boolean isActive() {
		return active;
	}
	public void action(){
		App.stateManager.pop();
		App.stateManager.peek().unBlock();
		App.pauseFlag = false;
	}
	public void setActive(boolean active) {
		if(active){
			body = body_active;
		}else{
			body = body_deactive;
		}
		this.active = active;
	}
	
}
