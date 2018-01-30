package scene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MyScene {
	
	public static final int COVER_WIDTH = 475;
	public static final int COVER_HEIGHT = 600;
	
	protected Scene scene;
	
	public abstract Scene createScene() throws FileNotFoundException;
	
	public static ImageView readImage (String fileName) throws FileNotFoundException {
		Image image = new Image(new FileInputStream(fileName));
		ImageView imageView = new ImageView(image);
		return imageView;
	}
	
	public Scene getScene() throws FileNotFoundException {
		return this.createScene();
	}
	
	
}
