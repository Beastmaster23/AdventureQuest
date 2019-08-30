package kbo256_controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.StageController;
import application.controller.ViewController;
import application.controller.level_controllers.BossLevelController;
import application.models.player.Player;
import application.models.player.PlayerData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class CutScene02Controller extends ViewController implements Initializable{

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MediaView mediaView2;

    @FXML
    private BorderPane cutScene2BorderPane;
    private MediaPlayer mediaPlayer2;
	private Media media2;
	private Player player;
	private PlayerData data;
	
    @FXML
    void onClickNextButton(ActionEvent event) {
    	mediaPlayer2.stop();
    	
    	changeToCombat();
    }
	public CutScene02Controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cutScene2BorderPane.styleProperty().set("-fx-background-color: #000000"); // black background
		
		String videoPath = new File("resources/cutscenes/Cutscene02.mp4").getAbsolutePath();
		media2 = new Media(new File(videoPath).toURI().toString());
		mediaPlayer2 = new MediaPlayer(media2);
		mediaView2.setMediaPlayer(mediaPlayer2);
		mediaPlayer2.setAutoPlay(true); // play video
		mediaPlayer2.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				changeToCombat();
			}
			
		});
	}
	
	public void changeToCombat() {
		try {
    		StageController.getInstance().changeScene("CombatLevel");
			ViewController controller=((ViewController)StageController.getInstance().getCurrent().getController());
			controller.init(player, data);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(Object... args) {
		// TODO Auto-generated method stub
		player=(Player) args[0];
		data=(PlayerData) args[1];
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		
	}

}
