package application.models;

import java.io.IOException;

import application.controller.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


/**
 * Holds the material to build a scene. 
 * @author Interco
 */
public class Level {
	private String name, path;
	private ViewController controller;
	public Level(String levelName) {
		setName(levelName);
		setPath("");
		setController(null);
	}

	public Level(String levelName, String viewPath, ViewController viewController) {
		setName(levelName);
		setPath(viewPath);
		setController(viewController);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ViewController getController() {
		return controller;
	}

	public void setController(ViewController controller) {
		this.controller = controller;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public static class Builder{
		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public String getPath() {
			return path;
		}

		public Builder setPath(String path) {
			this.path = path;
			return this;
		}

		public ViewController getController() {
			return controller;
		}

		public Builder setController(ViewController controller) {
			this.controller = controller;
			return this;
		}

		private String name, path;
		private ViewController controller;
		
		public Builder() {
			
		}
		
		public Level build() {
			Level level = new Level(name);
			level.setPath(path);
			level.setController(controller);
			return level;
		}
	}
}
