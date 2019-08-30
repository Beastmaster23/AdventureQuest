package application.controller;

import java.io.IOException;
import java.util.HashMap;

import application.models.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * Controls the app by only one scene.
 * @author edn12
 *
 */
public class SceneController {
	private Scene currentScene;
	
	private HashMap<String, Level> scenes;
	public SceneController() {
		currentScene=null;
		scenes=new HashMap<String, Level>();
	}
	
	public void addLevel(String name, Level level) throws IOException {
		if(isValidKey(name))
		scenes.put(name, level);
		if(currentScene==null) {
			FXMLLoader loader=new FXMLLoader();
			loader.setController(level.getController());
			loader.setLocation(getClass().getResource(level.getPath()));
			Parent parent = loader.load();
			currentScene=new Scene(parent);
		}
	}
	
	public Scene getScene() {
		return currentScene;
	}
	
	public void changeScene(String name) throws IOException {
		if(scenes.containsKey(name)) {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource(scenes.get(name).getPath()));
			//MainWorldController controller=new MainWorldController(this);
			//loader.setController(controller);
			Parent parent = loader.load();
			if(currentScene==null) {
				currentScene=new Scene(parent);
			}
			currentScene.setRoot(parent);
		}
	}
	
	public boolean isValidKey(String name) {
		return !scenes.containsKey(name);
	}
}
