package core.model;

public abstract class Process implements Runnable{
	private Thread thread;
	public enum ProcessState{
		init(0),
		running(2),
		waiting(3),
		interrupt(4);
		
		private int state;
		
		ProcessState(int state){
			this.state = state;
		}
		
		public int getState(){
			return state;
		}
	}
	
	private ProcessState state;
	public Process(){
		init();
	}
	protected void init(){
		state = ProcessState.init;
		thread = new Thread(this);
	}
	//Запускает процесс, инициализируя его снова если того требует состояние
	public synchronized void start(){
			if( state == ProcessState.interrupt){
				init();
				state = ProcessState.running;
				thread.start();
			}else if( state == ProcessState.waiting){
				state = ProcessState.running;
				thread.notify();
			}else{
				return;
			}
			
	}
	//Останавливает процесс, прерывая его поток
	public synchronized void stop(){
		if(state!=ProcessState.interrupt || state!=ProcessState.init)
			return;
		
		state = ProcessState.interrupt;
		
		try {
			Thread.sleep(1);
			thread.interrupt();
		} catch (InterruptedException e){
			return;
		}
	}
	//Приостанавливает процесс
	public synchronized void pause(){
		if(state != ProcessState.running)
			return;
		
		state = ProcessState.waiting;
		
		try {
			thread.wait();
		} catch (InterruptedException e){
			if( thread.isInterrupted()){
				return;
			}
		}
	}
	public int getProcessState(){
		return state.getState();
	}
	//Приостанавливает процесс на заданное время
	public synchronized void pause(long mils){
		if(state != ProcessState.running)
			return;
		
		try {
			thread.wait(mils);
		} catch (InterruptedException e){
			if( thread.isInterrupted()){
				return;
			}
		}
	}
}
