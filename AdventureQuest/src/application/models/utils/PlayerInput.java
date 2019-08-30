package application.models.utils;

import javafx.scene.input.KeyCode;


/**
 * PlayerInput is used to hold keycodes on the keyboard
 * @author Interco
 */
public class PlayerInput {
	private KeyCode left, right, up, down;
	
	public PlayerInput() {
		setLeft(KeyCode.LEFT);
		setRight(KeyCode.RIGHT);
		setUp(KeyCode.UP);
		setDown(KeyCode.DOWN);
	}

	public KeyCode getLeft() {
		return left;
	}

	public void setLeft(KeyCode left) {
		this.left = left;
	}

	public KeyCode getRight() {
		return right;
	}

	public void setRight(KeyCode right) {
		this.right = right;
	}

	public KeyCode getUp() {
		return up;
	}

	public void setUp(KeyCode up) {
		this.up = up;
	}

	public KeyCode getDown() {
		return down;
	}

	public void setDown(KeyCode down) {
		this.down = down;
	}
}
