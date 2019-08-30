package application.models.player;

import application.models.utils.GameObject;
import application.models.utils.Vector;

public abstract class Follower extends GameObject {

	private double maxVelocity, maxAcceleration;
	
	public Vector seek(Vector seekedPosition) {
		Vector desired = Vector.sub(seekedPosition, getPosition());
		desired.setMagnitude(maxVelocity);
		Vector steer = Vector.sub(desired, getPhysics().getVelocity());
		if (steer.getMagnitude() > maxAcceleration) {
			steer.setMagnitude(maxAcceleration);
		}
		return steer;
	}
	public double getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public double getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(double maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}
}
