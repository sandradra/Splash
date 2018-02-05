package characters;

import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import util.EventEmitter;

public class Whale extends Character {

	public Timeline jump, fall, left, right;
	private EventEmitter<Whale> moveEmitter = new EventEmitter<Whale>();
	private Seaweed currentSeaweed = null;
	
	public Whale(Pane layer, Image image, double x, double y, double dx, double dy) {
		super(layer, image, x,y, dx, dy);

		jump = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			move(0, -10);
			updateUI();
			currentSeaweed = null;
		}));
		jump.setCycleCount(20);


		fall = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			move(0, 10);
			updateUI();
		}));
		fall.setCycleCount(Timeline.INDEFINITE); // original 20


		left = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			if (this.x > 5) {
				move(-5,0);
				updateUI();
				
				if (currentSeaweed != null && !collidesWith(currentSeaweed)) {
					fall.play();
				}
			}
		}));
		left.setCycleCount(Timeline.INDEFINITE);

		right = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
			if (this.x < (COVER_WIDTH - this.w / 2)){
				move(5, 0);
				updateUI();
				
				if (currentSeaweed != null && !collidesWith(currentSeaweed)) {
					fall.play();
				}
			}
		}));
		right.setCycleCount(Timeline.INDEFINITE);

		jump.setOnFinished(actionEvent -> fall.play());

	}

	@Override
	public void move(int dx, int dy) {
		System.out.println("Whale.move()");
		super.move(dx,dy); 
		moveEmitter.emit(this);
		
	}

	public double getWhaleX() {
		return x;
	}

	public double getWhaleY() {
		return y;
	}

	public boolean checkHitRubbish(ArrayList<Rubbish> rubbish) {
		boolean hit = false;
			for(Rubbish r: rubbish) {
			if (this.collidesWith(r)) {
				hit = true;
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

	public void stopjump() {
		jump.stop();
	}
	
	public void stopFall() {
		fall.stop();
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

	public EventEmitter<Whale> getMoveEmitter() {
		return this.moveEmitter;
	}

	public boolean checkWhaleInScreen() {
		return y < (COVER_HEIGHT + h/2);
	}

	public void toFront() {
		this.toFront();
	}
	
	// override collidesWith function exclusively for checking the collision between whale and platforms to cater for the whale tail
	@Override
	public boolean collidesWith(Character other) {
		return (other.x + other.w >= x && other.y + other.h >= y && other.x <= x + w / 2 && other.y <= y + h);
	}

	// if whale collides with seaweeds, the whale will stay on top of seaweeds
	public void checkHittedSeaweed(ArrayList<Seaweed> seaweeds) {
		for (Seaweed seaweed: seaweeds) {
			if (this.collidesWith(seaweed)){
				stopFall();
				this.currentSeaweed = seaweed;
				break;
			}
		}
	}

	// find the list of seaweeds with y coordinate lower than whale
	public ArrayList<Seaweed> findSeaweedBelowWhale(ArrayList<Seaweed> seaweeds, Whale whale) {

		ArrayList<Seaweed> seaweedsBelowWhale = new ArrayList<Seaweed>();

		for (int i = 0; i < seaweeds.size(); i++) {
			if ((seaweeds.get(i).y - seaweeds.get(i).h) > whale.y ) {
				seaweedsBelowWhale.add(seaweeds.get(i));
			}
		}
		Collections.sort(seaweedsBelowWhale);

		return seaweedsBelowWhale;
	}

}