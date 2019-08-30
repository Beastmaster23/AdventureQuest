package application.models.player;

import application.models.utils.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FollowerPlayer extends Follower {
	/**
	 * @param data the data to set
	 */
	public void setData(PlayerData data) {
		this.data = data;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerSpriteController getSpriteController() {
		return spriteController;
	}

	public void setSpriteController(PlayerSpriteController spriteController) {
		this.spriteController = spriteController;
	}

	public PlayerAnimationSpriteController getAnimationController() {
		return animationController;
	}

	public void setAnimationController(PlayerAnimationSpriteController animationController) {
		this.animationController = animationController;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private Player player;
	private PlayerData data;
	private PlayerSpriteController spriteController;
	private PlayerAnimationSpriteController animationController;
	private boolean running;

	public FollowerPlayer(Player seekedPlayer) {
		player = seekedPlayer;
		spriteController = new PlayerSpriteController();
		animationController = new PlayerAnimationSpriteController();
		setMaxVelocity(7);
		setMaxAcceleration(10);
		running = true;
	}

	public void update() {
		move();

		getPhysics().update();
		// getPhysics().constrict(maxVelocity, maxAcceleration);

		getPosition().addVector(getPhysics().getVelocity());
	}

	public void move() {
		getPhysics().getAcceleration().addVector(seek(player.getPosition()));
		double dir = getPhysics().getVelocity().getDirection() * 180 / Math.PI;

		/*
		 * if (false) { Vector v = getPhysics().getPrevVelocity(); if (v.getX() > 0) {
		 * spriteController.changeSprite(new Vector(1, 0)); }
		 * 
		 * if (v.getX() < 0) { spriteController.changeSprite(new Vector(-1, 0)); } if
		 * (v.getY() < 0) { spriteController.changeSprite(new Vector(0, -1)); }
		 * 
		 * if (v.getY() > 0) { spriteController.changeSprite(new Vector(0, 1)); }
		 * running = false; } else
		 */
		{
			//System.out.println(dir);
			double prevDir = getPhysics().getPrevVelocity().getDirection() * 180 / Math.PI;
			if ((dir >= -45 && dir <= 45) &&!(prevDir >= -45 && prevDir <= 45)) {
				animationController.startSprite(new Vector(1, 0));
			} else if ((dir >= 135 || dir <= -135) &&!(prevDir >= 135 || prevDir <= -135)) {
				animationController.startSprite(new Vector(-1, 0));
			} else if ((dir < -45 && dir > -135) &&!(prevDir < -45 && prevDir > -135)) {
				animationController.startSprite(new Vector(0, -1));
			} else if ((dir > 45 && dir < 135) &&!(prevDir > 45 && prevDir < 135)) {
				animationController.startSprite(new Vector(0, 1));
			}

			running = true;
		}
	}

	public void draw(GraphicsContext context) {
		if (running)
			animationController.getCurrentSprite().draw(context);
		else
			spriteController.getCurrentSprite().draw(context);
	}
	public PlayerData getData() {
		return data;
	}
}
