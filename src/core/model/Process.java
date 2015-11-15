package core.model;

public abstract class Process implements Runnable{
	public Thread thread;
	public Object monitor;//Обьект синхронизации.
	public enum ProcessState{
		init(0), 		//Процесс создан, но не запущен;
		running(2),		//Процесс выполняется;
		waiting(3),		//Процесс ждет;
		interrupt(4);	//Процесс остановлен;
		
		private int state;
		
		ProcessState(int state){
			this.state = state;
		}
		//Возвращает int значение состояния процесса.
		public int getState(){
			return state;
		}
		
	}
	
	private ProcessState state;
	public Process(){
		monitor = new Object();
		init();
	}
	protected void init(){
		state = ProcessState.init;
		thread = new Thread(this);
	}
	//Запускает процесс, инициализируя его снова если того требует состояние.
	public void start(){
		if(state == ProcessState.running){
			return;
		}
		if(state == ProcessState.interrupt){
			init();
			state = ProcessState.running;
			thread.start();
			return;
		}
		if( state == ProcessState.waiting){
			state = ProcessState.running;
			synchronized (monitor) {
				monitor.notifyAll();
			}
			
			return;
		}
		state = ProcessState.running;
		thread.start();
		return;		
	}
	//Останавливает процесс, прерывая его поток.
	public void stop(){
		if(state==ProcessState.interrupt || state==ProcessState.init)
			return;
		
		state = ProcessState.interrupt;
		
		try {
			Thread.sleep(1);
			thread.interrupt();
		} catch (InterruptedException e){
			return;
		}
	}
	//Приостанавливает процесс.
	public void pause(){
		if(state != ProcessState.running)
			return;
		//Yellow tricycle - "Little bastard"
		state = ProcessState.waiting;
		
	}
	public int getProcessState(){
		return state.getState();
	}
//TODO исправить этот метод или убрать его.
//	//Приостанавливает процесс на заданное время.
//	public synchronized void pause(long mils){
//		if(state != ProcessState.running)
//			return;
//		
//		try {
//			thread.wait(mils);
//		} catch (InterruptedException e){
//			if( thread.isInterrupted()){
//				return;
//			}
//		}
//	}
}