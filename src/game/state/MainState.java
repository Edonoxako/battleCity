package game.state;

import java.util.ArrayList;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.Process;
import core.model.State;
import core.ui.ButtonList;
import game.process.MainMenu;
import game.ui.button.ExitButton;
import game.ui.button.OptionsButton;
import game.ui.button.StartButton;
import game.ui.text.PauseAnimatedTitle;
import game.ui.text.PauseText;

public class MainState extends State{
	private Process mp;
	private ArrayList<GameObject> tObjectList;
	public MainState(){
		super();
		mp = new MainMenu("MainMenu", App.processManager.generateID());
	}
	
	public void init(){
		App.processManager.addProc(mp);
		App.objectManager.removeAllObject();
		App.objectManager.addObject(new PauseAnimatedTitle(9091, Scene.getSize().width/2, 50));
		App.objectManager.addObject(new PauseText(9090, Scene.getSize().width/2, Scene.getSize().height/2));
		ButtonList btList = new ButtonList(9990, GameObjectCategory.UI, App.input, Scene.getSize().width/2,
				(Scene.getSize().height/2)+50);
		btList.add(new StartButton(1005));
		btList.add(new OptionsButton(1001));
		btList.add(new ExitButton(1003));
		App.objectManager.addObject(btList);
		setInit(true);
		App.processManager.start(mp);
	}
	
	public void destroy(){
		App.processManager.kill(mp);
		App.objectManager.removeAllObject();
		App.objectManager.getObjects().trimToSize();
	}
	
	public void block(){
		tObjectList = new ArrayList<GameObject>(App.objectManager.getObjects());
		App.processManager.stop(mp);
	}
	
	public void unBlock(){
		App.objectManager.removeAllObject();
		for(GameObject o: tObjectList){
			App.objectManager.addObject(o);
		}
		App.processManager.start(mp);
		
	}
}
