package application.models.player;


/**
 * Represents a player move. 
 * @author Interco
 */
public class Move {
	private String name, description;
	private double damage;
	
	public Move() {
		name="";
		description="";
		damage=-1;
	}
	
	public void actOnPlayer() {
		
	}
	
	public void actOnEnemy() {
		
	}
	
	public static class Builder{
		private String name, description;
		private double damage;
		
		public Builder() {
			name="";
			description="";
			damage=-1;
		}
		
		public Move build() {
			Move move=new Move();
			move.name=name;
			move.description=description;
			move.damage=damage;
			return move;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getDamage() {
			return damage;
		}

		public void setDamage(double damage) {
			this.damage = damage;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	
}
