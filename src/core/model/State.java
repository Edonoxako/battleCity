package core.model;

public abstract class State {
private boolean isInit;
	
	public void init(){
		isInit = true;
	}
	//���������� ���� �������������.
	public boolean isInit(){
		return isInit;
	}
}
