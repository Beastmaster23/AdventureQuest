package application.models.player;

import java.util.HashMap;

import application.models.AnimationSprite;
import application.models.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;


/**
 * Controls player visuals, but animated. 
 * @author Interco
 */
public class PlayerAnimationSpriteController<Type> {
	private AnimationSprite currentSprite;
	private Type currentCode;
	private HashMap<Type, AnimationSprite> sprites;
	
	public PlayerAnimationSpriteController() {
		sprites=new HashMap<Type, AnimationSprite>();
		currentSprite=null;
		currentCode=null;
	}
	
	public void addSprite(Type code, AnimationSprite sprite) {
		if(sprites.isEmpty()) {
			currentSprite=sprite;
		}
		sprites.put(code, sprite);
	}
	
	public void changeSprite(Type code) {
		if(!sprites.isEmpty()) {
			
			currentSprite=sprites.get(code);
			currentCode=code;
		}
	}
	
	public void startSprite(Type code) {
		if(currentCode==null||code!=currentCode) {
			currentSprite.stop();
			changeSprite(code);
			currentSprite.start();
		}
		
	}
	public void stop() {
		if(currentCode!=null)
		currentSprite.stop();
	}
	public AnimationSprite getSprite(Type code) {
		return sprites.get(code);
	}
	
	public AnimationSprite getCurrentSprite() {
		return currentSprite;
	}
	
	
}
