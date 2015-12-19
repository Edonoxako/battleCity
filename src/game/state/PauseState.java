package game.state;

import java.util.LinkedList;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.Process;
import core.model.State;
import core.ui.ButtonList;
import core.utils.IdService;
import game.process.Pause;
import game.ui.button.BackButton;
import game.ui.button.ExitButton;
import game.ui.button.MainButton;
import game.ui.button.OptionsButton;
import game.ui.text.PauseAnimatedTitle;
import game.ui.text.PauseText;

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
		App.objectManager.addObject(new PauseAnimatedTitle(IdService.generateId(), Scene.getSize().width/2, 50));
		App.objectManager.addObject(new PauseText(IdService.generateId(), Scene.getSize().width/2, Scene.getSize().height/2));
		ButtonList btList = new ButtonList(IdService.generateId(), GameObjectCategory.UI, App.input, Scene.getSize().width/2,
				(Scene.getSize().height/2)+50);
		btList.add(new BackButton(IdService.generateId()));
		btList.add(new MainButton(IdService.generateId()));
		btList.add(new OptionsButton(IdService.generateId()));
		btList.add(new ExitButton(IdService.generateId()));
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
