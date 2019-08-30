package application.controller.level_controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import application.controller.StageController;
import application.controller.ViewController;
import application.models.Game;
import application.models.factory.PlayerFactory;
import application.models.player.FollowerPlayer;
import application.models.player.Player;
import application.models.player.PlayerData;
import application.models.utils.Collider;
import application.models.utils.TileMap;
import application.models.utils.Vector;
import application.models.utils.listeners.OnCollisionListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import kbo256_controllers.CutScene01Controller;
import kbo256_controllers.CutScene02Controller;

public class BossLevelController extends ViewController {

	@FXML
	private Canvas canvas;

	@FXML
	private Label storyText;

	@FXML
	void goToMenu(ActionEvent event) {
		if (game.isRunning())
			game.stop();
		else
			game.start();
	};

	private Player player;
	private Game game = null;
	public void spawnParty(ArrayList<String> support, Vector[] positions) throws FileNotFoundException {
		for(int i=0;i<support.size();i++) {
			FollowerPlayer bird = PlayerFactory.createFollowerPlayer(support.get(i), 32, 48, 200, 2, 5, player);
			
			bird.setTag("party");
			bird.getPosition().setX(positions[i].getX());
			bird.getPosition().setY(positions[i].getY());
			player.getParty().add(bird);
			game.getGameObjects().add(bird);
		}
		
	}
	public BossLevelController() {

	}

	public void startGame() {
		game = new Game(canvas, 800, 600);
		start();
	}

	public void start() {

		// TODO update boss level background : flames
		TileMap tileMap = TileMap.loadMapByFile("resources/levels/bossLevel/tileMaps/backgroundMap.csv");
		tileMap.setCollider(null);

		// TODO add more colliding objects
		TileMap otherColliderObjects = TileMap
				.loadMapByFile("resources/levels/bossLevel/tileMaps/collidingObjects.csv");

		// TODO add more colliding objects
		TileMap treeM = TileMap.loadMapByFile("resources/levels/bossLevel/tileMaps/castle.csv");
		treeM.getCollider().addCollisionListener(new OnCollisionListener() {

			@Override
			public void onCollision(Collider collider) {
				if (collider.getColliderLayer() == 7) {
					game.stop();

					try {
						StageController.getInstance().changeScene("Cutscene02");
						CutScene02Controller controller = ((CutScene02Controller) StageController.getInstance()
								.getCurrent().getController());
						PlayerData data = new PlayerData.Builder().setName("King Prof. Rebinson").build();

						controller.init(player, data);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		tileMap.setLayer(-2);
		game.getGameObjects().add(tileMap);
		otherColliderObjects.setLayer(-1);
		treeM.setLayer(-1);
		game.getGameObjects().add(treeM);
		game.getGameObjects().add(otherColliderObjects);
		game.getGameObjects().add(player);
		Vector[] positions = { new Vector(250, 150), new Vector(350, 150), new Vector(200, 100)};
		try {
			spawnParty(player.getPartyNames(), positions);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		game.start();

	}

	@Override
	public void init(Object... args) {
		// TODO Auto-generated method stub
		player = (Player) args[0];
		
		if (game == null) {
			startGame();
		} else {
			// resume game
			game.setCanvas_pane(canvas);
			game.start();
		}

		StageController.getInstance().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				player.move(event.getCode());
			}
		});
		StageController.getInstance().getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				player.move(null);
			}
		});
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		game.stop();
	}

}
