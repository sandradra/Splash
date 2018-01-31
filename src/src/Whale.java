import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Whale extends Character{
    private enum Direction {
        Left, Right, Up, Down
    }
	private int hVelocity;
	private int vVelocity;
	private int x;
	private int y;
	private int width;
	private int height;
    private Image img = new Image( "images/doodleR.png" );
    private int speed = 40;
    
	public Whale(){
		
	}
	
}
