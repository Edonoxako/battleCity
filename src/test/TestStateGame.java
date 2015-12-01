package test;

import java.util.ArrayList;

import core.App;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;
import core.model.GameObject;
import core.model.State;
import core.utils.IdService;

public class TestStateGame extends State{
	private Game gm;
	private ArrayList<GameObject> tObjectList;
	public TestStateGame(){
		super();
		gm = new Game("GameProcessTest", App.processManager.generateID());
		System.out.println(gm.getId());
		App.processManager.addProc(gm);
	}
	
	public void init(){

		TileMap map = ResourceLoader.loadMap("res/testmap.txt");
		App.objectManager.createMap(map);

		App.objectManager.addObject(new TestPlayer(IdService.generateId(), 50, 50, App.input, GameObjectCategory.Entity));
		App.objectManager.addObject(new MovingObject(IdService.generateId(), GameObjectCategory.Entity, App.objectManager));
		App.processManager.start(gm);

		setInit(true);
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
