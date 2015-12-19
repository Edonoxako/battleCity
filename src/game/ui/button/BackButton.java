package game.ui.button;

import java.awt.Graphics2D;

import core.App;
import core.ui.Button;
import core.utils.ResourceLoader;

public class BackButton extends Button{
	/**
	 *
	 * 
	 */
	//private GameState gm ;
	public BackButton(int id) {
		super(id, ResourceLoader.loadImage("UI/back_active.png"), ResourceLoader.loadImage("UI/back.png"));
		//setActivated(false);
		//gm  = new GameState();
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
		
		//App.stateManager.push(gm);
		App.pauseFlag = false;
	}
}
