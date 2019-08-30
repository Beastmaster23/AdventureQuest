package application.models.player;

import java.util.ArrayList;

import application.models.AnimationSprite;
import application.models.Sprite;
import application.models.utils.GameObject;
import application.models.utils.PlayerInput;
import application.models.utils.Vector;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Represents and controls the player. Is a 2D rectangle sprite/sprites
 * @author Interco
 */
public class Player extends GameObject {
	private PlayerSpriteController spriteController;
	private PlayerAnimationSpriteController animationController;
	private PlayerInput input;
	private PlayerData data;
	private Vector maxVelocity, maxAcceleration, moveSpeed;
	private ArrayList<FollowerPlayer> party;
	private ArrayList<String> partyNames;
	private boolean running;
	public Player() {
		getDimensions().setX(50);
		getDimensions().setY(50);
		input=new PlayerInput();
		spriteController=new PlayerSpriteController();
		animationController=new PlayerAnimationSpriteController();
		setParty(new ArrayList<FollowerPlayer>());
		setPartyNames(new ArrayList<String>());
		moveSpeed=new Vector();
		running=false;
		data=new PlayerData();
	}
	
	public void update() {
		getPhysics().update();
		if(maxVelocity!=null&&maxAcceleration!=null)
		getPhysics().constrict(maxVelocity, maxAcceleration);
		getPosition().addVector(getPhysics().getVelocity());
	}
	
	public void move(KeyCode code) {
		if(code== input.getRight()) {
			getPhysics().getVelocity().setX(moveSpeed.getX());
			getPhysics().getDirection().setY(0);
			getPhysics().getDirection().setX(-1);
			animationController.startSprite(input.getRight());
			running=true;
		}
		
		if(code== input.getLeft()) {
			getPhysics().getVelocity().setX(-moveSpeed.getX());
			getPhysics().getDirection().setX(1);
			getPhysics().getDirection().setY(0);
			animationController.startSprite(input.getLeft());
			running=true;
		}
		if(code== input.getUp()) {
			getPhysics().getVelocity().setY(-moveSpeed.getY());
			getPhysics().getDirection().setX(0);
			getPhysics().getDirection().setY(1);
			animationController.startSprite(input.getUp());
			running=true;
		}
		
		if(code== input.getDown()) {
			getPhysics().getVelocity().setY(moveSpeed.getY());
			getPhysics().getDirection().setX(0);
			getPhysics().getDirection().setY(-1);
			animationController.startSprite(input.getDown());
			running=true;
		}
		
		if(code== null) {
			Vector v=getPhysics().getVelocity();
			getPhysics().getDirection().setX(0);
			getPhysics().getDirection().setY(0);
			if(v.getX()>0) {
				spriteController.changeSprite(input.getRight());
			}
			
			if(v.getX()<0) {
				spriteController.changeSprite(input.getLeft());
			}
			if(v.getY()<0) {
				spriteController.changeSprite(input.getUp());
			}
			
			if(v.getY()>0) {
				spriteController.changeSprite(input.getDown());
			}
			getPhysics().getVelocity().setX(0);
			getPhysics().getVelocity().setY(0);
			running=false;
		}
	}
	
	public void draw(GraphicsContext context) {		
		if(running)
		animationController.getCurrentSprite().draw(context);
		else
		spriteController.getCurrentSprite().draw(context);
	}

	public static class Builder{
		private PlayerSpriteController spriteController;
		private PlayerAnimationSpriteController animationController;
		private PlayerInput input;
		private PlayerData data;
		private Vector maxVelocity, maxAcceleration, moveSpeed, dimensions;
		
		private boolean running;
		public Builder() {
			
		}
		
		public Builder hasSpriteController(PlayerSpriteController spriteController) {
			this.spriteController=spriteController;
			return this;
		}
		
		public Builder hasPlayerAnimationSpriteController(PlayerAnimationSpriteController animationController) {
			this.animationController=animationController;
			return this;
		}
		
		public Builder hasMaxAcceleration(Vector maxAcceleration) {
			this.maxAcceleration=maxAcceleration;
			return this;
		}
		
		public Builder hasMoveSpeed(Vector moveSpeed) {
			this.moveSpeed=moveSpeed;
			return this;
		}
		
		public Builder hasMaxVelocity(Vector maxVelocity) {
			this.maxVelocity=maxVelocity;
			return this;
		}
		
		public Builder hasInput(PlayerInput input) {
			this.input=input;
			return this;
		}

		public Builder hasData(PlayerData data) {
			this.data = data;
			return this;
		}
		public Player build() {
			Player player=new Player();
			
			player.spriteController=spriteController;
			player.animationController=animationController;
			player.input=input;
			player.maxVelocity=maxVelocity;
			player.maxAcceleration=maxAcceleration;
			player.moveSpeed=moveSpeed;
			player.data=data;
			if(dimensions!=null) {
				player.getDimensions().setX(dimensions.getX());
				player.getDimensions().setY(dimensions.getY());
			}
			
			return player;
		}

		public Vector getDimensions() {
			return dimensions;
		}

		public Builder setDimensions(Vector dimensions) {
			this.dimensions = dimensions;
			return this;
		}
	}
	public Vector getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(Vector moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public PlayerAnimationSpriteController getAnimationController() {
		return animationController;
	}

	public void setAnimationController(PlayerAnimationSpriteController animationController) {
		this.animationController = animationController;
	}

	public Vector getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(Vector maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public Vector getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(Vector maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public PlayerSpriteController getSpriteController() {
		return spriteController;
	}

	public void setSpriteController(PlayerSpriteController spriteController) {
		this.spriteController = spriteController;
	}

	public PlayerInput getInput() {
		return input;
	}

	public void setInput(PlayerInput input) {
		this.input = input;
	}
	
	public PlayerData getData() {
		return data;
	}

	public void setData(PlayerData data) {
		this.data = data;
	}

	public ArrayList<FollowerPlayer> getParty() {
		return party;
	}

	public void setParty(ArrayList<FollowerPlayer> party) {
		this.party = party;
	}

	public ArrayList<String> getPartyNames() {
		return partyNames;
	}

	public void setPartyNames(ArrayList<String> partyNames) {
		this.partyNames = partyNames;
	}
}
