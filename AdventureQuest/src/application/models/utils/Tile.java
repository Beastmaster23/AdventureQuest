package application.models.utils;

import application.models.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Tile extends GameObject {
	
	private Sprite sprite;
	private int id;
	public Tile() {
		id=-1;
		getDimensions().setX(50);
		getDimensions().setY(50);
	}
	
	public void draw(GraphicsContext ctx) {
		
		ctx.drawImage(sprite.getSprite(), getPosition().getX(), getPosition().getY());
		
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
