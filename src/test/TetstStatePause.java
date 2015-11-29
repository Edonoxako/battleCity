package test;

import java.util.ArrayList;

import core.App;
import core.model.GameObject;
import core.model.GameObjectType;
import core.model.Process;
import core.model.State;

public class TetstStatePause extends State{
	private Process ps;
	private ArrayList<GameObject> tObjectList;
	public TetstStatePause(){
		super();
		ps = new Pause("PauseProcessTest", App.processManager.generateID());
		System.out.println(ps.getId());
		
	}
	
	public void init(){
		App.processManager.addProc(ps);
		App.objectManager.removeAllObject();
		App.objectManager.addObject(new PauseObjectText(0, GameObjectType.UI, App.objectManager));
		setInit(true);
		App.processManager.start(ps);
	}
	
	public void destroy(){
		App.processManager.kill(ps);
		App.objectManager.removeAllObject();
	}
	
	public void block(){
		App.objectManager.setObjects(tObjectList);
		App.processManager.start(ps);
	}
	
	public void unBlock(){
		App.processManager.kill(ps);
		App.objectManager.removeAllObject();
	}
}
