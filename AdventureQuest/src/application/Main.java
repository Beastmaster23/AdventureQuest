package application;


import java.io.FileNotFoundException;

import application.controller.CombatController;
import application.controller.GameOverController;
import application.controller.PartyMenuController;
import application.controller.MainCharactorSelectionController;
import application.controller.StageController;
import application.controller.SupportSelectionController;
import application.controller.level_controllers.BossLevelController;
import application.controller.level_controllers.LevelOneController;
import application.models.Level;
import application.models.factory.PlayerFactory;
import application.models.player.Player;
import application.models.player.PlayerData;
import application.models.utils.Collider;
import application.models.utils.Vector;
import application.models.utils.listeners.OnCollisionListener;
import javafx.application.Application;
import javafx.stage.Stage;
import kbo256_controllers.CutScene01Controller;
import kbo256_controllers.CutScene02Controller;
import kbo256_controllers.MainMenuController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StageController sceneController= StageController.getInstance(primaryStage);
			Level level=new Level.Builder().setName("StartMenu")
					.setPath("/kbo256_views/mainMenu.fxml")
					.setController(new MainMenuController())
					.build();
			sceneController.addLevel("StartMenu", level);
		
			level=new Level.Builder().setName("Cutscene")
					.setPath("/kbo256_views/cutScene01.fxml")
					.setController(new CutScene01Controller())
					.build();
			sceneController.addLevel("Cutscene", level);
			
			//TODO @Logan
			
			level=new Level.Builder().setName("Cutscene02")
					.setPath("/kbo256_views/cutScene2.fxml")
					.setController(new CutScene02Controller())
					.build();
			sceneController.addLevel("Cutscene02", level);
			
			
			level=new Level.Builder().setName("CharacterSelection")
			    .setPath("/application/views/SupportSelection.fxml")
			    	.setController(new SupportSelectionController())
			    	.build();
			sceneController.addLevel("CharacterSelection", level);
			level=new Level.Builder().setName("MainCharactorSelection")
				    .setPath("/application/views/MainCharacterSelection.fxml")
				    	.setController(new MainCharactorSelectionController())
				    	.build();
				sceneController.addLevel("MainCharactorSelection", level);
			level=new Level.Builder().setName("LevelOne")
					.setPath("/application/views/levels/LevelOne.fxml")
					.setController(new LevelOneController())
					.build();
			sceneController.addLevel("LevelOne", level);
			
			level=new Level.Builder().setName("BossLevel")
					.setPath("/application/views/levels/BossLevel.fxml")
					.setController(new BossLevelController())
					.build();
			sceneController.addLevel("BossLevel", level);
			level = new Level.Builder().setName("CombatLevel")
					.setPath("/application/views/combatScene.fxml")
					.setController(new CombatController())
					.build();
			sceneController.addLevel("CombatLevel", level);
			
			Player player=null;
			try {
				player = PlayerFactory.createPlayer(36, 56, 300, new Vector(4, 4));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			player.getPosition().setX(100);
			player.getPosition().setY(100);
			player.getCollider().addCollisionListener(new OnCollisionListener() {
				@Override
				public void onCollision(Collider collider) {
				}
			});
			
			level=new Level.Builder().setName("GameOver")
					.setPath("/application/views/GameOver.fxml")
					.setController(new GameOverController())
					.build();
			sceneController.addLevel("GameOver", level);
			player.getCollider().setColliderLayer(7);
			sceneController.changeScene("StartMenu");
			
			sceneController.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			sceneController.getCurrent().getController().init(player, "You got an A+!");
			
			primaryStage.setTitle("Adventure Quest");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
