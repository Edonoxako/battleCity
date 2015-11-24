package main;

import core.App;
import test.TestStateGame;
import core.managers.TileMapManager;
import core.model.GameObjectType;
import test.Game;
import test.MovingObject;

public class Main {

	public static void main(String[] args) {

        //---Запуск приложения---
		App app = new App();
		app.init();
		
		//add test object
		TestStateGame gm = new TestStateGame();
		App.stateManager.push(gm);
		
        //-----------------------
		app.start();
		
		
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

//        //---Тестим менеджер объектов---
//        ObjectManager manager = new ObjectManager();
//        Tr obj1 = new Tr(1, GameObjectType.PLAYER);
//        Tr obj2 = new Tr(2, GameObjectType.ENEMY);
//        Tr obj3 = new Tr(3, GameObjectType.WALL);
//        Tr obj4 = new Tr(4, GameObjectType.WALL);
//        Tr obj5 = new Tr(5, GameObjectType.PLAYER);
//        Tr obj6 = new Tr(6, GameObjectType.ENEMY);
//        Tr obj7 = new Tr(7, GameObjectType.ENEMY);
//        Tr obj8 = new Tr(8, GameObjectType.ENEMY);
//
//        manager.addObject(obj1);
//        manager.addObject(obj2);
//        manager.addObject(obj3);
//        manager.addObject(obj4);
//        manager.addObject(obj5);
//        manager.addObject(obj6);
//        manager.addObject(obj7);
//        manager.addObject(obj8);
//
//        manager.showObjects();
//
//        manager.removeObject(2);
//        System.out.println("Removed 2d object");
//        manager.showObjects();
//
//        manager.sortObjects();
//        manager.showObjects();
//        //-------------------------------
//        //-----TEST PROCESS MANAGER------
//        //used example class test Game
//        ProcessManager processmanager = new ProcessManager();
//		Game gm1 = new Game("���� 1");
//		Game gm2 = new Game("���� 2");
//		Game gm3 = new Game("���� 3");
//		processmanager.addProc(gm1);
//		processmanager.addProc(gm2);
//		processmanager.addProc(gm3);
//		System.out.println("Start all proc:.. Control thread wait 3 seconds");
//		processmanager.startAll();
//		
//		try {
//			Thread.sleep(3000l);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Pause all proc:.. Control thread wait 3 seconds");
//		processmanager.stopAll();
//		
//		try {
//			int i = 0;
//			while(i<3){
//				Thread.sleep(1000l);
//				System.out.print(".");
//				i++;
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("Start all proc:.. Control thread wait 3 seconds");
//		processmanager.startAll();
//		
//		try {
//			Thread.sleep(3000l);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		System.out.println("Pause all proc:.. Control thread wait 3 seconds");
//		processmanager.stopAll();
//		
//		try {
//			int i = 0;
//			while(i<3){
//				Thread.sleep(1000l);
//				System.out.print(".");
//				i++;
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		System.out.println("Start proc gm1:.. Control thread wait 3 seconds");
//		processmanager.start(gm2);
//		try {
//			int i = 0;
//			while(i<3){
//				Thread.sleep(1000l);
//				System.out.print(".");
//				i++;
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//---TEST PROCESS MANAGER END---------
	}

}
