import javax.swing.JOptionPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Game{

	private boolean gameOver = false;
	private int score = 0;
	private String playerName = "";
	private Rubbish rubbish;
	private Seaweed[] seaweed;
	private Whale whale;

	public Game() {
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

	public Canvas startGame() {
		
		 Canvas canvas = new Canvas( 500, 800 );
	     GraphicsContext gc = canvas.getGraphicsContext2D();       

	     	Image bg = new Image("images/sea_cover.png");
	        Image whale  = new Image( "images/doodleR.png" );
	        Image seaweed = new Image( "images/doodleS.png" );
	        
	        gc.drawImage(bg,0,0);
	        gc.drawImage( this.rubbish.getImg(), this.rubbish.getX(), this.rubbish.getY());
	        gc.drawImage( whale, 200, 300 );
	        
	        for (int i=0; i <12; i++) {
	        gc.drawImage( seaweed, Math.random()*501, Math.random()*801 );
	        }
	        
	        return canvas;
	        
	}
	
	public boolean trackGameStatus(Splash splash, Whale whale) {
		//if (splash.checkhitRubbish = true || whale.checkFall = true) {
		// this.gameStatus = true;
		//}
		return this.gameOver;
	}

	public void endGame() {
		//this.score = whale.getScore();
		//this.playerName = JOptionPane.showInputDialog(null, "Please enter your name", "Recording Player Name", JOptionPane.QUESTION_MESSAGE);
	}
	
	public void resetGame() {

    }

	public void displayEndGamePage() { 
		
	}

}
