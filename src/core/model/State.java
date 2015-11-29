package core.model;

public abstract class State {
private boolean isInit;
	public State(){
		isInit = false;
	}
	public void setInit(boolean b){
		isInit = b;
	}
	//���������� ���� �������������.
	public boolean isInit(){
		return isInit;
	}
	
	public void init(){
		
	}
	
	public void block(){
		
	}
	
	public void unBlock(){
		
	}
	
	public void destroy(){
		
	}
}
