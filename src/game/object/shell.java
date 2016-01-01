package game.object;

import java.awt.Graphics2D;
import java.awt.Image;

import core.App;
import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.GameObjectType;
import core.utils.ResourceLoader;

public class shell extends GameObject {
	private class Animator{
		private int animate_frame;
		private boolean init;
		private Image north, northNext, south, southNext, east, eastNext,
		west, westNext, current, currentInit, currentNext, northInit, southInit, eastInit, westInit;
		
		public Animator(){
			init = true;
			northInit = ResourceLoader.loadImage("object/shell_unactive_up.png");
			north = ResourceLoader.loadImage("object/shell_up_active1.png");
			northNext = ResourceLoader.loadImage("object/shell_up_active2.png");
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
			if(init){
				if(animate_frame > 60){
					animate_frame = 0;
					init = false;
					return current;
				}else return currentInit;
			}
			if (animate_frame / 15 != 0){
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
    private int dx = 4;
    private int dy = 4;
    private int h = 48;
    private int parentId;
    //private ObjectManager objectManager;

    //public MovingObject(int id, GameObjectCategory type, ObjectManager objectManager) {
    public shell(int id, int parentId, GameObjectCategory type, int x, int y, int dmx, int dmy, int cs) {
        super(id, type, GameObjectType.SHELL);
        frameCount = 0;
		anim = new Animator();
        setX(x);
        setY(y);
        this.parentId = parentId;
        this.dmx = dmx;
        this.dmy = dmy;
        Start(Course.toCourse(cs));
        //this.objectManager = objectManager;
		
    }
    
    @Override
    public void draw(Graphics2D g) {

		g.drawImage(body, dmx + getX() - (int)body.getWidth(null)/2,
				dmy + getY() - (int)body.getHeight(null)/2, null);

    }
    private void Start(Course cs){
    	switch (cs.value){
	    	case 0: {
	    		course = cs;
	    		anim.setCourse(cs);
	    		dy = -dy;
	    		setY(getY()-24);
	    		body = anim.nextState();
	    		break;
	    	}
			case 1: {
				course = cs;
	    		anim.setCourse(cs);
	    		setY(getY()+24);
	    		body = anim.nextState();
				break;
			}
			case 2: {
				course = cs;
	    		anim.setCourse(cs);
	    		setX(getX()+24);
	    		body = anim.nextState();
				break;
			}
			case 3: {
				course = cs;
	    		anim.setCourse(cs);
	    		dx = -dx;
	    		setX(getX()-24);
	    		body = anim.nextState();
				break;
			}
    	}
    	
    }
    @Override
    public void update() {
    	//System.out.println("shlee");
        frameCount++;
        if(!collision(x + dx, y + dy)){
        	if (course.value <= 1){
        		if(getY()+dy<App.objectManager.getMapHeight()&&getY()+dy>0)
        		setY(getY()+dy);
            }else{
            	if(getX()+dx<App.objectManager.getMapWidth()&&getX()+dx>0)
            	setX(getX()+dx);
            }
		}else{
			App.objectManager.removeObject(this.getId(), this.getCategory());
			App.objectManager.addObject(new SplashEfect(x, y, dmx, dmy));
			
		}
        
        if (frameCount == 100) {
        	App.objectManager.removeObject(this.getId(), this.getCategory());
        	App.objectManager.addObject(new SplashEfect(x, y, dmx, dmy));
        	return;
        }
        body = anim.nextState();
        
    }
    public boolean collision(double x, double y){
		
		return App.objectManager.checkObject((int)(x)/48, (int)(y)/48, getId(), parentId);
	}

	@Override
	public void collision(int x, int y, GameObject obj) {
		// TODO Auto-generated method stub
		
	}
}