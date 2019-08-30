package application.controller.level_controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import application.controller.StageController;
import application.controller.ViewController;
import application.models.Game;
import application.models.factory.PlayerFactory;
import application.models.player.FollowerEnemy;
import application.models.player.FollowerPlayer;
import application.models.player.Player;
import application.models.player.PlayerData;
import application.models.utils.Collider;
import application.models.utils.TileMap;
import application.models.utils.Vector;
import application.models.utils.listeners.OnCollisionListener;
import application.viewModel.TextAnimationController;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import application.controller.CombatController;
import application.controller.PartyMenuController;
import application.controller.CombatController;

/**
 * Controls level one. Creates a game object. *Important* needs a player
 * 
 * @author Interco
 */
public class LevelOneController extends ViewController {
	@FXML
	private Canvas canvas;

	@FXML
	private Label storyText;
	

	private Media audio;
	private MediaPlayer mediaPlayer;
	private PartyMenuController partyMenu = null;
	private ArrayList<String> support;
	
	public void setSupport(ArrayList<String> support) {
		this.support = support;
	}

	@FXML
	public void goToMenu() {
		Scene scene=null;
		try {
			game.stop();
			FXMLLoader loader = new FXMLLoader(LevelOneController.class.getResource("/application/views/PartyMenu.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Party Menu");
			if(partyMenu == null) {
				PartyMenuController partyMenu = new PartyMenuController();
				partyMenu.init(player.getParty(), stage, game);
				loader.setController(partyMenu);
				scene = new Scene(loader.load());
			} else {
				partyMenu.update(player.getParty());
			}
			stage.setScene(scene);
			stage.show();
			stage.focusedProperty().addListener((x, y, z)->{
				if(!z?true:false) {
					stage.close();
					game.start();
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Player player;
	private Game game = null;
	private TextAnimationController textAnimation;

	public LevelOneController() {
	}

	@Override
	public void init(Object... args) {
		player=(Player) args[0];
		if (game == null) {
			textAnimation=new TextAnimationController(storyText);
			textAnimation.getLoop().start();
			Task task=new Task() {

				@Override
				protected Object call() throws Exception {
					Thread.sleep(6*1000);
					textAnimation=new TextAnimationController(storyText);
					textAnimation.setString("Oh no! Enemies are approaching!!");
					textAnimation.getLoop().start();
					Thread.sleep(2*1000);
					Vector[] positions = { new Vector(50, 50), new Vector(450, 500), new Vector(200, 400)};
					spawnEnemies(positions);
					return null;
				}
				
			};
			new Thread(task).start();
			startGame();
		} else {
			game.setCanvas_pane(canvas);
			game.start();
		}
		/*
		if(support == null){
			support= new ArrayList<String>();
			support.add((String)args[0]);
			support.add((String)args[1]);
			support.add((String)args[2]);
		}
		*/

		String audioPath = new File("resources/music/GameWorldTheme.wav").getAbsolutePath();
		audio = new Media (new File(audioPath).toURI().toString());
		mediaPlayer = new MediaPlayer(audio);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
		

		StageController.getInstance().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				Vector[] positions = { new Vector(50, 50), new Vector(450, 500), new Vector(200, 400), };
				if (event.getCode().equals(KeyCode.P)){
					try {
						spawnEnemies(positions);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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

	public void startGame() {
		game = new Game(canvas, 800, 600);
		start();
	}

	public void spawnEnemies(Vector[] positions) throws FileNotFoundException {
		for (Vector pos : positions) {
			FollowerEnemy bird = PlayerFactory.createFollowEnemy(32, 60, 200, 2, 5, player);
			bird.setTag("bird");
			bird.getPosition().setX(pos.getX());
			bird.getPosition().setY(pos.getY());
			bird.getCollider().setColliderLayer(5);
			game.getGameObjects().add(bird);
		}
	}

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

	public void start() {
		if(player==null) {
			try {
				player = PlayerFactory.createPlayer( "Logan", 36, 60, 300, new Vector(4, 4));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			player.setData(new PlayerData.Builder().setName("Name").build());
		}
		
		player.getPosition().setX(200);
		player.getPosition().setY(200);
		player.getCollider().setColliderLayer(7);
		player.getCollider().addCollisionListener(new OnCollisionListener() {
			@Override
			public void onCollision(Collider collider) {
				if(collider.getColliderLayer() == 5){
					mediaPlayer.stop();
					game.stop();
				
					try {

						StageController.getInstance().changeScene("CombatLevel");
						CombatController controller = ((CombatController)StageController.getInstance().getCurrent().getController());
						PlayerData data=new PlayerData.Builder().setName("Rando Alien").build();
						
						controller.init(player, data);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					game.getGameObjects().remove(collider.getParent());
				}
			}
		});
		
		
		/***************************************************************************************************/
		// TODO: change the tile background loadup
		TileMap tileMap = TileMap.loadMapByFile("resources/levels/levelOne/tileMaps/backgroundMap.csv");
		tileMap.setCollider(null);
		// TODO: change the tile with collision loadup
		TileMap treeM = TileMap.loadMapByFile("resources/levels/levelOne/tileMaps/treeMap.csv");
		treeM.getCollider().addCollisionListener(new OnCollisionListener() {
		/***************************************************************************************************/
			
			@Override
			public void onCollision(Collider collider) {
				// TODO Auto-generated method stub

				// game.stop();
				// System.out.println("Tree touched");
			}

		});
		
		tileMap.setLayer(-2);
		game.getGameObjects().add(tileMap);
		treeM.setLayer(-1);
		game.getGameObjects().add(treeM);
		TileMap transMap = TileMap.loadMapByFile("resources/levels/levelOne/tileMaps/transition.csv");
		transMap.getCollider().addCollisionListener(new OnCollisionListener() {

			@Override
			public void onCollision(Collider collider) {
					if(collider.getColliderLayer() == 7){
						mediaPlayer.stop();
						game.stop();
					
						try {
							StageController.getInstance().changeScene("BossLevel");
							BossLevelController controller = ((BossLevelController)StageController.getInstance().getCurrent().getController());
							controller.init(player);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}
			});
		game.getGameObjects().add(transMap);
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
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		if(game!=null)
		game.stop();
		
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
