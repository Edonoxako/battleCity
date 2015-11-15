package test;
import core.model.Process;
import core.utils.Time;
public class Game extends Process{
	public static final float UPDATE_RATE = 60.0f;
	public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
	public static final long IDLE_TIME = 1;
	public Game(String name){
		super();
		thread.setName(name);
	}
	@Override
	public void run() {
		try {
			int fps = 0;
			int upd = 0;
			int updl = 0;
			
			long count = 0;
			
			float delta = 0; 
			
			long lastTime = Time.get();
			
			while(this.getProcessState() == ProcessState.running.getState() ||
					this.getProcessState() == ProcessState.waiting.getState()) {
				long nowTime = Time.get(); 
				long elapsedTime = nowTime - lastTime; 
				lastTime = nowTime;
				
				count += elapsedTime;
				
				boolean render = false;
				delta += ( elapsedTime / UPDATE_INTERVAL );
				
				while(delta > 1){
					//update
					delta--;
					upd++;
					if (render) {
						updl++;
					} else {
						render = true;
					}
				}
				
				if (this.getProcessState() == ProcessState.waiting.getState()){
					synchronized (monitor) {
						monitor.wait();
					}	
				}
				
				if (render) {
					//draw
					fps++;
				} else {
					try {
						Thread.sleep(IDLE_TIME);
					} catch (InterruptedException e) {
						
						return;
					}
				}
				
				if (count >= Time.SECOND) {
					System.out.println(thread.getName() +": FPS: " + fps 
							+ " | UPD: " + upd + " | UPDL: " 
							+ updl);
					upd = 0;
					fps = 0;
					updl = 0;
					count = 0;
				}
				if(thread.isInterrupted())
					return;
			}
			Thread.sleep(1);
			} catch (InterruptedException e) {
				return;
			}
		
	}

}
