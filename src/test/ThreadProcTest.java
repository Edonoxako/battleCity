package test;

import java.util.ArrayList;
import java.util.Random;

import core.graphics.Scene;
import core.managers.ObjectManager;
import core.model.GameObject;
import core.model.GameObjectType;
import core.utils.Time;

public class ThreadProcTest implements Runnable{
//	private static final int WIDTH = 600;
//	private static final int HEIGHT = 400;
//	private static final String TITLE = "Квадратишь";
//	private static final int CLEAR_COLOR = 0xffffffff;
//	private static final int NUM_BUFFERS = 2;
	private Thread gameThread;
	private static Random rands;
	private static int max = 1000;
	private int count = 0;
	//public static ArrayList<bools> arEn =  new ArrayList<bools>();
	//public static ArrayList<GameObject> arEn =  new ArrayList<>();
    private ObjectManager objectManager = new ObjectManager();
	public static final float UPDATE_RATE = 30.0f;
	public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
	public static final long IDLE_TIME = 1;
	
	public ThreadProcTest() {
		rands = new Random();
		/*for ( int i = 0; i < 1; i++) {
			arEn.add(new bools(rands.nextInt(Scene.content.getWidth()-50)+20, rands.nextInt(Scene.content.getHeight()-50)+20, i));
		}*/
		objectManager.addObject(new MovingObject(0, GameObjectType.ENEMY));
		gameThread = new Thread(this);
	}
	@Override
	public void run() {
		
		int fps = 0;
		int upd = 0;
		int updl = 0;
		
		long count = 0;
		
		float delta = 0; // количество опдейтов которое нужно сделать
		
		long lastTime = Time.get();// прошлое
		while(true) {
			long nowTime = Time.get(); // текущее
			long elapsedTime = nowTime - lastTime; // количество времени которое прошло
			lastTime = nowTime;
			
			count += elapsedTime;
			
			boolean render = false;
			delta += ( elapsedTime / UPDATE_INTERVAL );
			// возможно delta > 1
			while(delta > 1){
				for ( int i = 0; i < objectManager.getObjects().size(); i++) {
//					Thread t = new Thread(arEn[i]);
//					t.start();
                    if (objectManager.isObjectExists(i)){
                        objectManager.getObject(i).update();
                    }
					/*if ( arEn.get(i).death == false){
						arEn.get(i).update(arEn, max);
						if (arEn.get(i).death) {
							arEn.remove(i);
						}
					}*/
					
				}
//				arEn.trimToSize();
//				arEn.ensureCapacity(arEn.lastIndexOf(arEn));
				/*if (rands.nextInt(100) == 99) {
					arEn.add(new bools(rands.nextInt(Scene.content.getWidth()-50)+20, rands.nextInt(Scene.content.getHeight()-50)+20, arEn.get(arEn.size()-1).id+1));
				}*/
				delta--;
				upd++;
				if (render) {
					updl++;
				} else {
					render = true;
				}
			}
			if (render) {
				Scene.clear();
				for ( int i = 0; i < objectManager.getObjects().size(); i++) {
                    if (objectManager.isObjectExists(i)) {
                        objectManager.getObject(i).draw(Scene.getGraphics());
                    }
                    //arEn.get(i).draw(Scene.getGraphics());
					/*if ( arEn.get(i).death == false){
						arEn.get(i).draw(Scene.getGraphics());
					}*/
					
				}
				Scene.swapBuffers();
				fps++;
			} else {
				try {
					Thread.sleep(IDLE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (count >= Time.SECOND) {
				//tApp.window.setTitle
				//System.out.println(" || FPS: " + fps + " | UPD: " + upd + " | UPDL: " + updl + " | " + arEn.size()); //
				//DevInfo.update(fps, upd, updl, player.getX(), player.getY(), Scene.getWidth(), Scene.getHeight(), tileMap.getX(), tileMap.getY());
				//DevInfo.windowDialog.repaint();
				upd = 0;
				fps = 0;
				updl = 0;
				count = 0;
			}
			
		}
		
	}
//		while(true){
//			for ( int i = 0; i < max; i++) {
//				Thread t = new Thread(arEn[i]);
//				t.start();
//			}
//			//System.out.println("clear");
//			
//			Scene.clear();
//			for ( int i = 0; i < max; i++) {
//				((bools) arEn[i]).draw(Scene.getGraphics());
//			}
//			//System.out.println("draw");
//			//bl1.draw(Scene.getGraphics());
//			Scene.swapBuffers();
//		}
//	}
	public void start(){
		gameThread.start();
	}
}
