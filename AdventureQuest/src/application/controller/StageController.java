package application.controller;

import java.io.IOException;
import java.util.HashMap;
import application.models.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Controls the stage.
 * @author Interco
 */
public class StageController {
	private Stage stage;
	private Scene currentScene;
	private HashMap<String, Level> scenes;
	private Level current;
	private static StageController myInstance =null;
	
	public StageController(Stage primaryStage) {
		current=null;
		scenes=new HashMap<String, Level>();
		stage=primaryStage;
		stage.setScene(new Scene(new BorderPane()));
	}
	/**
	 * Only returns instance
	 * @return
	 */
	public static StageController getInstance() {
		return myInstance;
	}
	/**
	 * Returns and initializes if needed. *Important* Is thread safe
	 * @param primaryStage Stage to control.
	 * @return
	 */
	public static StageController getInstance(Stage primaryStage) {
		if(myInstance==null) {
			synchronized (StageController.class) {
				if(myInstance==null) {
					myInstance=new StageController(primaryStage);
				}
			}
		}
		return myInstance;
	}
	public void addLevel(String name, Level level){
		scenes.put(name, level);
	}
	
	public void changeScene(String name) throws IOException {
		if(current!=null)
		current.getController().stop();
		FXMLLoader loader=new FXMLLoader();
		loader.setController(scenes.get(name).getController());
		loader.setLocation(getClass().getResource(scenes.get(name).getPath()));
		Scene scene=new Scene(loader.load());
		current=scenes.get(name);
		stage.setScene(scene);
	}
	
	public boolean isValidKey(String name) {
		return !scenes.containsKey(name);
	}
	
	public HashMap<String, Level> getScenes() {
		return scenes;
	}

	public void setScenes(HashMap<String, Level> scenes) {
		this.scenes = scenes;
	}

	public Level getCurrent() {
		return current;
	}
	
	public Scene getScene() {
		return stage.getScene();
	}
}
