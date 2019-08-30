package application.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.models.Game;
import application.models.player.Attribute;
import application.models.player.FollowerPlayer;
import application.models.player.PlayerData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PartyMenuController implements Initializable {

	@FXML
    private Label playerTwoAttack;

    @FXML
    private Label playerOneStrength;

    @FXML
    private Button returnToMenuButton;

    @FXML
    private Label playerThreeAttack;

    @FXML
    private Label playerThreeStrength;

    @FXML
    private Label playerTwoElusiveness;

    @FXML
    private ProgressBar playerOneHealthBar;

    @FXML
    private Label playerTwoName;

    @FXML
    private Label playerTwoStrength;

    @FXML
    private Button optionsButton;

    @FXML
    private Label playerTwoSpeed;

    @FXML
    private Label playerThreeMaxHP;

    @FXML
    private Label playerThreeSpeed;

    @FXML
    private Label playerTwoMaxHP;

    @FXML
    private ProgressBar playerTwoHealthBar;

    @FXML
    private Button continueButton;

    @FXML
    private Label playerOneName;

    @FXML
    private ProgressBar playerThreeHealthBar;

    @FXML
    private Label playerTwoCurrentHP;

    @FXML
    private Label playerThreeElusiveness;

    @FXML
    private Label playerOneSpeed;

    @FXML
    private Label playerThreeCurrentHP;

    @FXML
    private Label playerOneElusiveness;

    @FXML
    private Label playerThreeName;

    @FXML
    private Label playerOneCurrentHP;

    @FXML
    private Button itemsButton;

    @FXML
    private Label playerOneAttack;

    @FXML
    private Label playerOneMaxHP;

    @FXML
    private ImageView playerOneImage;
    
    @FXML 
    private ImageView playerTwoImage;
    
    @FXML
    private ImageView playerThreeImage;
    
    @FXML
    void returnToMenu(ActionEvent event) {
    	game.stop();
    	try {
			StageController.getInstance().changeScene("StartMenu");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@FXML
	void continueGame(ActionEvent event) {		
		stage.close();
		game.start();
	}
	
	@FXML
	void openInventory(ActionEvent event) {
		/*
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/Inventory.fxml"));
			ItemsController itemsController = new ItemsController(stage, pOneData, game);
			loader.setController(itemsController);

			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		*/
	}
	
	
	
	private Stage stage;
	private ArrayList<FollowerPlayer> party;
	private Game game;
	
	
	public void init(ArrayList<FollowerPlayer> party, Stage stage, Game game) {
		this.game = game;
		this.party = party;
		this.stage = stage;
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		optionsButton.setVisible(false);
		itemsButton.setVisible(false);
		
		setPlayerOneData(party.get(0).getData());
		setPlayerTwoData(party.get(1).getData());
		setPlayerThreeData(party.get(2).getData());
		
		Image image = new Image(party.get(0).getData().getPath());
		playerOneImage.setImage(image);
		image = new Image(party.get(1).getData().getPath());
		playerTwoImage.setImage(image);
		image = new Image(party.get(2).getData().getPath());
		playerThreeImage.setImage(image);
		}
	
	public void update(ArrayList<FollowerPlayer> supports) {
		setPlayerOneData(supports.get(0).getData());
		setPlayerTwoData(supports.get(1).getData());
		setPlayerThreeData(supports.get(2).getData());
		Image image = new Image(party.get(0).getData().getPath());
		playerOneImage.setImage(image);
		image = new Image(party.get(1).getData().getPath());
		playerTwoImage.setImage(image);
		image = new Image(party.get(2).getData().getPath());
		playerThreeImage.setImage(image);
		}
	
	
	
	private void setPlayerOneData(PlayerData data) {
		playerOneHealthBar.setProgress(100 /100);
		playerOneName.setText(data.getName());
		playerOneCurrentHP.setText(Integer.toString((int)data.getHealth()));
		playerOneMaxHP.setText(Integer.toString((int)data.getMaxHealth()));
		//playerOneAttack.setText(Integer.toString((int)data.getAttributes().getAttack()));
		//playerOneSpeed.setText(Integer.toString((int)data.getAttributes().getSpeed()));
		//playerOneElusiveness.setText(Integer.toString((int)data.getAttributes().getElusiveness()));
		//playerOneStrength.setText(Integer.toString((int)data.getAttributes().getStrength()));
		

	}
	private void setPlayerTwoData(PlayerData data) {
		playerTwoHealthBar.setProgress(100 /100);
		playerTwoName.setText(data.getName());
		playerTwoCurrentHP.setText(Integer.toString((int)100));
		playerTwoMaxHP.setText(Integer.toString((int)100));
		//playerTwoAttack.setText(Integer.toString((int)data.getAttributes().getAttack()));
		//playerTwoSpeed.setText(Integer.toString((int)data.getAttributes().getSpeed()));
		//playerTwoElusiveness.setText(Integer.toString((int)data.getAttributes().getElusiveness()));
		//playerTwoStrength.setText(Integer.toString((int)data.getAttributes().getStrength()));
		
		
	}
	private void setPlayerThreeData(PlayerData data) {
		playerThreeHealthBar.setProgress(data.getHealth() /100);
		playerThreeName.setText(data.getName());
		playerThreeCurrentHP.setText(Integer.toString((int)100));
		playerThreeMaxHP.setText(Integer.toString((int)100));
		//playerThreeAttack.setText(Integer.toString((int)data.getAttributes().getAttack()));
		//playerThreeSpeed.setText(Integer.toString((int)data.getAttributes().getSpeed()));
		//playerThreeElusiveness.setText(Integer.toString((int)data.getAttributes().getElusiveness()));
		//playerThreeStrength.setText(Integer.toString((int)data.getAttributes().getStrength()));
		
	}
	
}


