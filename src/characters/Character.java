package characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Character{
	
    Image image;
    ImageView imageView;
    Pane layer;
    public double x, y, dx, dy, w, h;

	public static final int COVER_WIDTH = 475;
	public static final int COVER_HEIGHT = 600;
    
    public Character(Pane layer,Image image, double x, double y) {

        this.layer = layer;
        this.image = image;
        
        this.x = x;
        this.y = y;
        
        this.dx = 0;
        this.dy = 0;

        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);

        this.w = image.getWidth(); 
        this.h = image.getHeight();
       
        addToLayer();
    }
    
    public Character(Pane layer) {
    		this.layer = layer;
    		
        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);

        this.w = image.getWidth(); 
        this.h = image.getHeight();
       
        addToLayer();  		
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

//    public Pane getLayer() {
//        return layer;
//    }
//
//    public void setLayer(Pane layer) {
//        this.layer = layer;
//    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public ImageView getView() {
        return imageView;
    }

    public void updateUI() {
        imageView.relocate(x, y);
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }

    public boolean collidesWith(Character other) {
        return ( other.x + other.w >= x && other.y + other.h >= y && other.x <= x + w && other.y <= y + h);
    }

	public int compareTo(Character other) {
		return 0;
	}

}