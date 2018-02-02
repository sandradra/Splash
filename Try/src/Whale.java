import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Whale extends Character {

    public Whale(Pane layer, Image image, double x, double y, double dx, double dy) {
        super(layer, image, x,y, dx, dy);
    }
    
    @Override
    public void move(int dx, int dy) {
        super.move(dx,dy); 
    }

	public int checkHitPlatform(Seaweed[] seaweed, int score) {	
		boolean hit;
		for (Seaweed i:seaweed) {
			hit = this.collidesWith(i);
			if (hit = false) {
				System.out.println("Game Over!");
			}else {
				score += 20;
			}
		}
		return score;
	}
	
}