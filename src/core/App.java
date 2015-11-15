package core;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.graphics.Scene;
import core.managers.ObjectManager;
import core.managers.ProcessManager;
import core.managers.StateManager;

public class App {
	
	public JFrame window;
	public JPanel p;
	private GraphicsDevice device;
	private boolean sizeFlag = false;
	public static ProcessManager processManager;
	public static ObjectManager objectManager;
	public static StateManager stateManager;
	public void init(){
		//TODO убрать, или доделать выбор диспле€.
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//—оздание и задание параметров главного окна приложени€
		window = new JFrame();
		window.setSize(new Dimension(600, 400));

		//»нициализаци€ менеджеров.
		
		processManager = new ProcessManager();
		objectManager = new ObjectManager();
		stateManager = new StateManager();
		
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//—оздание сцены
		Scene.create(window);
		
		//ќбработка событий окна.
		window.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				processManager.startAll();
				
			}
			@Override
			public void componentResized(ComponentEvent arg0) {
				if (Scene.isCreated()){
					processManager.stopAll();
					Scene.resize(window.getRootPane().getSize());
					processManager.startAll();
				}
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				processManager.stopAll();
			}
		});
		//ќбработка глобальных "гор€чих клавиш".
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F11) {
					processManager.stopAll();
					if (!sizeFlag){
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
	            			sizeFlag = true;
	            		}
					}else {
						//window.setUndecorated(false);
						//window.setAlwaysOnTop(false);
						processManager.stopAll();
						window.dispose();
						window.setUndecorated(false);
						device.setFullScreenWindow(null);
						window.setVisible(true);
						sizeFlag = false;
						//processManager.startAll();
					}
					processManager.startAll();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}
	
	public void start(){
		processManager.startAll();
	}
}

