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
import application.models.player.PlayerData;
import application.models.utils.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SupportSelectionController extends ViewController implements Initializable {

	public SupportSelectionController() {

	}

	@FXML
	private CheckBox elfSelector;

	@FXML
	private CheckBox knightSelector;

	@FXML
	private CheckBox ogreSelector;

	@FXML
	private CheckBox wizardSelector;

	private Alert badSelection;

	private ArrayList<String> support;

	@FXML
	private ImageView backgroundCharacterSelectionImageView;

	@FXML
	private ImageView whiteBackground;
	private Player player=null;
	@FXML
	void makeTeamSelection(ActionEvent event) {
		try {
			int count = 0;
			if (elfSelector.isSelected())
				count++;
			if (knightSelector.isSelected())
				count++;
			if (ogreSelector.isSelected())
				count++;
			if (wizardSelector.isSelected())
				count++;
			if (count != 3) {
				badSelection = new Alert(AlertType.ERROR);
				badSelection.setHeaderText("ILLEGAL CHOICE");
				badSelection.setContentText("You must select exactly three (3) support characters.");
				badSelection.show();
				return;
			}
			support = new ArrayList<String>();
			if (elfSelector.isSelected())
				support.add("Elf");
			if (knightSelector.isSelected())
				support.add("Knight");
			if (ogreSelector.isSelected())
				support.add("Ogre");
			if (wizardSelector.isSelected())
				support.add("Wizard");

			StageController.getInstance().changeScene("LevelOne");
			LevelOneController controller = ((LevelOneController) StageController.getInstance().getCurrent()
					.getController());
			controller.stop();
			controller.setGame(null);
			player.setPartyNames(support);
			
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
		player=(Player) args[0];
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub

	}

}
