package application.models.utils;

import java.util.ArrayList;

import application.models.utils.listeners.OnCollisionListener;

/**
 * CoCollider is used to detect collisions between GamoObjects
 * @author Interco
 */
public abstract class Collider {
	private int colliderLayer;
	private Vector position;
	private ArrayList<OnCollisionListener> collisionListeners=new ArrayList<OnCollisionListener>();
	private GameObject parent;
	
	abstract public boolean collide(Collider collider2);
	
	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
	public int getColliderLayer() {
		return colliderLayer;
	}

	public void setColliderLayer(int colliderLayer) {
		this.colliderLayer = colliderLayer;
	}

	public void addCollisionListener(OnCollisionListener collisionListener) {
		this.collisionListeners.add(collisionListener);
	}

	public ArrayList<OnCollisionListener> getCollisionListeners() {
		return collisionListeners;
	}

	public void setCollisionListeners(ArrayList<OnCollisionListener> collisionListeners) {
		this.collisionListeners = collisionListeners;
	}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}
	
	
}
