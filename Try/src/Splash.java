import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class Splash extends Character {
	
    public Splash(Pane layer, Image image, double x, double y, double dx, double dy) {
        super(layer, image, x, y, dx, dy);
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