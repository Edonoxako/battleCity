package test;

import java.awt.Graphics2D;

import core.App;
import core.ui.Button;
import core.utils.Resour�eLoader;

public class TestButton extends Button{

	/**
	 *
	 * 
	 */
	public TestButton(int id) {
		super(id, Resour�eLoader.loadImage("UI/options_active.png"), Resour�eLoader.loadImage("UI/options.png"));
		//setActivated(false);
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
			g.drawImage(bodyImage, this.getX()-(bodyImage.getWidth(null)/2), this.getY(), null);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	@Override
	public void action(){
		App.stateManager.pop();
		App.stateManager.peek().unBlock();
		App.pauseFlag = false;
	}
}
