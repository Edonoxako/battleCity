package test;

import java.awt.Graphics2D;

public class Tclass extends Twrap{
	
	@Override
	public void draw(Graphics2D g) {
		System.out.println("����� ����");
		
	}

	@Override
	public void update() {
		System.out.println("�������� ����");
	}

}
