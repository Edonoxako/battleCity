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
	private static Dimension size;
	private static BufferStrategy bufferStrategy;
		
	public static void create(JFrame win){
		if (created)
			return;
		content = new Canvas();
		size = win.getContentPane().getSize();
		System.out.println(size);
		content.setSize(size);
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
		if(created){
			Arrays.fill(bufferData, clearColor);
		}
	}

	public static void swapBuffers(){
		try {
			if(created){
				Graphics g = bufferStrategy.getDrawGraphics();
				g.drawImage(buffer, 0, 0, null);
				bufferStrategy.show();
			}
		} catch (Exception e) {
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
		created = false;

	}
	//Изменение размера сцены
	public static void resize(Dimension size){
		try {
			Scene.size = size;
			content.setSize(size);
			buffer = new BufferedImage(content.getWidth(), 
			content.getHeight(), BufferedImage.TYPE_INT_ARGB);
			bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData(); 
			bufferGraphics = buffer.getGraphics();
			((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			content.createBufferStrategy(2); 
			bufferStrategy = content.getBufferStrategy();
		} catch (Exception e) {
			//Нечего не делать
		}
	}
	public static Dimension getSize(){
		if(created){
			return size;
		}
		return null;
	}
	public static boolean isCreated() {
		// TODO Auto-generated method stub
		return created;
	}
}
