package game.object;

import java.awt.Graphics2D;
import java.awt.Image;

import core.App;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.IdService;
import core.utils.ResourceLoader;

public class SplashEfect extends GameObject{
	private class Animator{
		private int animate_frame;
		private Image expMin, expMid, expBig, expMax, current;
		private Image[] frames = new Image[8];
		private int frames_counter;
		
		public Animator(){
			expMin = ResourceLoader.loadImage("blocks/explose_min.png");
			expMid = ResourceLoader.loadImage("blocks/explose_middle.png");
			expBig = ResourceLoader.loadImage("blocks/explose_big.png");
			expMax = ResourceLoader.loadImage("blocks/explose_max.png");
			frames[0] = expMin;
			frames[1] = expMid;
			frames[2] = expBig;
			frames[3] = expMax;
			frames[4] = expBig;
			frames[5] = expMax;
			frames[6] = expBig;
			frames[7] = expMax;
			animate_frame = 0;
			frames_counter = 0;

		}
		
		public Image nextState(){
			animate_frame++;
			current = frames[frames_counter];
			if(animate_frame > 1){
				animate_frame = 0;
				frames_counter++;
			}
			if(frames_counter > 7) return null;
			else return current;
			
		}
	}
	private Animator anim;
	private Image body;
	public SplashEfect(int x, int y, int dmx, int dmy) {
		super(IdService.generateId(), GameObjectCategory.Environment);
		anim = new Animator();
		setX(x);
		setY(y);
		this.dmx = dmx;
		this.dmy = dmy;
		body = anim.nextState();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(body, dmx + getX() - (int)body.getWidth(null)/2,
				dmy + getY() - (int)body.getHeight(null)/2, null);
		
	}

	@Override
	public void update() {
		body = anim.nextState();
		if (body == null){
			App.objectManager.removeObject(this.getId());
		}
	}

}
