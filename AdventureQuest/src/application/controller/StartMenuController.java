package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


/**
 * Controls the start menu
 * @author Interco
 */
public class StartMenuController extends ViewController implements Initializable{
	@FXML
    private Button startButton;

	@FXML private ResourceBundle resources;
	@FXML private URL location;
    @FXML private ImageView mainMenuBackground;
    @FXML private ImageView titleImagView;
    @FXML private BorderPane mainMenuBorderPane;
    
    @FXML
    void navigateToMainGame(ActionEvent event) {
    	
    	try {
    		StageController.getInstance().changeScene("LevelOne");
			ViewController controller=((ViewController)StageController.getInstance().getCurrent().getController());
			controller.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public StartMenuController() {
	}

	@Override
	public void init(Object... args) {
		
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		mainMenuBorderPane.styleProperty().set("-fx-background-color: #000000"); // black background
		
		String imageFilePath = "resources/images/main.png";
		File file1 = new File(imageFilePath);
		Image image = new Image(file1.toURI().toString());
		mainMenuBackground.setImage(image);
		
		String titleImageFilePath = "resources/images/logo.png";
		File titleFile = new File(titleImageFilePath);
		Image titleImage = new Image(titleFile.toURI().toString());
		titleImagView.setImage(titleImage);
	}
}
