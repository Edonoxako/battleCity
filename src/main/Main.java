package main;

import java.util.ArrayList;

import core.App;
import core.graphics.Scene;
import core.managers.ObjectManager;
import test.Tr;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		App app = new App();
//		app.init();
//		Scene.create(app.window);
//
//		app.start();
//		Tclass t = new Tclass();
//		System.out.println(t.getX());
//		System.out.println(t.isDraw());
//		Canvas c = new Canvas();
//		Graphics g = c.getGraphics();
//		t.draw((Graphics2D) g);
//		t.update();
//		System.out.println("");
//		Tr a = null;
//		ArrayList<Tr> trList = new ArrayList<Tr>(10);
//		for (int i = 0; i < 10; i++) {
//			if ( i == 1) {
//				a = new Tr(i);
//				trList.add(a);
//			}else{
//				trList.add(new Tr(i));
//			}
//		}
//
//		for (int i = 0; i < 10; i++)
//			trList.get(i).who();
//		System.out.println("============");
//		trList.get(trList.indexOf(a)).who();

        //---Тестим менеджер объектов---
        ObjectManager manager = new ObjectManager();
        Tr obj1 = new Tr(1);
        Tr obj2 = new Tr(2);
        Tr obj3 = new Tr(3);

        manager.addObject(obj1);
        manager.addObject(obj2);
        manager.addObject(obj3);

        manager.showObjects();

        manager.removeObject(2);
        System.out.println("Removed 2d object");
        manager.showObjects();
        //-------------------------------
	}

}
