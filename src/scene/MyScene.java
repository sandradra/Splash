package scene;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.EventEmitter;

public abstract class MyScene {
	
	public static final int COVER_WIDTH = 475;
	public static final int COVER_HEIGHT = 600;
	
	public static final String RANK_BUTTON = "resources/button/rank_button_white.png";
	public static final String RANK_BUTTON_MOUSEOVER = "resources/button/rank_button_blue.png";
	public static final String HOME_BUTTON = "resources/button/home_button_white.png";
	public static final String HOME_BUTTON_MOUSEOVER = "resources/button/home_button_blue.png";
	public static final String CLICKED_RANK_BUTTON = "clicked_rank_button";
	public static final String CLICKED_HOME_BUTTON = "clicked_home_button";
	
	public static final String LEADERBOARD_IMAGE = "resources/page/leaderboard_page.png";
	public static final String SCORE_FILE = "resources/scores/leader_list.txt";
	public static final String HOBO_FONT_TYPE = "file:resources/font/Hobo-Std-Medium.ttf";
	
	protected Scene scene;
	protected Map<String, EventEmitter<Object>> emitterMap;
	
	public MyScene() {
		this.emitterMap = new HashMap<String, EventEmitter<Object>>();
	}
	
	public abstract Scene createScene();
	
	public static ImageView readImage (String fileName) {
		Image image = null;
		try {
			image = new Image(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (image != null) {
			ImageView imageView = new ImageView(image);
			return imageView;
		}
		else {
			// cannot read image file
			return new ImageView();
		}
	}
	
	public static Image readImage2 (String fileName) {
		Image image = null;
		try {
			image = new Image(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public Scene getScene() {
		return createScene();
	}
	
	public EventEmitter<Object> getEventEmitter(String key) {
		return this.emitterMap.containsKey(key) ? this.emitterMap.get(key) : null;
	}
	
	
}
