package game.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.Input;
import core.utils.ResourceLoader;

public class TestPlayer extends GameObject{
	private class Animator{
		private int animate_frame;
		private Image north, northNext, south, southNext, east, eastNext,
		west, westNext, current, currentNext;
		
		public Animator(){
			north = ResourceLoader.loadImage("player/tankup1.png");
			northNext = ResourceLoader.loadImage("player/tankup2.png");
			south = ResourceLoader.loadImage("player/tankdown1.png");
			southNext = ResourceLoader.loadImage("player/tankdown2.png");
			east = ResourceLoader.loadImage("player/tankright1.png");
			eastNext = ResourceLoader.loadImage("player/tankright2.png");
			west = ResourceLoader.loadImage("player/tankleft1.png");
			westNext = ResourceLoader.loadImage("player/tankleft2.png");
			animate_frame = 0;
			current = north;
			currentNext = northNext;
		}
		
		public void setCourse(Course cs){
			switch (cs.getCourse()) {
				case 0: {
					current = north;
					currentNext = northNext;
					break;
				}
				case 1: {
					current = south;
					currentNext = southNext;
					break;
				}
				case 2: {
					current = east;
					currentNext = eastNext;
					break;
				}
				case 3: {
					current = west;
					currentNext = westNext;
					break;
				}
				default: break;
			}
		}
		
		public Image nextState(){
			animate_frame++;
			if (animate_frame % 5 != 0){
				animate_frame = 0;
				Image tmp = current;
				current = currentNext;
				currentNext = tmp;
			}
			return current;
		}
	}
	//private boolean North, South, East, West; //w, s, d, a
	public enum Course{
		North(0), //w
		South(1), //s
		East(2), //d
		West(3); //a
		
		private int value;
		
		Course(int value){
			this.value = value;
		}
		
		public int getCourse(){
			return value;
		}
		
		public static Course toCourse(int value) {
			switch (value) {
				case 0: return North;
				case 1: return South;
				case 2: return East;
				case 3: return West;
			}
			return null;
		}
	}
	
	private Input in;
	private double dx, dy;
	private int h = 48;
	private Course course;
	private Image body;
	private Animator anim;
	private int count_push;
	private boolean tank_fire;
	public TestPlayer(int id, int x, int y, Input inp, GameObjectCategory type){
		//adadad
		super(id, type);
		this.x = x;
		this.y = y;
		count_push = 0;
		in = inp;
		course = Course.North;
		anim = new Animator();
		anim.setCourse(course);
		body = anim.current;
		tank_fire = false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(body, dmx + x - body.getWidth(null)/2, 
				dmy + y - body.getHeight(null)/2, null);
	}

	@Override
	public void update() {
		int width = Scene.content.getSize().width;
		int height = Scene.content.getSize().height;
		int sceneWidth = Scene.getSize().width;
		int sceneHeight = Scene.getSize().height;
		int mapWidth = App.objectManager.getMapWidth();
		int mapHeight = App.objectManager.getMapHeight();
		if (count_push == 30){
			count_push = 0;
			tank_fire = false;
		}
		if(in.getKey(KeyEvent.VK_A)){
			if(course.getCourse() != Course.West.getCourse()){
				course = Course.West;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dx = -1f;
			body = anim.nextState();
		}else if(in.getKey(KeyEvent.VK_W)){
			if(course.getCourse() != Course.North.getCourse()){
				course = Course.North;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dy = -1f;
			body = anim.nextState();
		}else if(in.getKey(KeyEvent.VK_D)){
			if(course.getCourse() != Course.East.getCourse()){
				course = Course.East;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dx = 1f;
			body = anim.nextState();
		}else if(in.getKey(KeyEvent.VK_S)){
			if(course.getCourse() != Course.South.getCourse()){
				course = Course.South;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dy = 1f;
			body = anim.nextState();
		}
		if(!collision(x + dx, y + dy)){
			if(x+dx>=mapWidth -h/2){
				x = mapWidth -h/2;
			}else if(x+dx<=h/2){
				x = h/2;
			}else x +=dx;
			dx = 0;
			if(y+dy>=mapHeight -h/2){
				y = mapHeight -h/2;
			}else if(y+dy<=h/2){
				y = h/2;
			}else y +=dy;
			dy = 0;
		}else{
			dx = 0;
			dy = 0;
		}
		
		
		if( x < sceneWidth / 2) {
			App.objectManager.moveObjectX(0);
		}else if( x > mapWidth - sceneWidth / 2 ){
			App.objectManager.moveObjectX((int)((sceneWidth / 2) - (mapWidth - sceneWidth / 2)));
		}else {
			App.objectManager.moveObjectX((int)(sceneWidth /2 - x));
		}
		if( y < sceneHeight / 2) {
			App.objectManager.moveObjectY(0);
		}else if( y > mapHeight - sceneHeight / 2){
			App.objectManager.moveObjectY((int)((sceneHeight / 2) - (mapHeight - sceneHeight / 2)));
		}else {
			App.objectManager.moveObjectY((int)(sceneHeight /2 - y));
		}
		
		if(in.getKey(KeyEvent.VK_SPACE)){
			if (count_push == 0){
					App.objectManager.addObject(new shell(800, GameObjectCategory.Entity,
							x, y, course.value));

					tank_fire = true;
			}
		}
		if(tank_fire){
			count_push++;
		}
		
	}
	
	public boolean collision(double x, double y){
			
		return App.objectManager.checkObject((int)(x)/48, (int)(y)/48);
	}
	
}
