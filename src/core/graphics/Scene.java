package core.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;

public class Scene {
		private static boolean created = false;
		public static Canvas content;
		
		private static BufferedImage buffer;
		private static int[] bufferData;
		private static Graphics bufferGraphics;
		private static int clearColor = 0xffffffff;
		
		private static BufferStrategy bufferStrategy;
			
		public static void create(JFrame win){
			if (created)
				return;
			content = new Canvas();
			content.setSize(win.getRootPane().getSize());
			win.getContentPane().add(content);
			created = true;
			content.setFocusable(false);
			buffer = new BufferedImage(win.getWidth(), 
					win.getHeight(), BufferedImage.TYPE_INT_ARGB);
			bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData(); 
			bufferGraphics = buffer.getGraphics();
			((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			content.createBufferStrategy(2); 
			bufferStrategy = content.getBufferStrategy();
			
		}

		public static void clear(){
			Arrays.fill(bufferData, clearColor);
		}

		public static void swapBuffers(){
			try {
				Graphics g = bufferStrategy.getDrawGraphics();
				g.drawImage(buffer, 0, 0, null);
				bufferStrategy.show();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		//Получение буфера рисования
		public static Graphics2D getGraphics(){
			if (created){
				return (Graphics2D) bufferGraphics;
			}else{
				return null;
			}
		}

		public static void destroy(){
			if (!created)
				return;

		}
		//Изменение размера сцены
		public static void resize(Dimension size){
			try {
				content.setSize(size);
				if(created){
					buffer = new BufferedImage(content.getWidth(), 
							content.getHeight(), BufferedImage.TYPE_INT_ARGB);
					bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData(); 
					bufferGraphics = buffer.getGraphics();
					((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
							RenderingHints.VALUE_ANTIALIAS_ON);
					content.createBufferStrategy(2); 
					bufferStrategy = content.getBufferStrategy();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			
		}

		public static boolean isCreated() {
			// TODO Auto-generated method stub
			return created;
		}
		
//		public static void setTitle(String title){
//			window.setTitle(title);
//		}
		
//		public static void addInputListener(tInput inputListener){
//				window.add(inputListener);
//		}
		
//		public static int getWidth() {
//			return window.getWidth();
//		}
//		public static int getHeight() {
//			return window.getHeight();
//		}
}
