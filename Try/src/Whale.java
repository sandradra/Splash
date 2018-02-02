import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Whale extends Character {

    double playerShipMinX, playerShipMaxX, playerShipMinY, playerShipMaxY;

    public Whale(Pane layer, Image image, double x, double y, double dx, double dy) {
        super(layer, image, x, y, dx, dy);
        init();
    }

    private void init() {

        playerShipMinX = 0 - image.getWidth() / 2.0;
        playerShipMaxX = Settings.SCENE_WIDTH - image.getWidth() / 2.0;
        playerShipMinY = 0 - image.getHeight() / 2.0;
        playerShipMaxY = Settings.SCENE_HEIGHT -image.getHeight() / 2.0;
    }
    
    @Override
    public void move() {
        super.move(); 
    }

}