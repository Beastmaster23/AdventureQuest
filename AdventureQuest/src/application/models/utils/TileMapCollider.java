package application.models.utils;

import application.models.utils.listeners.OnCollisionListener;

public class TileMapCollider extends Collider {

	public TileMapCollider(GameObject parent) {
		setParent(parent);
	}
	
	@Override
	public boolean collide(Collider collider2) {
		if(collider2==null)
			return false;
		if(collider2.getClass()==this.getClass())
			return false;
		
		TileMap map=(TileMap) this.getParent();
		for (int i = 0; i < map.getRows(); i++) {
			for (int j = 0; j < map.getColoumns(); j++) {
				if(map.getTiles()[i][j]==null)continue;
				
				if(map.getTiles()[i][j].getCollider().collide(collider2)) {
					for(OnCollisionListener listener: getCollisionListeners()) {
						listener.onCollision(collider2);
					}
					return true;
				}
			}
		}
		return false;
	}

}
