package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.Input;
import core.utils.ResourceLoader;
import game.ui.button.OptionsButton;

public class TestButtonContainer extends GameObject{
	private int key = 0;
	private ArrayList<OptionsButton> menuList;
	private int delay = 10;
	private int delay_check = 0;
	private Image title;
	private boolean active;
	private OptionsButton bt1;
	private OptionsButton bt2;
	private OptionsButton bt3;
	private Input input;
	private double x, nx;
	private double y, ny;
	private double dx = 0.05;
	private double dy = 0.03;
	public TestButtonContainer(int id, GameObjectCategory type, Input in) {
		super(id, type);
		title = ResourceLoader.loadImage("UI/logo_sample.png");
		int dY = 50;
		this.input = in;
		menuList = new ArrayList<OptionsButton>();
		bt1 = new OptionsButton(1000);
		bt1.setY((Scene.getSize().height/2)+dY);
		bt1.setActivated(true);
		menuList.add(bt1);
		bt2 = new OptionsButton(1001);
		bt2.setY(bt1.getY()+dY);
		bt2.setActivated(false);
		menuList.add(bt2);
		bt3 = new OptionsButton(1002);
		bt3.setActivated(false);
		menuList.add(bt3);
		bt3.setY(bt2.getY()+dY);
		menuList.trimToSize();
		x = (Scene.getSize().width/2)-(title.getWidth(null)/2);
		y = 50;
		nx = x;
		ny = y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		//nx = (Scene.getSize().width/2)-(title.getWidth(null)/2);
		g.drawImage(title, (int)nx, (int)ny, null);
		Iterator<OptionsButton> iterator = menuList.iterator();
		while (iterator.hasNext()) {
			iterator.next().draw(g);
		}
		
	}

	@Override
	public void update() {
		animateTitle();
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
				Iterator<OptionsButton> iterator = menuList.iterator();
				while (iterator.hasNext()) {
					iterator.next().setActivated(false);
				}
				menuList.get(key).setActivated(true);
			}
		}
	}
	public void animateTitle(){
		x = (Scene.getSize().width/2)-(title.getWidth(null)/2);
		if(x - nx >= 0){
			if(x - nx > 5){
				dx = -dx;
			}
			nx +=dx;
		}else{
			if(x - nx < -5){
				dx = -dx;
			}
			nx +=dx;
		}
		
		if(y - ny >= 0){
			if(y - ny > 5){
				dy = -dy;
			}
			ny +=dy;
		}else{
			if(y - ny < -5){
				dy = -dy;
			}
			ny +=dy;
		}
	}

	@Override
	public void collision(int x, int y, GameObject obj) {
		// TODO Auto-generated method stub
		
	}
}
