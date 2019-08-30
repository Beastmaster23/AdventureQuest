package application.models.player;

import java.util.Random;

import application.models.AnimationSprite;
import application.models.Sprite;
import application.models.utils.PlayerInput;
import application.models.utils.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;


/**
 * This enemy moves randomly.
 * @author Interco
 */
public class ErrorBirdEnemy extends Player{
	private Random brain;
	
	private Timeline dispatcher;
	private KeyCode current;
	private int prevDir;
	public ErrorBirdEnemy() {
		getDimensions().setX(50);
		getDimensions().setY(50);
		brain=new Random();
		dispatcher=new Timeline();
		dispatcher.setCycleCount(Timeline.INDEFINITE);
		prevDir=0;
		dispatcher.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				int dir=brain.nextInt(5);
				while(dir==prevDir) {
					dir=brain.nextInt(5);
				}
				prevDir=dir;
				KeyCode code=null;
				switch(dir) {
				case 0:
					code=getInput().getDown();
					getPhysics().getVelocity().setX(0);
					break;
				case 1:
					code=getInput().getUp();
					getPhysics().getVelocity().setX(0);
					break;
				case 2:
					code=getInput().getLeft();
					getPhysics().getVelocity().setY(0);
					break;
				case 3:
					code=getInput().getRight();
					getPhysics().getVelocity().setY(0);
					break;
				case 4:
					code=null;
					break;
				}
				setCurrent(code);
				move(code);
			}
		}));
		dispatcher.play();
	}

	public KeyCode getCurrent() {
		return current;
	}



	public void setCurrent(KeyCode current) {
		this.current = current;
	}
}
