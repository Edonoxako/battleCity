package core.managers;

import java.util.Stack;

import core.model.State;

public class StateManager {
	Stack<State> StateStack;
	
	public State processingState(){
		return peek();
	}
	
	
	public StateManager() {
		StateStack = new Stack<State>();
	}
	
	public boolean empty() {
		return StateStack.empty();
		
	}
	//���������� ��������� ��������� �� ������ ���
	public State peek() {
		return StateStack.peek();
	}
	
	public synchronized State pop() {
		StateStack.peek().destroy();
		return StateStack.pop();
	}
	
	public State push(State s) {
		StateStack.push(s).init();
		return s;
	}
	/*����� ���� �������� ������� � �����, ��������� ���������� �������� pop, 
	������� ��������� ��� ���� ����� ��������� ������� ������� � ������� �����. 
	���� �������� ������� � ����� �����������, ���� ����� ���������� -1.*/
	public int search(State s) {
		return StateStack.search(s);
	}
}