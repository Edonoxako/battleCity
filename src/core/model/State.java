package core.model;

public abstract class State {
private boolean isInit;
	
	public void init(){
		isInit = true;
	}
	//Возвращает флаг инициализации.
	public boolean isInit(){
		return isInit;
	}
}
