package characters;

import java.util.ArrayList;
import java.util.Iterator;
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

	public double getWhaleX() {
		return x;
	}

	public double getWhaleY() {
		return y;
	}


	public boolean checkHitPlatform(Seaweed[] seaweed, int score) {	
		boolean hit = false;
		for (Seaweed i:seaweed) {
			hit = this.collidesWith(i);
			if (hit = false) {
				System.out.println("Game Over!");
			}else {
				score += 20;
			}
		}
		return hit;
	}

	public boolean checkHitRubbish(ArrayList<Rubbish> rubbish) {
		boolean hit = false;
		for(Iterator<Rubbish> it = rubbish.iterator(); it.hasNext(); ) {
			Rubbish r = it.next();
			if (this.collidesWith(r)) {
				r.removeFromLayer();
				it.remove();
				hit = true;
			}else{
				this.dx = 0;
			}
		}
		return hit;
	}


}