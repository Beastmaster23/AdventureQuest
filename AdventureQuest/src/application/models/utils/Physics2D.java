package application.models.utils;

/**
 * Physics2D is specialized in 2D forces.
 * @author edn12
 */
public class Physics2D {
	
	private Vector velocity, acceleration;
	private GameObject parent;
	private Vector direction;
	private Vector prevVelocity, prevAcceleration, prevDirection;
	public Physics2D(GameObject parent) {
		velocity=new Vector();
		acceleration=new Vector();
		this.parent=parent;
		direction=new Vector();
		prevVelocity=new Vector();
		prevAcceleration=new Vector();
		prevDirection=new Vector();
	}
	/**
	 * Updates and calculates forces
	 */
	public void update() {
		prevVelocity.setX(velocity.getX());
		prevVelocity.setY(velocity.getY());
		velocity.addVector(acceleration);
		prevAcceleration.setX(acceleration.getX());
		prevAcceleration.setY(acceleration.getY());
		prevDirection.setX(this.direction.getX());
		prevDirection.setY(this.direction.getY());
		acceleration.multVector(0);
	}
	/**
	 * Constrict physics values by the arguments
	 * @param maxVelocity 
	 * @param maxAcceleration
	 */
	public void constrict(Vector maxVelocity, Vector maxAcceleration) {
		if(maxVelocity!=null)
		velocity.constrict(maxVelocity);
		if(maxAcceleration!=null)
		acceleration.constrict(maxAcceleration);
	}
	
	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public Vector getAcceleration() {
		return acceleration;
	}
	
	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
	public GameObject getParent() {
		return parent;
	}
	public void setParent(GameObject parent) {
		this.parent = parent;
	}
	public Vector getDirection() {
		return direction;
	}
	public void setDirection(Vector direction) {
		
		this.direction = direction;
		
	}
	public Vector getPrevVelocity() {
		return prevVelocity;
	}
	public void setPrevVelocity(Vector prevVelocity) {
		this.prevVelocity = prevVelocity;
	}
	public Vector getPrevAcceleration() {
		return prevAcceleration;
	}
	public void setPrevAcceleration(Vector prevAcceleration) {
		this.prevAcceleration = prevAcceleration;
	}
	public Vector getPrevDirection() {
		return prevDirection;
	}
	public void setPrevDirection(Vector prevDirection) {
		this.prevDirection = prevDirection;
	}
}
