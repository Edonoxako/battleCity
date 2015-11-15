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
import test.ThreadProcTest;
//import test.ThreadProcTest;

public class App {

	public JFrame window;
	public JPanel p;
	private GraphicsDevice device;
	private boolean sizeFlag = false;

	public void init(){
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		window = new JFrame();
		window.setSize(new Dimension(600, 400));
//		BorderLayout bl = new BorderLayout();
//		bl.setHgap(0);
//		bl.setVgap(0);
//		window.setLayout(bl);
//		p = new JPanel();
//		window.getContentPane().add(p, BorderLayout.CENTER);
		//Инициализация менеджеров
		
		
		
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
//				tScene.clear();
//				tScene.swapBuffers();
				
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				if (Scene.isCreated()){
					Scene.resize(window.getRootPane().getSize());
				}
//				tScene.clear();
//				tScene.swapBuffers();
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F11) {
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
	            			
	            		}
						
						sizeFlag = true;
					}else {
						//window.setUndecorated(false);
						//window.setAlwaysOnTop(false);
						window.dispose();
						window.setUndecorated(false);
						device.setFullScreenWindow(null);
						window.setVisible(true);
						sizeFlag = false;
						
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		Scene.create(window);
	}
	public void start(){
		ThreadProcTest procTest = new ThreadProcTest();
		procTest.start();
	}
}

