package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectType;
import core.utils.Input;
import core.utils.ResourñeLoader;

public class TestButtonContainer extends GameObject{
	private int key = 0;
	private ArrayList<TestButton> menuList;
	private int delay = 10;
	private int delay_check = 0;
	private Image title;
	private boolean active;
	private TestButton bt1;
	private TestButton bt2;
	private TestButton bt3;
	private Input input;
	public TestButtonContainer(int id, GameObjectType type, Input in) {
		super(id, type);
		title = ResourñeLoader.loadImage("UI/logo_sample.png");
		int dY = 50;
		this.input = in;
		menuList = new ArrayList<TestButton>();
		bt1 = new TestButton(1000);
		bt1.setY((Scene.getSize().height/2)+dY);
		bt1.setActive(true);
		menuList.add(bt1);
		bt2 = new TestButton(1001);
		bt2.setY(bt1.getY()+dY);
		bt2.setActive(false);
		menuList.add(bt2);
		bt3 = new TestButton(1002);
		bt3.setActive(false);
		menuList.add(bt3);
		bt3.setY(bt2.getY()+dY);
		menuList.trimToSize();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(title, (Scene.getSize().width/2)-(title.getWidth(null)/2), 0, null);
		Iterator<TestButton> iterator = menuList.iterator();
		while (iterator.hasNext()) {
			iterator.next().draw(g);
		}
		
	}

	@Override
	public void update() {
		if(input.getKey(KeyEvent.VK_ENTER) ||
				input.getKey(KeyEvent.VK_SPACE)	){
			menuList.get(key).action();
		}
		if(active){
			if(delay == delay_check){
				delay_check = 0;
				active = false;
			}
			delay_check +=1;
		}else{
			int t_key = key;
			if(input.getKey(KeyEvent.VK_W) ||
					input.getKey(KeyEvent.VK_UP) ||
					input.getKey(KeyEvent.VK_KP_UP)){
				key-=1;
				active = true;
				if(key<0){
					key = menuList.size()-1;
				}
			}
			if(input.getKey(KeyEvent.VK_S) ||
					input.getKey(KeyEvent.VK_DOWN) ||
					input.getKey(KeyEvent.VK_KP_DOWN)){
				key+=1;
				active = true;
				if(key>menuList.size()-1){
					key = 0;
				}
			}
			if(t_key!=key){
				Iterator<TestButton> iterator = menuList.iterator();
				while (iterator.hasNext()) {
					iterator.next().setActive(false);
				}
				menuList.get(key).setActive(true);
			}
		}
	}
	
}
