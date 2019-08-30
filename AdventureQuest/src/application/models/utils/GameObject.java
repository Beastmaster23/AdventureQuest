package application.models.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * GameObject is the most basic 2D object in the game. It is normally using rectangle physics or collision math.
 * It is compared by it's layer number. 
 * @author Interco
 */
public class GameObject implements Comparable {
	
	private Vector position;
	private Vector dimensions;
	private Collider collider;
	private Physics2D physics;
	private int layer;
	private String tag;
	
	public GameObject() {
		dimensions=new Vector();
		collider=new Collider2D(dimensions, this);
		
		physics=new Physics2D(this);
		position=new Vector();
		collider.setPosition(position);
		layer=0;
		setTag("");
	}
	
	public void draw(GraphicsContext context) {
		Paint prev=context.getFill();
		context.setFill(Color.RED);
		context.fillRect(position.getX(), position.getY(), dimensions.getX(), dimensions.getY());
		context.setFill(prev);
	}
	
	public void update() {
		
	};
	
	/**
	 * Constricts the game objects position to be within the screen. 
	 * 	*Important* It also affects the physics.
	 * @param maxWidth
	 * @param maxHeight
	 */
	public void constrict(double maxWidth, double maxHeight) {
		if(position.getX()+dimensions.getX()>maxWidth) {
			position.setX(maxWidth-dimensions.getX());
			physics.getVelocity().setX(0);
		}
		if(position.getX()<0) {
			position.setX(0);
			physics.getVelocity().setX(0);
		}
		if(position.getY()+dimensions.getY()>maxHeight) {
			position.setY(maxHeight-dimensions.getY());
			physics.getVelocity().setY(0);
		}
		if(position.getY()<0) {
			position.setY(0);
			physics.getVelocity().setY(0);
		}
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}
	public void setCollider(Collider collider) {
		this.collider = collider;
	}

	public Vector getDimensions() {
		return dimensions;
	}

	public Vector getPosition() {
		return position;
	}
	
	public Collider getCollider() {
		return collider;
	}

	public Physics2D getPhysics() {
		return physics;
	}

	@Override
	public int compareTo(Object o) {
		GameObject obj2=(GameObject)o;
		return this.layer-obj2.getLayer();
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
