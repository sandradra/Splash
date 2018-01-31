import javafx.scene.image.Image;

public abstract class Character {
	protected int x, y, width, height;
	protected Image icon;
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Image getIcon() {
		return this.icon;
	}
	
	// check if two characters collide with each other
//	public abstract boolean checkHit(Character character);
	

}
