package characters;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Seaweed extends Character implements Comparable<Seaweed>{

	
    public Seaweed(Pane layer, Image image, double x, double y, double dx, double dy) {
        super(layer, image,x, y, dx, dy);
    }
    
	@Override
	public int compareTo(Seaweed other) {
		int result = (int) ((this.y - other.y) * 1000);
		return result;
	}
	
	@Override
	public String toString() {
		return "seaweed: " + this.y + "\n";
	}


}