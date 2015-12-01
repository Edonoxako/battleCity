package test;

import java.awt.Graphics2D;

import core.ui.Button;
import core.utils.Resour�eLoader;

public class ExitButton extends Button{

	public ExitButton(int id) {
		super(id, Resour�eLoader.loadImage("UI/exit_active.png"), Resour�eLoader.loadImage("UI/exit.png"));
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
		System.exit(0);
	}

}
