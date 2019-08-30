package kbo256_controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.StageController;
import application.controller.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * kbo_256 main menu with cut scene. Only used once on the first click of play button.
 * @author kbo256
 *
 */
public class MainMenuController extends ViewController implements Initializable {
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ImageView mainMenuBackground;
    @FXML private ImageView titleImagView;
    
    public MainMenuController() {
    	
    }
    
    
	@FXML
    void onClickPlayButton(ActionEvent event) {
		try {
			StageController.getInstance().changeScene("Cutscene");
			ViewController controller=((ViewController)StageController.getInstance().getCurrent().getController());
			controller.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		String imageFilePath = "resources/images/main.png";
		File file1 = new File(imageFilePath);
		Image image = new Image(file1.toURI().toString());
		mainMenuBackground.setImage(image);
		
		String titleImageFilePath = "resources/images/logo.png";
		File titleFile = new File(titleImageFilePath);
		Image titleImage = new Image(titleFile.toURI().toString());
		titleImagView.setImage(titleImage);
		
		// set image view to full screen.Causes WARNING but its okay.
		/*
		DoubleProperty width = mainMenuBackground.fitWidthProperty();
		DoubleProperty height = mainMenuBackground.fitHeightProperty();
		width.bind(Bindings.selectDouble(mainMenuBackground.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mainMenuBackground.sceneProperty(), "height"));
		*/
		
	}

	@Override
	public void init(Object... args) {
		
		
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		
	}
	
}
