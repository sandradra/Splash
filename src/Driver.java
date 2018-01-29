import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

public class Driver extends Application {

 @Override
 public void start(Stage theStage) {
   
 AudioClip introMusic =  new AudioClip("sound/mystery.wav");
 introMusic.play();
 ImageView introImage =  new ImageView("images/bg-grid.png");  
 Button btStart = new Button("",new ImageView("images/doodleR.png"));
 Button btRecord = new Button("",new ImageView("images/doodleS.png"));  
 Group mainScreen = new Group(introImage,btStart,btRecord);
 Scene scene = new Scene(mainScreen, 500, 200);
 theStage.setTitle("Splash!"); 
 theStage.setScene(scene);
 theStage.show();
 
 }
  
//  public void displayScore() {
//   AudioClip introMusic =  new AudioClip("music/introMusic.mp3");
//   introMusic.play();
//   ImageView scoreImage =  new ImageView("image/ScoreImage.jpg");  
//   Button btBack = new Button("",new ImageView("image/back.jpg"));
//   StackPane pane = new StackPane();
//   pane.getChildren().addAll(scoreImage,btBack);   
//  }
  
   public static void main(String[] args) {
    launch(args);
  }
   
}
