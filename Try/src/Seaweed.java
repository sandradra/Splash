import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.util.Random;

public class Seaweed extends Character {

    public Seaweed(Pane layer, Image image, double x, double y, double dx, double dy) {
        super(layer, image,x, y, dx, dy);
    }
    
    public Seaweed(Pane layer) {
    		super(layer);
    		this.image = new Image("images/platform.png");
    		Random r = new Random();
    		this.x = (double)(r.nextInt(350) + 50); 
    		this.y = (double)(r.nextInt(450)); 
    }

    @Override
    public void move(int dx, int dy) {
    }

}