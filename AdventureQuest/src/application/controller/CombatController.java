package application.controller;
 


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import application.controller.level_controllers.LevelOneController;
import application.models.player.Player;
import application.models.player.PlayerData;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import kbo256_controllers.MainMenuController;

public class CombatController extends ViewController implements Initializable{

    //@FXML
    //private Button abilitiesButton;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView enemyImage;
    @FXML
    private Label playerName;
    @FXML
    private Button attackButton;
    @FXML
    private Label enemyName;
    @FXML
    private TextArea infoBox;
    //@FXML
    //private Button itemsButton;
    @FXML
    private Button fleeButton;
    @FXML
    private Label playerHPLabel;
    @FXML
    private Label enemyHPLabel;
    @FXML
    private ProgressBar enemyHealthBar;
    @FXML
    private ProgressBar playerHealthBar;
    
    @FXML
    private ImageView backgroundImageView;

    @FXML
    private URL location;
    
    private static PlayerData playerInfo = null;
    private static PlayerData enemyInfo = null;
    private boolean inputReady;
    private Player player=null;
    //
    private Media audio;
	private MediaPlayer mediaPlayer;
    //
	@FXML
	void attackButtonPressed(ActionEvent event) throws InterruptedException {
    	if(attackButton.getText().equals("Attack"))
    	{
    		attackButton.setText("Next");
    		//abilitiesButton.setText("");
    		//itemsButton.setText("");
    		fleeButton.setText("");
    		int damage = playerInfo.attack();
    		infoBox.setText(playerInfo.getName() + " attacks " + enemyInfo.getName() + " for " + damage + " damage!");
    		enemyHitAnimation();
    		enemyInfo.setHealth(enemyInfo.getHealth() - damage);
    		enemyHPLabel.setText(enemyInfo.getHealth() + "/" + enemyInfo.getMaxHealth());
    		enemyHealthBar.setProgress(enemyInfo.getHealth() / enemyInfo.getMaxHealth());
    		if(enemyInfo.getHealth() <= 0){
    			victorySequence();
    			return;
    		}
    		if(playerInfo.getHealth() <= 0){
    			losingSequence();
    			return;
    		}
    	}
    	else if(attackButton.getText().equals("Next")){
    		enemyTurn();
    	}
    	else if(attackButton.getText().equals("Ok")){
    		attackButton.setText("Attack");
    		fleeButton.setText("Flee");
    		//itemsButton.setText("Items");
    		//abilitiesButton.setText("Abilities");
    		try {
    			 mediaPlayer.stop();
    			 if(infoBox.getText().contentEquals("You Lose...")) {
    				 StageController.getInstance().changeScene("GameOver");
    				 ViewController controller=(StageController.getInstance().getCurrent().getController());
    				 controller.init(player, "Game Over");
    			 }else {
    				 if(enemyInfo.getName()=="King Prof. Rebinson") {
    					 StageController.getInstance().changeScene("GameOver");
        				 ViewController controller=(StageController.getInstance().getCurrent().getController());
        				 controller.init(player, "You got an A+!");
    				 }else {
    					 StageController.getInstance().changeScene("LevelOne");
        				 ViewController controller=(StageController.getInstance().getCurrent().getController());
        				 controller.init(player);
    				 }
    				 
    			 }
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
    	}
	}

	private void losingSequence() {
		infoBox.setText("You Lose...");
		attackButton.setText("Ok");
		playerImage.setRotate(-90);
		playerName.setText("Corpse");
		
	}

	@FXML
	void abilitiesButtonPressed(ActionEvent event) {
		infoBox.setText("You pressed the Abilities Button.");
	}

	@FXML
	void itemsButtonPressed(ActionEvent event) {
		infoBox.setText("You pressed the Items Button.");
	}

	@FXML
	void fleeButtonPressed(ActionEvent event) {
		if(fleeButton.getText().equals("Flee")){
			try {
				 mediaPlayer.stop();
				 if(enemyInfo.getName()=="King Prof. Rebinson") {
					 StageController.getInstance().changeScene("GameOver");
					 ViewController controller=((ViewController)StageController.getInstance().getCurrent().getController());
				     //controller.setGame(null);
				     controller.init(player, "Game Over");
				 }else {
					 StageController.getInstance().changeScene("LevelOne");
					 ViewController controller=((ViewController)StageController.getInstance().getCurrent().getController());
				     //controller.setGame(null);
				     controller.init(player);
				 }
				 
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		
		
	}
	
	private void enemyTurn() {
		
		int damage = enemyInfo.attack();
		if(enemyInfo.getName()=="King Prof. Rebinson")damage/=2;
		infoBox.setText(enemyInfo.getName() + " attacks " + playerInfo.getName() + " for " + damage + " damage!");
		playerHitAnimation();
		playerInfo.setHealth(playerInfo.getHealth() - damage);
		playerHPLabel.setText(playerInfo.getHealth() + "/" + playerInfo.getMaxHealth());
		playerHealthBar.setProgress(playerInfo.getHealth() / playerInfo.getMaxHealth());
		attackButton.setText("Attack");
		//abilitiesButton.setText("Abilities");
		//itemsButton.setText("Items");
		fleeButton.setText("Flee");
		
		
	}
	
	private void enemyHitAnimation(){
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(250));
		transition.setToX(enemyImage.getX() + 10);
		transition.setToY(enemyImage.getY());
		transition.setAutoReverse(true);
		transition.setCycleCount(2);
		transition.setNode(enemyImage);
		transition.play();
	}
	
	private void playerHitAnimation(){
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(250));
		transition.setToX(playerImage.getX() - 10);
		transition.setToY(playerImage.getY());
		transition.setAutoReverse(true);
		transition.setCycleCount(2);
		transition.setNode(playerImage);
		transition.play();
	}
	
	
	private void victorySequence(){
		infoBox.setText("You win!");
		attackButton.setText("Ok");
		enemyImage.setRotate(90);
		enemyName.setText("Corpse");
	}

	@Override
	public void init(Object... args) {
		player=((Player)args[0]);
		this.playerInfo = player.getData();
		this.enemyInfo = (PlayerData)args[1];
		if(enemyInfo.getName()=="King Prof. Rebinson") {
			//
			String audioPath = new File("resources/music/BossTheme.wav").getAbsolutePath();
			audio = new Media (new File(audioPath).toURI().toString());
			mediaPlayer = new MediaPlayer(audio);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
			//
			enemyInfo.setAttackValue(15);
			enemyInfo.setHealth(500);
			enemyInfo.setMaxHealth(500);
			enemyInfo.setPath("file:resources/images/professorKing.png");
			
			String imageFilePath = "resources/images/bossFightBackground.png";
			File file1 = new File(imageFilePath);
			Image image = new Image(file1.toURI().toString());
			backgroundImageView.setImage(image);
		}else {
			//
			String audioPath = new File("resources/music/BattleTheme.wav").getAbsolutePath();
			audio = new Media (new File(audioPath).toURI().toString());
			mediaPlayer = new MediaPlayer(audio);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
			//
			enemyInfo.setAttackValue(10);
			enemyInfo.setName("Rando Alien");
			enemyInfo.setHealth(35);
			enemyInfo.setMaxHealth(35);
			enemyInfo.setPath("file:resources/enemies/errorBird/standing/Enemy_standing_left.png");
			
			String imageFilePath2 = "resources/images/backgroundBattleSceneLevelOne.png";
			File file2 = new File(imageFilePath2);
			Image image2 = new Image(file2.toURI().toString());
			backgroundImageView.setImage(image2);
		}
		/*
		enemyInfo = new PlayerData();
		enemyInfo.setAttackValue(10);
		enemyInfo.setName("Skelletron");
		enemyInfo.setHealth(35);
		enemyInfo.setMaxHealth(35);*/
		Image enemyPicture = new Image(enemyInfo.getPath());
		enemyImage.setImage(enemyPicture);
		enemyName.setText(enemyInfo.getName());
		enemyHPLabel.setText(enemyInfo.getHealth() + "/" + enemyInfo.getMaxHealth());
		enemyHealthBar.setProgress(1);
		/*
		playerInfo = new PlayerData();
		playerInfo.setAttackValue(10);
		playerInfo.setName("Leeroy Brown");
		playerInfo.setHealth(100);
		playerInfo.setMaxHealth(100);
		*/
		Image playerPicture = new Image(player.getData().getPath());
		playerImage.setImage(playerPicture);
		playerName.setText(playerInfo.getName());
		playerHPLabel.setText(playerInfo.getHealth() + "/" + playerInfo.getMaxHealth());
		playerHealthBar.setProgress(playerInfo.getHealth()/playerInfo.getMaxHealth());
	}

	@Override
	public void stop(Object... args) {
		// TODO Auto-generated method stub
		
	}
	
}
