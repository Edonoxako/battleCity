package core;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

import core.graphics.Scene;
import core.managers.ObjectManager;
import core.managers.ProcessManager;
import core.managers.StateManager;
import core.utils.Input;
import core.utils.ResourceLoader;
import test.TetstStatePause;

public class App {
	
	public static JFrame window;
	private GraphicsDevice device;
	private boolean fullSize;
	public static boolean pauseFlag = false;
	public static Input input;
	public static ProcessManager processManager;
	public static ObjectManager objectManager;
	public static StateManager stateManager;
	public static Dimension defaultFrameSize;
	private Properties property;
	public void init(){
		property = new Properties();
	    try {
	        property.load(ResourceLoader.loadConfigDefault());
	        defaultFrameSize = new Dimension(Integer.parseInt(property.getProperty("default.window.width")), 
	        		Integer.parseInt(property.getProperty("default.window.height")));
	        fullSize = Boolean.getBoolean(property.getProperty("window.fullsize"));
	        System.out.println(defaultFrameSize.toString());
	    } catch (IOException e) {
	        System.err.println("ОШИБКА: Файл свойств отсуствует!");
	    }
		//TODO убрать, или доделать выбор дисплея.
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//Создание и задание параметров главного окна приложения
		window = new JFrame();
		window.setSize(defaultFrameSize);

		//Инициализация менеджеров.
		
		processManager = new ProcessManager();
		objectManager = new ObjectManager();
		stateManager = new StateManager();
		input = new Input();
		
		
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TetstStatePause ps = new TetstStatePause();
		//Создание сцены
		Scene.create(window);
		window.add(input);
		//Обработка событий окна.
		window.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				//processManager.startAll();
				
			}
			@Override
			public void componentResized(ComponentEvent arg0) {
					//processManager.stopAll();
					Scene.resize(window.getRootPane().getSize());
					
					//processManager.startAll();
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				//processManager.stopAll();
			}
		});
		//Обработка глобальных "горячих клавиш".
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F11) {
					//processManager.stopAll();
					if (!fullSize){
						window.dispose();
		            	window.setUndecorated(true);
		            	//window.setAlwaysOnTop(true);
		            	window.setVisible(true);
						try{
							java.awt.EventQueue.invokeLater(new Runnable() {
					            @Override
					            public void run() {
					            	
					                if(window != null) {
					                	window.toFront();
					                	window.repaint();
					                	//get over here, focus!
				            			device.setFullScreenWindow(window);
				            			
				            			
					                }
					            }
					        });	
	            		} finally {
	            			fullSize = true;
	            		}
					}else {
						//window.setUndecorated(false);
						//window.setAlwaysOnTop(false);
						//processManager.stopAll();
						window.dispose();
						window.setUndecorated(false);
						device.setFullScreenWindow(null);
						window.setVisible(true);
						fullSize = false;
						//processManager.startAll();
					}
					//processManager.startAll();
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(!pauseFlag){
						stateManager.peek().block();
						stateManager.push(ps);
						pauseFlag = true;
					}else{
						stateManager.pop();
						stateManager.peek().unBlock();
						pauseFlag = false;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}
	
	public void start(){
		System.out.println("APP init");
	}
}

