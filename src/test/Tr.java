package test;

import java.awt.Graphics2D;

import core.model.GameObject;

public class Tr extends GameObject{
	//private int id;

	public Tr(int id) {
		super(id);
	}

//	public Tr(int id) {
//		setId(id);
//	}
	
	public void who() {
		System.out.println("I'm core.model, my id: #"+ getId());
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

//	public int getId() {
//		return id;
//	}

//	public void setId(int id) {
//		this.id = id;
//	}

//	public boolean hasId(int id) {
//        return id == this.id;
//    }
	
}
