package game.state;

import java.util.ArrayList;

import core.App;
import core.graphics.Scene;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;
import game.object.MovingObject;
import game.object.TestPlayer;
import game.process.Game;
import game.ui.frame.UiFrame;
import test.TileMap;
import core.model.GameObject;
import core.model.State;
import core.utils.IdService;

public class GameState extends State{
	private Game gm;
	private ArrayList<GameObject> tObjectList;
	private TileMap map;
	public GameState(){
		super();
		map = ResourceLoader.loadMap("res/testmap.txt");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(){
		
		if(!App.objectManager.getObjects().isEmpty()){
			App.objectManager.removeAllObject();
		}
		
		App.objectManager.addObject(new MovingObject(IdService.generateId(), GameObjectCategory.Entity, App.objectManager));
		App.objectManager.createMap(map);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UiFrame frame = new UiFrame(Scene.getSize().width, Scene.getSize().height, 100);
		App.objectManager.addObject(frame);
		App.objectManager.addObject(new TestPlayer(IdService.generateId(), 150, 150, App.input, frame, GameObjectCategory.Entity));
		gm = new Game("GameProcessTest", IdService.generateId());
		App.processManager.addProc(gm);
		setInit(true);
		App.processManager.start(gm);
		
		
	}
	public void block(){
		tObjectList = new ArrayList<GameObject>(App.objectManager.getObjects());
		App.processManager.stop(gm);
	}
	
	public void unBlock(){
		App.objectManager.removeAllObject();
		for(GameObject o: tObjectList){
			App.objectManager.addObject(o);
		}
		App.processManager.start(gm);
	}
	
	public void destroy(){
		App.processManager.kill(gm);
		App.objectManager.removeAllObject();
	}
}
