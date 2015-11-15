package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import core.graphics.Scene;

public class bools implements Runnable{
	//двигается в рандомную сторону пока не встертит припятсвие;
	private int x;
	private int y;
	private int dx = 1;
	private int dy = 1;
	private Random rand = new Random();
	private Color color;
	private boolean first;
	private int maxspeed = 10;
	public int mas;
	public int id;
	public boolean death = false;
	public bools(int x, int y, int id){
		setX(x);
		setY(y);
		this.id = id;
		 first = true;
		 mas = rand.nextInt(7)+5;
		 int red = (int) (( Math.random()*245)+1);
		 int green = (int) (( Math.random()*245)+1);
		 int blue = (int) (( Math.random()*245)+1);
		 color = new Color(red,green,blue);
		 int RandomRGB = (color.getRGB());
		 String RGBtoHEX = Integer.toHexString(RandomRGB);
		 color = new Color(RGBtoHEX.hashCode());
	}
	public void update(ArrayList<bools> trEn, int max) {
		if(first){
			dx+=rand.nextInt(maxspeed/2) +1;
			dy+=rand.nextInt(maxspeed/2) +1;
			int nav = rand.nextInt(10);
			if ( nav == 6) {
				dx = dx*(-1);
				dy = dy*(-1);
			}else if (nav > 7) {
				dx = dx*(-1);
			}else if ( nav < 2) {
				dy = dy*(-1);
			}
			first = false;
		}else{
			int t;
			for ( int i = 0; i < trEn.size(); i++){
				bools temp = trEn.get(i);
				t = temp.id;
				if( this.id!= t) check(trEn.get(i));
			}
			
		}
		if(!death){
			x += dx;
			y += dy;
			if (x < mas/2){
				x = mas/2;
				dx = rand.nextInt(maxspeed)+1;
				
			}
			
			if (x > Scene.content.getWidth()-mas/2) {
				x = Scene.content.getWidth()-mas/2;
				dx = -(rand.nextInt(maxspeed)+1);
			}
			
			if ( y < mas/2 ) {
				y = mas/2;
				dy = rand.nextInt(maxspeed)+1;
			}
		
			if (y > Scene.content.getHeight()-mas/2) {
				y = Scene.content.getHeight()-mas/2;
				dy = -(rand.nextInt(maxspeed)+1);
			}
		}
		//System.out.println("x: "+this.x+" y: "+this.y);
	}
	public void draw(Graphics2D g) {
		g.setColor(color);
//		g.drawLine(
//		(int)( x ),
//		(int)( y ),
//		(int)( x ),
//		(int)( y ));
		g.fillRect(
				(int)(x - mas / 2),
				(int)(y - mas / 2),
				mas,
				mas);
	}

	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	@Override
	public void run() {
		//update();
		
	}
	
	public void check(bools two) {
		if((death==false)&&(two.death==false)){
			if ( Math.sqrt(Math.pow((this.x-two.x), 2)
					+Math.pow((this.y-this.mas/2-two.y), 2))-this.mas/2-two.mas/2 < 1){
//				if (Math.abs((Math.abs(this.dx)+Math.abs(this.dy))-(Math.abs(two.dx)+Math.abs(two.dy))) > 0 && this.mas > two.mas){
//					this.mas = this.mas + two.mas;
//					two.death = true;
//				}
//				else{
//					
//				}
				if ((Math.abs(this.dx)+Math.abs(this.dy))>(Math.abs(two.dx)+Math.abs(two.dy))){
					if (this.mas > two.mas) {
							this.mas = this.mas + two.mas;
							two.death = true;
					}else{
							this.death = true;
							two.mas = two.mas/2;
					}
				}else{
					if (this.mas > two.mas) {
							this.mas = this.mas/2;
							two.death = true;
					}else{
							this.death = true;
							two.mas = two.mas + this.mas;
					}
					if (this.mas == two.mas) {
						int a = rand.nextInt(6) + 1;
						int b = rand.nextInt(6) + 1;
						if ( a > b) {
							this.mas =+ 10;
						}else{
							this.mas =- 10;
						}
					}
					if (this.mas > 30) this.mas = 30;
					if (two.mas > 30) two.mas = 30;
					if ( this.mas < 4) this.mas = 4;
					if ( two.mas < 4) two.mas = 4;
				}
				
			}
		}
	}

}
