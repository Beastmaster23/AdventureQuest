package application.models;

import application.models.utils.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * Represents an image on screen
 * @author Interco
 */
public class Sprite {
	private Image sprite;
	private Vector position;
	
	public Sprite(String spritePath) {
		sprite=new Image(spritePath);
		position=new Vector();
	}
	
	public Sprite(Image spriteImage) {
		sprite=spriteImage;
		position=new Vector();
	}
	
	public Sprite(String spritePath, Vector spritePosition) {
		sprite=new Image(spritePath);
		position=spritePosition;
	}
	
	public Sprite(Image spriteImage, Vector spritePosition) {
		sprite=spriteImage;
		position=spritePosition;
	}
	
	public void draw(GraphicsContext context) {
		
		context.drawImage(sprite, position.getX(), position.getY());
	}
	
	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
}
