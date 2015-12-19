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
	//¬озвращает последнее состо€ние не удал€€ его
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
	/*ћетод ищет заданный элемент в стеке, возвраща€ количество операций pop, 
	которые требуютс€ дл€ того чтобы перевести искомый элемент в вершину стека. 
	≈сли заданный элемент в стеке отсутствует, этот метод возвращает -1.*/
	public int search(State s) {
		return StateStack.search(s);
	}
}