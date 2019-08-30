package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.models.player.Player;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameOverController extends ViewController implements Initializable {
	@FXML
    private Label winText;
	@FXML
    private Pane gameOverPane;
	@FXML
	private ImageView backgroundCharacterSelectionImageView;

	@FXML
	private ImageView whiteBackground;
	
	private Player player;
	private String winState;
    @FXML
    void goToStartMenu(ActionEvent event) {
    	try {
    		StageController.getInstance().changeScene("StartMenu");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public GameOverController() {
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String imagePath3 = "resources/images/bossFightBackground800x700.png";
		File imageFile3 = new File(imagePath3);
		Image image3 = new Image(imageFile3.toURI().toString());
		backgroundCharacterSelectionImageView.setImage(image3);
		
	}
	@Override
	public void init(Object... args) {
		// TODO Auto-generated method stub
		player=(Player) args[0];
		winState=(String) args[1];
		winText.setText(winState);
		if(winState.equals("You got an A+!"))
			winText.setTextFill(Color.GREENYELLOW);
		Image image = new Image(player.getData().getPath());
		whiteBackground.setImage(image);
		if(winState.equals("Game Over"))
		whiteBackground.setRotate(-90);
	}
	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		
	}
}
