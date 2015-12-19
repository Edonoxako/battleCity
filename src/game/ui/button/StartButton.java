package game.ui.button;

import java.awt.Graphics2D;

import core.App;
import core.ui.Button;
import core.utils.ResourceLoader;
import game.state.GameState;

public class StartButton extends Button{
	/**
	 *
	 * 
	 */
	public StartButton(int id) {
		super(id, ResourceLoader.loadImage("UI/start1_active.png"), ResourceLoader.loadImage("UI/start1.png"));
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
		GameState gs = new GameState();
		App.stateManager.peek().block();
		App.stateManager.pop();
		App.stateManager.push(gs);
	}
}