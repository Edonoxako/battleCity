package test;

import java.util.ArrayList;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.Process;
import core.model.State;
import core.ui.ButtonList;

public class TestStatePause extends State{
	private Process ps;
	private ArrayList<GameObject> tObjectList;
	public TestStatePause(){
		super();
		ps = new Pause("PauseProcessTest", App.processManager.generateID());
		System.out.println(ps.getId());
	}
	
	public void init(){
		App.processManager.addProc(ps);
		App.objectManager.removeAllObject();
		App.objectManager.addObject(new TestTitle(9091, Scene.getSize().width/2, 50));
		App.objectManager.addObject(new TestText(9090, Scene.getSize().width/2, Scene.getSize().height/2));
		ButtonList btList = new ButtonList(9990, GameObjectCategory.UI, App.input, Scene.getSize().width/2,
				(Scene.getSize().height/2)+50);
		btList.add(new BackButton(1005));
		btList.add(new MainButton(1006));
		btList.add(new OptionsButton(1001));
		btList.add(new ExitButton(1003));
		App.objectManager.addObject(btList);
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
