package application.models;

import java.util.ArrayList;
import java.util.HashMap;
import application.models.player.Attribute;
import application.models.player.PlayerData;

public class Inventory {
	private Item[] sackOfItems = new Item[6];
    public HashMap<Item, PlayerData> equipMap = new HashMap<>();

	
	public Inventory() {
		this.sackOfItems[0] = new Item("First Item", new Attribute.Builder()
				.setAttack(6)
				.setElusiveness(10)
				.setMaxCumulative(1)
				.setStrength(1)
				.setSpeed(5).build()); 
		this.sackOfItems[1] = new Item("Second Item", new Attribute.Builder()
				.setAttack(5)
				.setElusiveness(14)
				.setMaxCumulative(4)
				.setStrength(9)
				.setSpeed(3).build());
		this.sackOfItems[2] = new Item("Third Item", new Attribute.Builder()
				.setAttack(9)
				.setElusiveness(30)
				.setMaxCumulative(1)
				.setStrength(1)
				.setSpeed(7).build());
		this.sackOfItems[3] = new Item("Fourth Item", new Attribute.Builder()
				.setAttack(2)
				.setElusiveness(30)
				.setMaxCumulative(9)
				.setStrength(3)
				.setSpeed(4).build());
		this.sackOfItems[4] = new Item("Fifth Item", new Attribute.Builder()
				.setAttack(6)
				.setElusiveness(10)
				.setMaxCumulative(1)
				.setStrength(1)
				.setSpeed(5).build());
		this.sackOfItems[5] = new Item("Sixth Item", new Attribute.Builder()
				.setAttack(.2)
				.setElusiveness(30)
				.setMaxCumulative(1)
				.setStrength(8)
				.setSpeed(3).build());
		
	}
	
	public Item getItem(int index) {
		return sackOfItems[index];
	}
	
	public boolean checkIfItemEquipped(Item item) {
		for(Item key : equipMap.keySet()) {
			if(key.getName().equals(item.getName())) {
				return true;
			}
		}
    	return false;
    }

	public void removeMapKey(Item item) {
		equipMap.remove(item);
	}
}
