package core.ui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.Input;

public class ButtonList extends GameObject{
	private ArrayList<Button> buttonList;
	private int delay, delayCounter;
	private int key;
	private int spacing;
	private boolean active;
	private boolean push;
	private Input input;
	private Point position;
	public ButtonList(int id, GameObjectCategory type, Input in, int x, int y) {
		super(id, type);
		setPosition(new Point(x,y));
		active = false;
		push = true;
		input = in;
		delay = 10;
		delayCounter = 0;
		spacing = 10;
		key = 0;
		buttonList = new ArrayList<Button>();
		// TODO Auto-generated constructor stub
	}
	public void setSpacing(int spacing){
		this.spacing = spacing;
	}

	@Override
	public void draw(Graphics2D g) {
		if(!buttonList.isEmpty()){
			Iterator<Button> iterator = buttonList.iterator();
			while (iterator.hasNext()) {
				iterator.next().draw(g);
			}
		}
		
	}

	@Override
	public void update() {
		if(!input.getKey(KeyEvent.VK_ENTER) &&
				!input.getKey(KeyEvent.VK_SPACE)){
			push = false;
		}
		if(input.getKey(KeyEvent.VK_ENTER) ||
				input.getKey(KeyEvent.VK_SPACE)){
			if(!push) {
				buttonList.get(key).action();
				push = true;
			}
		}
		
		if(active){
			if(delay == delayCounter){
				delayCounter = 0;
				active = false;
			}
			delayCounter +=1;
		}else{
			int t_key = key;
			if(input.getKey(KeyEvent.VK_W) ||
					input.getKey(KeyEvent.VK_UP) ||
					input.getKey(KeyEvent.VK_KP_UP)){
				key-=1;
				active = true;
				if(key<0){
					key = buttonList.size()-1;
				}
			}
			if(input.getKey(KeyEvent.VK_S) ||
					input.getKey(KeyEvent.VK_DOWN) ||
					input.getKey(KeyEvent.VK_KP_DOWN)){
				key+=1;
				active = true;
				if(key>buttonList.size()-1){
					key = 0;
				}
			}
			if(t_key!=key){
				Iterator<Button> iterator = buttonList.iterator();
				while (iterator.hasNext()) {
					iterator.next().setActivated(false);
				}
				buttonList.get(key).setActivated(true);
			}
		}
		
	}
	public void add(Button button){
		if(!buttonList.contains(button)){
			if(buttonList.isEmpty()){
				buttonList.add(button);
				button.setY(position.y+spacing);
				button.setX(position.x);
				System.out.println("First Button id" +button.getId() +": "+button.getY());
				button.setActivated(true);
			}else{
				buttonList.trimToSize();
				button.setY(buttonList.get(buttonList.size()-1).getY()+button.bodyImage.getHeight(null)+spacing);
				System.out.println("Button id" +button.getId() +": "+button.getY());
				button.setX(position.x);
				buttonList.add(button);
			}
			buttonList.trimToSize();
		}
	}
	public void remove(int id){
		if(!buttonList.isEmpty()){
			Iterator<Button> iterator = buttonList.iterator();
			while (iterator.hasNext()) {
				if(iterator.next().getId()== id){
					buttonList.remove(iterator);
				}
			}
		}
	}
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public void setPosition(int x, int y) {
		this.position = new Point(x, y);
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	@Override
	public void collision(int x, int y, GameObject obj) {
		// TODO Auto-generated method stub
		
	}

}
