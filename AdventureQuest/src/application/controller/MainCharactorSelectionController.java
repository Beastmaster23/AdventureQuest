package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.controller.level_controllers.LevelOneController;
import application.models.factory.PlayerFactory;
import application.models.player.Player;
import application.models.utils.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainCharactorSelectionController extends ViewController implements Initializable{

	public MainCharactorSelectionController() {

	}

	@FXML
    private CheckBox davidSelector;

    @FXML
    private CheckBox ericSelector;

    @FXML
    private CheckBox gerrySelector;

    @FXML
    private CheckBox cesarSelector;
    
    @FXML
    private CheckBox loganSelector;

	private Alert badSelection;

	private ArrayList<String> support;

	@FXML
	private ImageView backgroundCharacterSelectionImageView;

	@FXML
	private ImageView whiteBackground;
	
	@FXML
	void makeTeamSelection(ActionEvent event) {
		try {
			int count = 0;
			if (davidSelector.isSelected()) {
				ericSelector.setSelected(false);
				gerrySelector.setSelected(false);
				cesarSelector.setSelected(false);
				loganSelector.setSelected(false);
			}
			if (ericSelector.isSelected()) {
				davidSelector.setSelected(false);
				gerrySelector.setSelected(false);
				cesarSelector.setSelected(false);
				loganSelector.setSelected(false);
			}
			if (gerrySelector.isSelected()) {
				davidSelector.setSelected(false);
				ericSelector.setSelected(false);
				cesarSelector.setSelected(false);
				loganSelector.setSelected(false);
			}
			if (cesarSelector.isSelected()) {
				davidSelector.setSelected(false);
				ericSelector.setSelected(false);
				gerrySelector.setSelected(false);
				loganSelector.setSelected(false);
			}
			if (loganSelector.isSelected()) {
				davidSelector.setSelected(false);
				ericSelector.setSelected(false);
				gerrySelector.setSelected(false);
				cesarSelector.setSelected(false);
			}

			StageController.getInstance().changeScene("CharacterSelection");
			SupportSelectionController controller = ((SupportSelectionController) StageController.getInstance().getCurrent()
					.getController());
			Player player = null;
			try {
				if(cesarSelector.isSelected()){
					player = PlayerFactory.createPlayer("Cesar", 36, 60, 300, new Vector(4, 4));
				}
				else if(loganSelector.isSelected()){
					player = PlayerFactory.createPlayer("Logan", 36, 60, 300, new Vector(4, 4));
				}
				else if(gerrySelector.isSelected()){
					player = PlayerFactory.createPlayer("Gerry", 36, 60, 300, new Vector(4, 4));
				}
				else if(davidSelector.isSelected()){
					player = PlayerFactory.createPlayer("David", 36, 60, 300, new Vector(4, 4));
				}else {
					player = PlayerFactory.createPlayer(50, 50, 300, new Vector(4, 4));
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			controller.init(player);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void goToMainMenu(ActionEvent event) {
		try {
			StageController.getInstance().changeScene("StartMenu");
			ViewController controller = ((ViewController) StageController.getInstance().getCurrent().getController());
			controller.init();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String imagePath3 = "resources/images/bossFightBackground800x700.png";
		File imageFile3 = new File(imagePath3);
		Image image3 = new Image(imageFile3.toURI().toString());
		backgroundCharacterSelectionImageView.setImage(image3);

		String imagePath = "resources/images/whiteBackground.png";
		File imageFile = new File(imagePath);
		Image image = new Image(imageFile.toURI().toString());
		whiteBackground.setImage(image);

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
