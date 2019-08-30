package application.controller;

import java.io.IOException;

import application.controller.level_controllers.LevelOneController;
import application.models.Game;
import application.models.Inventory;
import application.models.Item;
import application.models.player.Attribute;
import application.models.player.PlayerData;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class ItemsController {
	@FXML
    private MenuButton itemFour;

    @FXML
    private MenuButton itemOne;

    @FXML
    private MenuItem equipToPlayerOne;

    @FXML
    private MenuItem equipToPlayerTwo;

    @FXML
    private MenuButton itemTwo;

    @FXML
    private MenuButton itemThree;

    @FXML
    private MenuButton itemFive;

    @FXML
    private MenuButton itemSix;

    @FXML
    private Button doneButton;

    @FXML
    private MenuItem equipToPlayerThree;
    
    @FXML
    void equipItemOne(ActionEvent event) {
    	if(inventory.checkIfItemEquipped(inventory.getItem(0))) {
    		unequipItemStats(inventory.getItem(0), inventory.equipMap.get(inventory.getItem(0)));
    		inventory.removeMapKey(inventory.getItem(0));
    	}
    	
    	MenuItem source = (MenuItem)event.getSource();
    	switch(source.getId()) {
    	case "equipToPlayerOne":
    		//playerOneData.equipItem(inventory.getItem(0));
    		inventory.equipMap.put(inventory.getItem(0), playerOneData);
    		itemOne.setText("Player One");
    		break;
    	case "equipToPlayerTwo":
    		itemOne.setText("Player Two");
    		break;
    	case "equipToPlayerThree":
    		itemOne.setText("Player Three");
    		break;
    	case "unequipItemOne":
        	itemOne.setText("Equip To:");
    		break;
    			
    	}
    }
    
    @FXML
    void equipItemTwo(ActionEvent event) {
    	if(inventory.checkIfItemEquipped(inventory.getItem(1))) {
    		unequipItemStats(inventory.getItem(1), inventory.equipMap.get(inventory.getItem(1)));
    		inventory.removeMapKey(inventory.getItem(1));
    	}
    	
    	MenuItem source = (MenuItem)event.getSource();
    	switch(source.getId()) {
    	case "equipToPlayerOne":
    		//playerOneData.equipItem(inventory.getItem(1));
    		inventory.equipMap.put(inventory.getItem(1), playerOneData);
    		itemTwo.setText("Player One");
    		break;
    	case "equipToPlayerTwo":
    		itemTwo.setText("Player Two");
    		break;
    	case "equipToPlayerThree":
    		itemTwo.setText("Player Three");
    		break;
    	case "unequipItemTwo":
        	itemTwo.setText("Equip To:");
    		break;
    			
    	}
    }
    @FXML
    void equipItemThree(ActionEvent event) {
    	if(inventory.checkIfItemEquipped(inventory.getItem(2))) {
    		unequipItemStats(inventory.getItem(2), inventory.equipMap.get(inventory.getItem(2)));
    		inventory.removeMapKey(inventory.getItem(2));
    	}
    	
    	MenuItem source = (MenuItem)event.getSource();
    	switch(source.getId()) {
    	case "equipToPlayerOne":
    		//playerOneData.equipItem(inventory.getItem(2));
    		inventory.equipMap.put(inventory.getItem(2), playerOneData);
    		itemThree.setText("Player One");
    		break;
    	case "equipToPlayerTwo":
    		itemThree.setText("Player Two");
    		break;
    	case "equipToPlayerThree":
    		itemThree.setText("Player Three");
    		break;
    	case "unequipItemThree":
        	itemThree.setText("Equip To:");
    		break;
    			
    	}
    }
    @FXML
    void equipItemFour(ActionEvent event) {
    	if(inventory.checkIfItemEquipped(inventory.getItem(3))) {
    		unequipItemStats(inventory.getItem(3), inventory.equipMap.get(inventory.getItem(3)));
    		inventory.removeMapKey(inventory.getItem(3));
    	}
    	
    	MenuItem source = (MenuItem)event.getSource();
    	switch(source.getId()) {
    	case "equipToPlayerOne":
    		//playerOneData.equipItem(inventory.getItem(3));
    		inventory.equipMap.put(inventory.getItem(3), playerOneData);
    		itemFour.setText("Player One");
    		break;
    	case "equipToPlayerTwo":
    		itemFour.setText("Player Two");
    		break;
    	case "equipToPlayerThree":
    		itemFour.setText("Player Three");
    		break;
    	case "unequipItemFour":
        	itemFour.setText("Equip To:");
    		break;
    			
    	}
    }
    @FXML
    void equipItemFive(ActionEvent event) {
    	if(inventory.checkIfItemEquipped(inventory.getItem(4))) {
    		unequipItemStats(inventory.getItem(4), inventory.equipMap.get(inventory.getItem(4)));
    		inventory.removeMapKey(inventory.getItem(4));
    	}
    	
    	MenuItem source = (MenuItem)event.getSource();
    	switch(source.getId()) {
    	case "equipToPlayerOne":
    		//playerOneData.equipItem(inventory.getItem(4));
    		inventory.equipMap.put(inventory.getItem(4), playerOneData);
    		itemFive.setText("Player One");
    		break;
    	case "equipToPlayerTwo":
    		itemFive.setText("Player Two");
    		break;
    	case "equipToPlayerThree":
    		itemFive.setText("Player Three");
    		break;
    	case "unequipItemFive":
    		itemFive.setText("Equip To:");
    		break;
    			
    	}
    }
    
    @FXML
    void equipItemSix(ActionEvent event) {
    	if(inventory.checkIfItemEquipped(inventory.getItem(5))) {
    		unequipItemStats(inventory.getItem(5), inventory.equipMap.get(inventory.getItem(5)));
    		inventory.removeMapKey(inventory.getItem(5));
    	}
    	
    	MenuItem source = (MenuItem)event.getSource();
    	switch(source.getId()) {
    	case "equipToPlayerOne":
    		//playerOneData.equipItem(inventory.getItem(5));
    		inventory.equipMap.put(inventory.getItem(5), playerOneData);
    		itemSix.setText("Player One");
    		break;
    	case "equipToPlayerTwo":
    		itemSix.setText("Player Two");
    		break;
    	case "equipToPlayerThree":
    		itemSix.setText("Player Three");
    		break;
    	case "unequipItemSix":
        	itemSix.setText("Equip To:");
    		break;
    			
    	}
    }
    
    
    @FXML
    void done(ActionEvent event) {
		/*try {
			FXMLLoader loader = new FXMLLoader(LevelOneController.class.getResource("/application/views/PartyMenu.fxml"));
	    	PartyMenuController PartyMenu = new PartyMenuController();
			PartyMenu.init(playerOneData, stage, game);
			loader.setController(PartyMenu);
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
    
    

	private Stage stage;
    private PlayerData playerOneData;
    private Game game;
    
    private Inventory inventory = new Inventory();
    
    public ItemsController(Stage stage, PlayerData playerOneData, Game game) {
    	this.stage = stage;
    	this.playerOneData = playerOneData;
    	this.game = game;
    }
    
    
    public void unequipItemStats(Item item, PlayerData data) {
    		//data.unequipItem(item);
    }
    
    
    

}
