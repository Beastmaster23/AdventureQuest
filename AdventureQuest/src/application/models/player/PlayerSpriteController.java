package application.models.player;

import java.util.HashMap;

import application.models.Sprite;
import application.models.utils.PlayerInput;
import javafx.scene.input.KeyCode;


/**
 * Controls Visuals of player based on keycode
 * 
 * @author Interco
 */
public class PlayerSpriteController<Type> {
	private Sprite currentSprite;
	private HashMap<Type, Sprite> sprites;
	
	public PlayerSpriteController() {
		sprites=new HashMap<Type, Sprite>();
		currentSprite=null;
	}
	
	public void addSprite(Type code, Sprite sprite) {
		if(sprites.isEmpty()) {
			currentSprite=sprite;
		}
		sprites.put(code, sprite);
	}
	
	public void changeSprite(Type code) {
		if(!sprites.isEmpty()) {
			currentSprite=sprites.get(code);
		}
	}
	
	public Sprite getSprite(Type code) {
		return sprites.get(code);
	}
	
	public Sprite getCurrentSprite() {
		return currentSprite;
	}
}
