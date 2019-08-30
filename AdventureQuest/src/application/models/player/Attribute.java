package application.models.player;


/**
 * Represents player stats
 * @author Interco
 */
public class Attribute {
	private double maxCumulative;
	private double speed, elusiveness, strength, attack;
	
	public Attribute() {
		
	}
	
	public static class Builder{
		private double maxCumulative;
		private double speed, elusiveness, strength, attack;
		
		public Builder() {
			this.maxCumulative = 0;
			this.speed = 0;
			this.elusiveness = 0;
			this.strength = 0;
			this.attack = 0;
		}
		public Builder setMaxCumulative(double maxCumulative) {
			this.maxCumulative = maxCumulative;
			return this;
		}
		
		public Builder setSpeed(double speed) {
			this.speed = speed;
			return this;
		}
		
		public Builder setElusiveness(double elusiveness) {
			this.elusiveness = elusiveness;
			return this;
		}
		public Builder setStrength(double strength) {
			this.strength = strength;
			return this;
		}
		public Builder setAttack(double attack) {
			this.attack = attack;
			return this;
		}
		
		public Attribute build() {
			Attribute attr=new Attribute();
			attr.maxCumulative=maxCumulative;
			attr.speed=speed;
			attr.elusiveness=elusiveness;
			attr.strength=strength;
			attr.attack=attack;
			
			return attr;
		}
	}

	public double getMaxCumulative() {
		return maxCumulative;
	}

	public void setMaxCumulative(double maxCumulative) {
		this.maxCumulative = maxCumulative;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getElusiveness() {
		return elusiveness;
	}

	public void setElusiveness(double elusiveness) {
		this.elusiveness = elusiveness;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}
	
	
}
