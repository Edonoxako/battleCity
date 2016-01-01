package game.ui.button;

import java.awt.Graphics2D;

import core.model.GameObject;
import core.ui.Button;
import core.utils.ResourceLoader;

public class ExitButton extends Button{

	public ExitButton(int id) {
		super(id, ResourceLoader.loadImage("UI/exit_active.png"), ResourceLoader.loadImage("UI/exit.png"));
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
	@Override
	public void collision(int x, int y, GameObject obj) {
		// TODO Auto-generated method stub
		
	}

}
