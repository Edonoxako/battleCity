package game.state;

import java.util.ArrayList;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.Process;
import core.model.State;
import core.ui.ButtonList;
import core.utils.IdService;
import game.process.MainMenu;
import game.ui.button.ExitButton;
import game.ui.button.OptionsButton;
import game.ui.button.StartButton;
import game.ui.text.PauseAnimatedTitle;

public class MainState extends State{
	private Process mp;
	private ArrayList<GameObject> tObjectList;
	public MainState(){
		super();
		mp = new MainMenu("MainMenu", App.processManager.generateID());
	}
	
	public void init(){
		App.objectManager.removeAllObject();
		App.pauseBlockKey = true;
		App.processManager.addProc(mp);
		
		App.objectManager.addObject(new PauseAnimatedTitle(IdService.generateId(), Scene.getSize().width/2, 50));
		//App.objectManager.addObject(new PauseText(IdService.generateId(), Scene.getSize().width/2, Scene.getSize().height/2));
		ButtonList btList = new ButtonList(IdService.generateId(), GameObjectCategory.UI, App.input, Scene.getSize().width/2,
				(Scene.getSize().height/2)+50);
		btList.add(new StartButton(IdService.generateId()));
		btList.add(new OptionsButton(IdService.generateId()));
		btList.add(new ExitButton(IdService.generateId()));
		App.objectManager.addObject(btList);
		setInit(true);
		App.processManager.start(mp);
	}
	
	public void block(){
		tObjectList = new ArrayList<GameObject>(App.objectManager.getObjects());
		//App.objectManager.removeAllObject();
		App.processManager.stop(mp);
		App.pauseBlockKey = false;
	}
	
	public void unBlock(){
		App.objectManager.removeAllObject();
		for(GameObject o: tObjectList){
			App.objectManager.addObject(o);
		}
		App.pauseBlockKey = true;
		App.processManager.start(mp);
		
	}
	
	public synchronized void destroy(){
		
		App.processManager.kill(mp);
		App.pauseBlockKey = false;
	}
}
