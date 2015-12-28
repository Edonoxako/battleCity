package game.ui.frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.utils.IdService;
import core.utils.ResourceLoader;

public class UiFrame extends GameObject{
	private BufferedImage topPanel;
	private BufferedImage downPanel;
	private int width, height, topPanelHeight, downPanelHeight, life_bar_posX, life_bar_posY;
	public UiFrame(int width, int height, int life_percent) {
		super(IdService.generateId(), GameObjectCategory.UI);
		this.width = width;
		this.height = height;
		Image up_bar = ResourceLoader.loadImage("UI/Shell/up_bar.png");
		Image left_down_corner = ResourceLoader.loadImage("UI/Shell/left_down_corner.png");
		Image right_down_corner = ResourceLoader.loadImage("UI/Shell/right_down_corner.png");
		Image down_bar = ResourceLoader.loadImage("UI/Shell/down_bar.png");
		Image life_bar = ResourceLoader.loadImage("UI/Shell/life_bar.png");
		Image bonus_bar = ResourceLoader.loadImage("UI/Shell/bonus_bar.png");
		topPanelHeight = up_bar.getHeight(null);
		downPanelHeight = down_bar.getHeight(null);
		topPanel = new BufferedImage(this.width, topPanelHeight, BufferedImage.TYPE_INT_ARGB);
		downPanel = new BufferedImage(this.width, downPanelHeight, BufferedImage.TYPE_INT_ARGB);
		topPanel.getGraphics().drawImage(up_bar, 0, 0, this.width, up_bar.getHeight(null), null);
		downPanel.getGraphics().drawImage(left_down_corner, 0, 0, 
				left_down_corner.getWidth(null), left_down_corner.getHeight(null), null);
		downPanel.getGraphics().drawImage(down_bar, left_down_corner.getWidth(null), 
				0, width-right_down_corner.getWidth(null), 
				down_bar.getHeight(null),null);
		downPanel.getGraphics().drawImage(right_down_corner, this.width-right_down_corner.getWidth(null), 
				0, right_down_corner.getWidth(null), 
				downPanelHeight, null);
		life_bar_posX = left_down_corner.getWidth(null);
		life_bar_posY = 0;
		downPanel.getGraphics().drawImage(life_bar, left_down_corner.getWidth(null), 0, 
				life_bar.getWidth(null), downPanelHeight,null);
		setLife(life_percent);
		int bonus_width, bonus_height;
		bonus_width = bonus_bar.getWidth(null);
		bonus_height = bonus_bar.getHeight(null);
		downPanel.getGraphics().drawImage(bonus_bar, 638, 7, 
				bonus_bar.getWidth(null), bonus_height, null);
		downPanel.getGraphics().drawImage(bonus_bar, 638+bonus_width+11, 7, 
				bonus_bar.getWidth(null), bonus_height, null);
		downPanel.getGraphics().drawImage(bonus_bar, 638+bonus_width*2+22, 7, 
				bonus_bar.getWidth(null), bonus_height, null);
		setLife(life_percent);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(topPanel, 0, 0, null);
		g.drawImage(downPanel, 0, height-downPanelHeight, null);
		
	}

	@Override
	public void update() {
		
		
	}
	
	public Graphics getGraphicsDownPanel(){
		return downPanel.getGraphics();
	}
	
	public Graphics getGraphicsTopPanel(){
		return topPanel.getGraphics();
	}
	
	public void setLife(int percent){
		Image hp = null;
		switch (percent) {
		case 100:
			hp = ResourceLoader.loadImage("UI/Shell/hp_bar_100.png");
			break;
		case 75:
			hp = ResourceLoader.loadImage("UI/Shell/hp_75.png");
			break;
		case 30:
			hp = ResourceLoader.loadImage("UI/Shell/hp_30.png");
			break;
		case 0:
			hp = ResourceLoader.loadImage("UI/Shell/hp_0.png");
			break;

		default:
			break;
		}
		if(hp!=null){
			downPanel.getGraphics().drawImage(hp, life_bar_posX+94, life_bar_posY+11, 
					hp.getWidth(null), hp.getHeight(null), null);
		}
	}
	
}
