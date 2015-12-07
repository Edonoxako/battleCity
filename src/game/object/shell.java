package game.object;

import java.awt.Graphics2D;
import java.awt.Image;

import org.omg.CORBA.Current;

import core.App;
import core.managers.ObjectManager;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.ResourceLoader;

public class shell extends GameObject {
	private class Animator{
		private int animate_frame;
		private boolean init;
		private Image north, northNext, south, southNext, east, eastNext,
		west, westNext, current, currentInit, currentNext, northInit, southInit, eastInit, westInit;
		
		public Animator(){
			init = false;
			northInit = ResourceLoader.loadImage("object/shell_unactive_up.png");
			north = ResourceLoader.loadImage("object/shell_up1.png");
			northNext = ResourceLoader.loadImage("object/shell_up2.png");
			southInit = ResourceLoader.loadImage("object/shell_unactive_down.png");
			south = ResourceLoader.loadImage("object/shell_down_active1.png");
			southNext = ResourceLoader.loadImage("object/shell_down_active2.png");
			eastInit = ResourceLoader.loadImage("object/shell_unactive_right.png");
			east = ResourceLoader.loadImage("object/shell_right_active1.png");
			eastNext = ResourceLoader.loadImage("object/shell_right_active2.png");
			westInit = ResourceLoader.loadImage("/object/shell_unactive_left.png");
			west = ResourceLoader.loadImage("object/shell_left_active1.png");
			westNext = ResourceLoader.loadImage("object/shell_left_active2.png");
			animate_frame = 0;
			currentInit = northInit;
			current = north;
			currentNext = northNext;
		}
		
		public void setCourse(Course cs){
			switch (cs.getCourse()) {
				case 0: {
					currentInit = northInit;
					current = north;
					currentNext = northNext;
					break;
				}
				case 1: {
					currentInit = southInit;
					current = south;
					currentNext = southNext;
					break;
				}
				case 2: {
					currentInit = eastInit;
					current = east;
					currentNext = eastNext;
					break;
				}
				case 3: {
					currentInit = westInit;
					current = west;
					currentNext = westNext;
					break;
				}
				default: break;
			}
		}
		
		public Image nextState(){
			animate_frame++;
			if (init && animate_frame < 6){
				return currentInit;
			} else {
				animate_frame = 0;
				init = false;
			}
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
    private int frameCount;
    private int dx = 1;
    private int dy = 1;
    //private ObjectManager objectManager;

    //public MovingObject(int id, GameObjectCategory type, ObjectManager objectManager) {
    public shell(int id, GameObjectCategory type, int x, int y, Course cs) {
        super(id, type);
        frameCount = 0;
		anim = new Animator();
        setX(x);
        setY(y);
        Start(cs);
        //this.objectManager = objectManager;
		
    }
    
    @Override
    public void draw(Graphics2D g) {
    	
        if (frameCount > START_FRAME_COUNT) {
        	g.drawImage(body, x - body.getWidth(null)/2, 
    				y - body.getHeight(null)/2, null);
        }
    }
    private void Start(Course cs){
    	switch (cs.value){
	    	case 0: {
	    		course = cs;
	    		anim.setCourse(cs);
	    		dy = -dy;
	    		body = anim.nextState();
	    		break;
	    	}
			case 1: {
				course = cs;
	    		anim.setCourse(cs);
	    		body = anim.nextState();
				break;
			}
			case 2: {
				course = cs;
	    		anim.setCourse(cs);
	    		body = anim.nextState();
				break;
			}
			case 3: {
				course = cs;
	    		anim.setCourse(cs);
	    		dx = -dx;
	    		body = anim.nextState();
				break;
			}
    	}
    	
    }
    @Override
    public void update() {
        setX(getX() + dx);
        frameCount++;
        if (course.value <= 1){
        	setY(getY()+dy);
        }else{
        	setX(getX()+dx);
        }
        if (frameCount == 360) {
        	App.objectManager.removeObject(this.getId());
        	return;
//           frameCount=0;
//           dx = -dx;
//           if (dx > 0){
//				course = Course.East;
//				anim.setCourse(course);
//				body = anim.nextState();
//           } else {
//				course = Course.West;
//				anim.setCourse(course);
//				body = anim.nextState();
//           }
        }
        body = anim.nextState();
    }
}