import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game{

	private boolean gameStatus;
	private int score;
	private String playerName;
	private Rubbish rubbish;
	private Seaweed[] seaweed;
	private Whale whale;

	public Game() {
		gameStatus = true;
		playerName = "";
		score = 0;
		rubbish = new Rubbish();
		seaweed = createSeaweed();
		whale = new Whale();
	}

	public Seaweed[] createSeaweed() {
		Seaweed[] seaweed = new Seaweed[12];
		for (int i=0; i<12; i++) {
			seaweed[i] = new Seaweed();
		}
		return seaweed;
	}

	public void initGame() {
		MediaPlayer bgMusic =  new MediaPlayer( new Media("music/bgMusic.mp3"));
		ImageView bgImage =  new ImageView("image/bgImage.jpg");
		bgMusic.play();  
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {

		}

		if (key == KeyEvent.VK_RIGHT) {

		}

		if (key == KeyEvent.VK_UP) {

		}

		if (key == KeyEvent.VK_DOWN) {
		}  
	}

	public boolean trackGameStatus(Splash splash, Whale whale) {
		//if (splash.checkhitRubbish = true || whale.checkFall = true) {
		// this.gameStatus = false;
		//}
		return this.gameStatus;
	}

	public int calculateScore(Whale whale) {
		// this.score = whale.getScore();
		return this.score;
	}

	public void resetGame() {
		this.playerName = JOptionPane.showInputDialog(null, "Please enter your name", "Recording Player Name", JOptionPane.QUESTION_MESSAGE);
	}

	public void displayEndGamePage() {  
	}

}
