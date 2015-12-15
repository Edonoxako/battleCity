package game.state;

import java.util.ArrayList;
import java.util.LinkedList;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.Process;
import core.model.State;
import core.ui.ButtonList;
import game.process.Pause;
import game.ui.button.BackButton;
import game.ui.button.ExitButton;
import game.ui.button.MainButton;
import game.ui.button.OptionsButton;
import game.ui.text.PauseText;
import game.ui.text.PauseAnimatedTitle;

public class PauseState extends State{
	private Process ps;
	private LinkedList<GameObject> tObjectList;
	public PauseState(){
		super();
		ps = new Pause("PauseProcessTest", App.processManager.generateID());
	}
	
	public void init(){
		App.processManager.addProc(ps);
		App.objectManager.removeAllObject();
		App.objectManager.addObject(new PauseAnimatedTitle(9091, Scene.getSize().width/2, 50));
		App.objectManager.addObject(new PauseText(9090, Scene.getSize().width/2, Scene.getSize().height/2));
		ButtonList btList = new ButtonList(9990, GameObjectCategory.UI, App.input, Scene.getSize().width/2,
				(Scene.getSize().height/2)+50);
		btList.add(new BackButton(1005));
		//btList.add(new MainButton(1006));
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
