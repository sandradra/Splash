import java.awt.*;    
import javax.swing.*; 
import java.net.URL;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Rubbish {
	private int x;
	private int y;
	private int width;
	private int length;
	private Image img;
	
	public Rubbish() {
		int x = (int) Math.random()*501;
		int y = (int) Math.random()*801;
		this.img = new Image( "images/doodleR.png" );
	}

 public Image getImg() {  
     return this.img;
 }
 
 public int getX() {
	 return this.x; 
 }
 
 public int getY() {
	 return this.y;
 }


}
