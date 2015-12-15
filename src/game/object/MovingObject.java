package game.object;

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
			north = ResourceLoader.loadImage("enemy/enemy_up1.png");
			northNext = ResourceLoader.loadImage("enemy/enemy_up2.png");
			south = ResourceLoader.loadImage("enemy/enemy_down1.png");
			southNext = ResourceLoader.loadImage("enemy/enemy_down2.png");
			east = ResourceLoader.loadImage("enemy/enemy_right1.png");
			eastNext = ResourceLoader.loadImage("enemy/enemy_right2.png");
			west = ResourceLoader.loadImage("enemy/enemy_left1.png");
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
    private int frameCount;
    private int dx = 1;
    //private ObjectManager objectManager;

    //public MovingObject(int id, GameObjectCategory type, ObjectManager objectManager) {
    public MovingObject(int id, GameObjectCategory type, ObjectManager objectManager) {
        super(id, type);
        frameCount = 0;

        setX(150);
        setY(110);
        
        course = Course.East;
		anim = new Animator();
		anim.setCourse(course);
		body = anim.current;
        
        //this.objectManager = objectManager;
    }

    @Override
    public void draw(Graphics2D g) {
        if (frameCount > START_FRAME_COUNT) {
        	g.drawImage(body, x - body.getWidth(null)/2, 
    				y - body.getHeight(null)/2, null);
        }
    }

    @Override
    public void update() {
        setX(getX() + dx);
        frameCount++;
        if (frameCount == 360) {
           frameCount=0;
           dx = -dx;
           if (dx > 0){
				course = Course.East;
				anim.setCourse(course);
				body = anim.nextState();
           } else {
				course = Course.West;
				anim.setCourse(course);
				body = anim.nextState();
           }
        }
        body = anim.nextState();
    }
}