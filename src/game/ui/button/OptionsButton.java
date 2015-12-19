package game.ui.button;

import java.awt.Graphics2D;

import core.ui.Button;
import core.utils.ResourceLoader;

public class OptionsButton extends Button{

	/**
	 *
	 * 
	 */
	public OptionsButton(int id) {
		super(id, ResourceLoader.loadImage("UI/options_active.png"), ResourceLoader.loadImage("UI/options.png"));
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
		System.out.println("���������");
	}
}
