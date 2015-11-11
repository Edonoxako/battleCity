package core.model;

public abstract class Process implements Runnable{
	private Thread thread;
	protected boolean running;
	
	public synchronized void start(){
			
			if(running)
				return;
			running = true;
			thread = new Thread(this);
			thread.start();
			
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		
		try {
			Thread.sleep(1);
			thread.interrupt();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
}
