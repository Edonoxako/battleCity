package core.ui;

import java.awt.Graphics2D;
import java.awt.Image;

import core.model.GameObject;
import core.model.GameObjectType;

public abstract class Button extends GameObject{
	/*Int id - ID элемента для ObjectManagera, 
	 * Image active - изображения для кнопки в активном состоянии,
	 * Image deactive - изображение для кнопки в неактивном состоянии,
	 */
	protected Image bodyImage;
	protected Image activateImage;
	protected Image deactivateImage;
	private boolean activated;
	private int scaleSize;
	public  Button(int id, Image activateImage, Image deactivateImage) {
		super(id, GameObjectType.UI);
		this.activateImage = activateImage;
		this.deactivateImage = deactivateImage;
		setActivated(false);
		// TODO Auto-generated constructor stub
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
		if(activated){
			bodyImage = activateImage.getScaledInstance(bodyImage.getWidth(null) + scaleSize,
					bodyImage.getHeight(null) + scaleSize, Image.SCALE_SMOOTH);
		}else{
			bodyImage = deactivateImage;
		}
	}
	public void action(){
		
	}
	public void setScaleSize(int scaleSize){
		this.scaleSize = scaleSize;
	}
	@Override
	public void draw(Graphics2D g){
		g.drawImage(bodyImage, this.x, this.y, null);
	}
}