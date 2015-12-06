package game.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import core.managers.ObjectManager;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

/**
 * Created by Евгений on 10.11.2015.
 *
 * Просто движущийся квадрат
 */
public class MovingObject extends GameObject {
	private class Animator{
		private int animate_frame;
		private Image north, northNext, south, southNext, east, eastNext,
		west, westNext, current, currentNext;
		
		public Animator(){
			north = ResourceLoader.loadImage("enemy/enemy_up.png");
			northNext = ResourceLoader.loadImage("enemy/enemy_up2.png");
			south = ResourceLoader.loadImage("enemy/enemy_down.png");
			southNext = ResourceLoader.loadImage("enemy/enemy_down2.png");
			east = ResourceLoader.loadImage("enemy/enemy_right.png");
			eastNext = ResourceLoader.loadImage("enemy/enemy_right2.png");
			west = ResourceLoader.loadImage("enemy/enemy_left.png");
			westNext = ResourceLoader.loadImage("enemy/enemy_left2.png");
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

    public static final int START_FRAME_COUNT = 40;
    public static final int END_FRAME_COUNT = 100;
    public static final int VELOCITY = 6;
    private Course course;
	private Image body;
	private Animator anim;
    private Color color;
    private int frameCount;
    private int dx = 1;
    //private ObjectManager objectManager;

    //public MovingObject(int id, GameObjectCategory type, ObjectManager objectManager) {
    public MovingObject(int id, GameObjectCategory type, ObjectManager objectManager) {
        super(id, type);
        frameCount = 0;

        setX(10);
        setY(10);

        this.color = new Color(100, 200, 128);
        //this.objectManager = objectManager;
    }

    @Override
    public void draw(Graphics2D g) {
        if (frameCount > START_FRAME_COUNT) {
            g.setColor(color);
            g.fillRect(getX(), getY(), 20, 20);
        }
    }

    @Override
    public void update() {
        setX(getX() + dx);

        frameCount++;
        if (frameCount == 360) {
           frameCount=0;
           dx = -dx;
        }
    }
}
/*package game.object;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

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
	public TestPlayer(int id, int x, int y, Input inp, GameObjectCategory type){
		//adadad
		super(id, type);
		this.x = x;
		this.y = y;
		in = inp;
		course = Course.North;
		anim = new Animator();
		anim.setCourse(course);
		body = anim.current;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(body, x - body.getWidth(null)/2, 
				y - body.getHeight(null)/2, null);
	}

	@Override
	public void update() {
		int width = Scene.content.getSize().width;
		int height = Scene.content.getSize().height;
		if(in.getKey(KeyEvent.VK_A)){
			if(course.getCourse() != Course.West.getCourse()){
				course = Course.West;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dx = -2f;
		}else if(in.getKey(KeyEvent.VK_W)){
			if(course.getCourse() != Course.North.getCourse()){
				course = Course.North;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dy = -2f;
		}else if(in.getKey(KeyEvent.VK_D)){
			if(course.getCourse() != Course.East.getCourse()){
				course = Course.East;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dx = 2f;
		}else if(in.getKey(KeyEvent.VK_S)){
			if(course.getCourse() != Course.South.getCourse()){
				course = Course.South;
				anim.setCourse(course);
				body = anim.nextState();
			}
			dy = 2f;
		}
		
		if(x+dx>=width -h/2){
			x = width -h/2;
		}else if(x+dx<=h/2){
			x = h/2;
		}else x +=dx;
		dx = 0;
		if(y+dy>=height -h/2){
			y = height -h/2;
		}else if(y+dy<=h/2){
			y = h/2;
		}else y +=dy;
		dy = 0;
		
		body = anim.nextState();
	}
	
}
*/