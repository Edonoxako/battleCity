package game;

import core.App;
import game.state.MainState;

public class GameMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.init();
		
		//add test object
		//GameState gm = new GameState();
		MainState ms = new MainState();
		App.stateManager.push(ms);
		App.window.setTitle("Battlefield city");
        //-----------------------
		app.start();
	}

}
