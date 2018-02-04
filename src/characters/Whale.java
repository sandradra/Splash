package characters;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Whale extends Character {

	private Timeline jump, fall, left, right;

	public Whale(Pane layer, Image image, double x, double y, double dx, double dy) {
		super(layer, image, x,y, dx, dy);

		jump = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			move(0, -10);
			updateUI();
		}));
		jump.setCycleCount(20);

		fall = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			move(0, 10);
			updateUI();
		}));
		fall.setCycleCount(20);

		left = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			if (this.x > 5 ) {
				move(-5,0);
				updateUI();
			}
		}));
		left.setCycleCount(Timeline.INDEFINITE);

		right = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			if (this.x < (COVER_WIDTH - this.w - 5)){
				move(5, 0);
				updateUI();
			}
		}));
		right.setCycleCount(Timeline.INDEFINITE);

		jump.setOnFinished(actionEvent -> fall.play());
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


	public boolean checkHitPlatform(ArrayList<Seaweed> seaweed) {	
		boolean hit= true;
		for (Seaweed i:seaweed) {
			hit = this.collidesWith(i);
			if (hit = false) {
				System.out.println("Game Over!");
			}else {
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

	public void jump() {
		if (!jump.getStatus().equals(Status.RUNNING) 
				&& !fall.getStatus().equals(Status.RUNNING)) {
			jump.play();
		}
	}

	public void left() {
		left.play();
	}

	public void right() {
		right.play();
	}

	public void stopLeft() {
		left.stop();
	}

	public void stopRight() {
		right.stop();
	}


}