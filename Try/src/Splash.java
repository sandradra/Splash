import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Splash {

    double playerShipMinX, playerShipMaxX, playerShipMinY, playerShipMaxY;
    
    public static final String SPLASH_IMAGE = "resources/splash/splash.png";

//    public Splash(Pane layer, Image image, double x, double y, double dx, double dy) {
//    }    
    
    public Splash(double x, double y, double dx, double dy) {
		this.image = new Image(SPLASH_IMAGE);
    }    
    
    
    public createSplash() {
    		
    		Whale whale = new Whale();
    	
    		// add splash to the pane w.r.t. whale x and y coordinate
    		Splash splash = new Splash(Whale.getWhaleX(), Whale.getWhaleY(), 0, 0);
    		
    		// the splash moves upwards
    		Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

    		    @Override
    		    public void handle(ActionEvent event) {
    		        System.out.println("this is called every 5 seconds on UI thread");
    		    }
    		}));
    		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
    		fiveSecondsWonder.play();
    }


}