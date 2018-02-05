package characters;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import scene.MyScene;

public class Splash extends Character {

	double playerShipMinX, playerShipMaxX, playerShipMinY, playerShipMaxY;
	public static final String SPLASH_IMAGE = "resources/splash/splash.png";
	boolean visible;
	
	public Splash(Pane layer, Image image, double x, double y) {
		super(layer, image, x, y);
		this.image = MyScene.readImage2(SPLASH_IMAGE);
	    this.visible = true;
	}	

	public void setVisible (boolean visible) {
			this.visible = visible;
	}
	
	public boolean getVisible() {
			return this.visible;
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