
import javafx.scene.image.Image;

public class Character {
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
	
	// check if two characters are in the same place
	public boolean checkHit(Character character) {
		if ( this.)
	}
	
	
	public boolean equals(Character obj)
	{
		Character other = obj;
		if(getX()+getWidth()>=other.getX()&&
			getX()<=other.getX()+other.getWidth() &&
			getY()+getHeight() >=other.getY() &&
			getY() <= other.getY()+other.getHeight())
		   return true;
		return false;
	}
}
