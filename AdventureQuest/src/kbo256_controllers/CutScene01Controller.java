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
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class CutScene01Controller extends ViewController implements Initializable {

	@FXML private ResourceBundle resources;
	@FXML private URL location;
	@FXML private MediaView mediaView;

	@FXML private BorderPane cutSceneBorderPane;
	private MediaPlayer mediaPlayer;
	private Media media;

	public CutScene01Controller(){
	}
	
	/**
	 * Go to level after cut scene. using eric's switch view implementation
	 * @param event
	 * 		clicking skip button action
	 */
	@FXML void onClickSkipButton(ActionEvent event) {
		mediaPlayer.stop();
		try {
    		StageController.getInstance().changeScene("MainCharactorSelection");
			ViewController controller=((ViewController)StageController.getInstance().getCurrent().getController());
			controller.init();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cutSceneBorderPane.styleProperty().set("-fx-background-color: #000000"); // black background
		
		String videoPath = new File("resources/cutscenes/cs3443Cutscene_01.mp4").getAbsolutePath();
		media = new Media(new File(videoPath).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setAutoPlay(true); // play video
		mediaPlayer.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				try {
		    		StageController.getInstance().changeScene("MainCharactorSelection");
					ViewController controller=((ViewController)StageController.getInstance().getCurrent().getController());
					controller.init();	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		// sets cut scene to full screen. Causes WARNING but its okay.
		/*
		DoubleProperty width = mediaView.fitWidthProperty();
		DoubleProperty height = mediaView.fitHeightProperty();
		width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
		*/
		
	}

	@Override
	public void init(Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		
	}


}
