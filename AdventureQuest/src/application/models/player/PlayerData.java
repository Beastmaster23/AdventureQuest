package application.models.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;


/**
 * Holds the data of player
 * @author Interco
 */
public class PlayerData {
	private String name;
	private List<Move> moves;
	private List<Attribute> attributes;
	private int attackValue;
	private double health;
	private double maxHealth = 0;
	Random rand = new Random();
	private String path ;
	
	public PlayerData() {
		name="";
		moves=null;
		attributes=null;
		health=-1;
		setPath("");
	}
	
	public static class Builder{
		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public List<Move> getMoves() {
			return moves;
		}

		public Builder setMoves(List<Move> moves) {
			this.moves = moves;
			return this;
		}

		public List<Attribute> getAttributes() {
			return attributes;
		}

		public Builder setAttributes( List<Attribute> attributes) {
			this.attributes = attributes;
			return this;
		}

		public double getCurrentHealth() {
			return currentHealth;
		}

		public Builder setHealth(double health) {
			this.currentHealth = health;
			this.maxHealth = health;
			return this;
		}

		private String name;
		private List<Move> moves;
		//private List<Attribute> attributes;
		private List<Attribute> attributes;
		private double currentHealth;
		private double maxHealth;
		
		public Builder(String name, List<Move> moves, List<Attribute> attributes, double maxHealth, double currentHealth) {
			this.name = name;
			this.moves = moves;
			this.attributes = attributes;
			this.maxHealth = maxHealth;
			this.currentHealth = currentHealth;
		}
		
		public Builder() {
		}
		
		public PlayerData build() {
			PlayerData data=new PlayerData();
			data.name=name;
			data.moves=moves;
			data.attributes=attributes;
			data.health=currentHealth;
			data.maxHealth = maxHealth;
			
			return data;
		}
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
	
	public int attack(){
		int damage = this.rand.nextInt(this.attackValue);
		return damage;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setAttackValue(int i) {
		attackValue = i;
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
