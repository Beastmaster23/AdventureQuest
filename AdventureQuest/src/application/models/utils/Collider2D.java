package application.models.utils;

import application.models.utils.listeners.OnCollisionListener;

/**
 * Collider2D uses rectangle math to compute collisions.
 * @author Interco
 */
public class Collider2D extends Collider {

	private Vector dimensions;
	
	public Collider2D(Vector colliderDimensions) {
		dimensions=colliderDimensions;
		setParent(null);
	}
	public Collider2D(Vector colliderDimensions, GameObject parent) {
		dimensions=colliderDimensions;
		setParent(parent);
	}
	/**
	 * Checks if a collision occurred between 2 colliders and handles positioning...
	 * Naive approach
	 * @param collider2 is the second collider
	 * @return boolean
	 */
	@Override
	public boolean collide(Collider collider2) {

		if(collider2==null)
			return false;
		
		if(collider2.getClass()!=this.getClass()) {
			return false;
		}
		
		Collider2D object2=(Collider2D) collider2;
		double widthPos=getPosition().getX()+dimensions.getX(), heightPos=getPosition().getY()+dimensions.getY();
		double width2Pos=object2.getPosition().getX()+object2.getDimensions().getX(), height2Pos=object2.getPosition().getY()+object2.getDimensions().getY();
		
		if(widthPos>object2.getPosition().getX()&&getPosition().getX()<width2Pos) {
			if(heightPos>object2.getPosition().getY()&&getPosition().getY()<height2Pos) {
				object2.getPosition().subVector(object2.getParent().getPhysics().getPrevVelocity());
				for(OnCollisionListener listener: getCollisionListeners()) {
					listener.onCollision(collider2);
				}
				return true;
			}
		}
		return false;
	}
	
	
	
	//TODO("Get a better algorithm");
	public boolean collided(Collider collider2) {
		Collider2D object2=(Collider2D) collider2;
		boolean collided=collide(collider2);
		double halfWidth=dimensions.getX()/2;
		double halfHeight=dimensions.getY()/2;
		
		if(collided) {
			
		}
		return collided;
	}
	
	public Vector getDimensions() {
		return dimensions;
	}
	public void setDimensions(Vector dimensions) {
		this.dimensions=dimensions;
	}
}