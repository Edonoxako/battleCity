package game;

import core.App;
import game.state.GameState;

public class GameMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.init();
		
		//add test object
		GameState gm = new GameState();
		App.stateManager.push(gm);
		App.window.setTitle("Battlefield city");
        //-----------------------
		app.start();
	}

}
