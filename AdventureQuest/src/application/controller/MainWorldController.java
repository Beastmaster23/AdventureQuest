package application.controller;

import java.io.FileNotFoundException;

import application.models.Game;
import application.models.factory.PlayerFactory;
import application.models.player.ErrorBirdEnemy;
import application.models.player.Player;
import application.models.utils.Vector;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;


/**
 * Controls main worrld. Creates a game object. *Important* needs a player
 * @author Interco
 */
public class MainWorldController extends ViewController {
	
	@FXML
	private Canvas canvas_pane;
	private Player player;
	private Game game;
	
	public MainWorldController() {
		try {
			player=PlayerFactory.createPlayer(40, 40, 100, new Vector(20, 20));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double maxSpeed=7, maxAcceleration=.5;
		
		player.setMaxVelocity(new Vector(maxSpeed));
		player.setMaxAcceleration(new Vector(maxAcceleration));
		player.getPhysics().setAcceleration(new Vector(1, 1, 0));
	}
	
	public void init(Object... args) {
		
		StageController.getInstance().getScene().setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			@Override
			public void handle(KeyEvent event) {
				
				player.move(event.getCode());
			}
		});
		StageController.getInstance().getScene().setOnKeyReleased(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				player.move(null);
			}
		});
		game=new Game(canvas_pane, 800, 600);
		ErrorBirdEnemy enemy;
		try {
			enemy = PlayerFactory.createEnemy(32, 60, 100, new Vector(.2));
			enemy.setLayer(1);
			enemy.setMaxVelocity(new Vector(10));
			enemy.setMaxAcceleration(new Vector(.25));
			enemy.getPhysics().setAcceleration(new Vector(.0));
			enemy.getPosition().setX(200);
			enemy.getPosition().setY(200);
			
			game.getGameObjects().add(enemy);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		game.getGameObjects().add(player);
		game.start();
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		
	}
}
