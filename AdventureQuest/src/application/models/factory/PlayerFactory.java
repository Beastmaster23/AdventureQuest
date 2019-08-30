package application.models.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.models.AnimationSprite;
import application.models.Sprite;
import application.models.player.ErrorBirdEnemy;
import application.models.player.FollowerEnemy;
import application.models.player.FollowerPlayer;
import application.models.player.Player;
import application.models.player.PlayerAnimationSpriteController;
import application.models.player.PlayerData;
import application.models.player.PlayerSpriteController;
import application.models.utils.PlayerInput;
import application.models.utils.Vector;
import javafx.scene.image.Image;


/**
 * Holds default methods to create players/enemies
 * @author Interco
 */
public class PlayerFactory {
	
	public static FollowerEnemy createFollowEnemy(double width, double height, int animationSpeed, double maxVelocity, double maxAcceleration, Player player) throws FileNotFoundException {
		FollowerEnemy enemy=new FollowerEnemy(player);
		enemy.getDimensions().setY(height);
		enemy.getDimensions().setX(width);
		Vector position=enemy.getPosition();
		enemy.setMaxVelocity(maxVelocity);
		enemy.setMaxAcceleration(maxAcceleration);
		AnimationSprite walkUp=new AnimationSprite(position, animationSpeed);
		walkUp.setDimensions(enemy.getDimensions());
		walkUp.load("resources/enemies/errorBird/walking/forward");
		AnimationSprite walkLeft=new AnimationSprite(position, animationSpeed);
		walkLeft.setDimensions(enemy.getDimensions());
		walkLeft.load("resources/enemies/errorBird/walking/left");
		AnimationSprite walkRight=new AnimationSprite(position, animationSpeed);
		walkRight.setDimensions(enemy.getDimensions());
		walkRight.load("resources/enemies/errorBird/walking/right");
		AnimationSprite walkDown=new AnimationSprite(position, animationSpeed);
		walkDown.setDimensions(enemy.getDimensions());
		walkDown.load("resources/enemies/errorBird/walking/back");
		
		enemy.getAnimationController().addSprite(new Vector(-1, 0), walkLeft);
		enemy.getAnimationController().addSprite(new Vector(1, 0), walkRight);
		enemy.getAnimationController().addSprite(new Vector(0, -1), walkUp);
		enemy.getAnimationController().addSprite(new Vector(0, 1), walkDown);
		FileInputStream finUp=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_forward.png");
		FileInputStream finDown=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_back.png");
		FileInputStream finLeft=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_left.png");
		FileInputStream finRight=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_right.png");
		Image upImg=new Image(finUp, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Image downImg=new Image(finDown, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Image leftImg=new Image(finLeft, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Image rightImg=new Image(finRight, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Sprite sprite=new Sprite(upImg, position);
		Sprite spriteLeft=new Sprite(leftImg, position);
		Sprite spriteDown=new Sprite(downImg, position);
		Sprite spriteRight=new Sprite(rightImg, position);
		enemy.getSpriteController().addSprite(new Vector(0, 1), spriteDown);
		enemy.getSpriteController().addSprite(new Vector(-1, 0), spriteLeft);
		enemy.getSpriteController().addSprite(new Vector(1, 0), spriteRight);
		enemy.getSpriteController().addSprite(new Vector(0, -1), sprite);
		return enemy;
	}
	public static FollowerPlayer createFollowerPlayer(String playerName, double width, double height, int animationSpeed, double maxVelocity, double maxAcceleration, Player player) throws FileNotFoundException {
		FollowerPlayer followerPlayer=new FollowerPlayer(player);
		followerPlayer.getDimensions().setY(height);
		followerPlayer.getDimensions().setX(width);
		Vector position=followerPlayer.getPosition();
		followerPlayer.setMaxVelocity(maxVelocity);
		followerPlayer.setMaxAcceleration(maxAcceleration);
		PlayerData data=new PlayerData.Builder().setName(playerName)
				.setHealth(100)
				.build();
		data.setMaxHealth(100);
		data.setPath("file:resources/players/"+playerName+"/standing/"+playerName+"_standing_forward.png");
		followerPlayer.setData(data);
		AnimationSprite walkUp=new AnimationSprite(position, animationSpeed);
		walkUp.setDimensions(followerPlayer.getDimensions());
		walkUp.load("resources/players/"+playerName+"/walking/back");
		AnimationSprite walkLeft=new AnimationSprite(position, animationSpeed);
		walkLeft.setDimensions(followerPlayer.getDimensions());
		walkLeft.load("resources/players/"+playerName+"/walking/left");
		AnimationSprite walkRight=new AnimationSprite(position, animationSpeed);
		walkRight.setDimensions(followerPlayer.getDimensions());
		walkRight.load("resources/players/"+playerName+"/walking/right");
		AnimationSprite walkDown=new AnimationSprite(position, animationSpeed);
		walkDown.setDimensions(followerPlayer.getDimensions());
		walkDown.load("resources/players/"+playerName+"/walking/forward");
		
		followerPlayer.getAnimationController().addSprite(new Vector(-1, 0), walkLeft);
		followerPlayer.getAnimationController().addSprite(new Vector(1, 0), walkRight);
		followerPlayer.getAnimationController().addSprite(new Vector(0, -1), walkUp);
		followerPlayer.getAnimationController().addSprite(new Vector(0, 1), walkDown);
		FileInputStream finUp=new FileInputStream("resources/players/"+playerName+"/standing/"+playerName+"_standing_forward.png");
		FileInputStream finDown=new FileInputStream("resources/players/"+playerName+"/standing/"+playerName+"_standing_back.png");
		FileInputStream finLeft=new FileInputStream("resources/players/"+playerName+"/standing/"+playerName+"_standing_left.png");
		FileInputStream finRight=new FileInputStream("resources/players/"+playerName+"/standing/"+playerName+"_standing_right.png");
		Image upImg=new Image(finUp, followerPlayer.getDimensions().getX(), followerPlayer.getDimensions().getY(), false, false);
		Image downImg=new Image(finDown, followerPlayer.getDimensions().getX(), followerPlayer.getDimensions().getY(), false, false);
		Image leftImg=new Image(finLeft, followerPlayer.getDimensions().getX(), followerPlayer.getDimensions().getY(), false, false);
		Image rightImg=new Image(finRight, followerPlayer.getDimensions().getX(), followerPlayer.getDimensions().getY(), false, false);
		Sprite sprite=new Sprite(upImg, position);
		Sprite spriteLeft=new Sprite(leftImg, position);
		Sprite spriteDown=new Sprite(downImg, position);
		Sprite spriteRight=new Sprite(rightImg, position);
		followerPlayer.getSpriteController().addSprite(new Vector(0, 1), spriteDown);
		followerPlayer.getSpriteController().addSprite(new Vector(-1, 0), spriteLeft);
		followerPlayer.getSpriteController().addSprite(new Vector(1, 0), spriteRight);
		followerPlayer.getSpriteController().addSprite(new Vector(0, -1), sprite);
		return followerPlayer;
	}
	public static ErrorBirdEnemy createEnemy(double width, double height, int animationSpeed, Vector moveVector) throws FileNotFoundException {
		ErrorBirdEnemy enemy=new ErrorBirdEnemy();
		enemy.getDimensions().setY(height);
		enemy.getDimensions().setX(width);
		Vector position=enemy.getPosition();
		enemy.setMoveSpeed(moveVector);
		AnimationSprite walkUp=new AnimationSprite(position, animationSpeed);
		walkUp.setDimensions(enemy.getDimensions());
		walkUp.load("resources/enemies/errorBird/walking/forward");
		AnimationSprite walkLeft=new AnimationSprite(position, animationSpeed);
		walkLeft.setDimensions(enemy.getDimensions());
		walkLeft.load("resources/enemies/errorBird/walking/left");
		AnimationSprite walkRight=new AnimationSprite(position, animationSpeed);
		walkRight.setDimensions(enemy.getDimensions());
		walkRight.load("resources/enemies/errorBird/walking/right");
		AnimationSprite walkDown=new AnimationSprite(position, animationSpeed);
		walkRight.setDimensions(enemy.getDimensions());
		walkDown.load("resources/enemies/errorBird/walking/back");
		
		enemy.getAnimationController().addSprite(enemy.getInput().getLeft(), walkLeft);
		enemy.getAnimationController().addSprite(enemy.getInput().getRight(), walkRight);
		enemy.getAnimationController().addSprite(enemy.getInput().getUp(), walkUp);
		enemy.getAnimationController().addSprite(enemy.getInput().getDown(), walkDown);
		FileInputStream fin=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_forward.png");
		FileInputStream fin2=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_back.png");
		FileInputStream fin3=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_left.png");
		FileInputStream fin4=new FileInputStream("resources/enemies/errorBird/standing/Enemy_standing_right.png");
		Image upImg=new Image(fin, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Image downImg=new Image(fin2, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Image leftImg=new Image(fin3, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Image rightImg=new Image(fin4, enemy.getDimensions().getX(), enemy.getDimensions().getY(), false, false);
		Sprite sprite=new Sprite(upImg, position);
		Sprite spriteLeft=new Sprite(leftImg, position);
		Sprite spriteDown=new Sprite(downImg, position);
		Sprite spriteRight=new Sprite(rightImg, position);
		enemy.getSpriteController().addSprite(enemy.getInput().getDown(), spriteDown);
		enemy.getSpriteController().addSprite(enemy.getInput().getLeft(), spriteLeft);
		enemy.getSpriteController().addSprite(enemy.getInput().getRight(), spriteRight);
		enemy.getSpriteController().addSprite(enemy.getInput().getUp(), sprite);
		return enemy;
	}
	
	public static Player createPlayer(double width, double height, int animationSpeed, Vector moveVector) throws FileNotFoundException {
		Player player=new Player.Builder()
				.hasInput(new PlayerInput())
				.hasMaxAcceleration(new Vector(0.5, 0.5))
				.hasMaxVelocity(new Vector(5, 5))
				.hasMoveSpeed(moveVector)
				.hasPlayerAnimationSpriteController(new PlayerAnimationSpriteController())
				.hasSpriteController(new PlayerSpriteController())
				.build();
		
		PlayerData data = new PlayerData.Builder().setName("Eric").setHealth(150).build();
		data.setMaxHealth(150);
		data.setAttackValue(23);
		data.setPath("file:resources/players/eric/wheelchair/Me_right.png");
		player.setData(data);
		Vector position=player.getPosition();
		
		AnimationSprite walkUp=new AnimationSprite(position, animationSpeed);
		walkUp.setDimensions(player.getDimensions());
		walkUp.load("resources/players/eric/wheelchair/rolling/back");
		AnimationSprite walkLeft=new AnimationSprite(position, animationSpeed);
		walkLeft.setDimensions(player.getDimensions());
		walkLeft.load("resources/players/eric/wheelchair/rolling/left");
		AnimationSprite walkRight=new AnimationSprite(position, animationSpeed);
		walkRight.setDimensions(player.getDimensions());
		walkRight.load("resources/players/eric/wheelchair/rolling/right");
		AnimationSprite walkDown=new AnimationSprite(position, animationSpeed);
		walkDown.setDimensions(player.getDimensions());
		walkDown.load("resources/players/eric/wheelchair/rolling/forward");
		
		player.getAnimationController().addSprite(player.getInput().getLeft(), walkLeft);
		player.getAnimationController().addSprite(player.getInput().getRight(), walkRight);
		player.getAnimationController().addSprite(player.getInput().getUp(), walkUp);
		player.getAnimationController().addSprite(player.getInput().getDown(), walkDown);
		FileInputStream fin=new FileInputStream("resources/players/eric/wheelchair/Me_back.png");
		FileInputStream fin2=new FileInputStream("resources/players/eric/wheelchair/Me_forward.png");
		FileInputStream fin3=new FileInputStream("resources/players/eric/wheelchair/Me_left.png");
		FileInputStream fin4=new FileInputStream("resources/players/eric/wheelchair/Me_right.png");
		Image upImg=new Image(fin, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Image downImg=new Image(fin2, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Image leftImg=new Image(fin3, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Image rightImg=new Image(fin4, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Sprite sprite=new Sprite(upImg, position);
		Sprite spriteLeft=new Sprite(leftImg, position);
		Sprite spriteDown=new Sprite(downImg, position);
		Sprite spriteRight=new Sprite(rightImg, position);
		player.getSpriteController().addSprite(player.getInput().getDown(), spriteDown);
		player.getSpriteController().addSprite(player.getInput().getLeft(), spriteLeft);
		player.getSpriteController().addSprite(player.getInput().getRight(), spriteRight);
		player.getSpriteController().addSprite(player.getInput().getUp(), sprite);
		
		return player;
	}
	
	public static Player createPlayer(String name, double width, double height, int animationSpeed, Vector moveVector) throws FileNotFoundException {
		Player player=new Player.Builder()
				.hasInput(new PlayerInput())
				.hasMaxAcceleration(new Vector(0.5, 0.5))
				.hasMaxVelocity(new Vector(5, 5))
				.hasMoveSpeed(moveVector)
				.hasPlayerAnimationSpriteController(new PlayerAnimationSpriteController())
				.hasSpriteController(new PlayerSpriteController())
				.build();
		
		PlayerData data = new PlayerData.Builder().setName(name).setHealth(150).build();
		data.setMaxHealth(150);
		data.setAttackValue(23);
		data.setPath("file:resources/players/"+name+"/standing/"+name+"_standing_right.png");
		player.setData(data);
		
		Vector position=player.getPosition();
		player.getDimensions().setX(width);
		player.getDimensions().setY(height);
		AnimationSprite walkUp=new AnimationSprite(position, animationSpeed);
		walkUp.setDimensions(player.getDimensions());
		walkUp.load("resources/players/"+name+"/walking/up");
		AnimationSprite walkLeft=new AnimationSprite(position, animationSpeed);
		walkLeft.setDimensions(player.getDimensions());
		walkLeft.load("resources/players/"+name+"/walking/left");
		AnimationSprite walkRight=new AnimationSprite(position, animationSpeed);
		walkRight.setDimensions(player.getDimensions());
		walkRight.load("resources/players/"+name+"/walking/right");
		AnimationSprite walkDown=new AnimationSprite(position, animationSpeed);
		walkDown.setDimensions(player.getDimensions());
		walkDown.load("resources/players/"+name+"/walking/down");
		
		player.getAnimationController().addSprite(player.getInput().getLeft(), walkLeft);
		player.getAnimationController().addSprite(player.getInput().getRight(), walkRight);
		player.getAnimationController().addSprite(player.getInput().getUp(), walkUp);
		player.getAnimationController().addSprite(player.getInput().getDown(), walkDown);
		FileInputStream fin=new FileInputStream("resources/players/"+name+"/standing/"+name+"_standing_forward.png");
		FileInputStream fin2=new FileInputStream("resources/players/"+name+"/standing/"+name+"_standing.png");
		FileInputStream fin3=new FileInputStream("resources/players/"+name+"/standing/"+name+"_standing_left.png");
		FileInputStream fin4=new FileInputStream("resources/players/"+name+"/standing/"+name+"_standing_right.png");
		Image upImg=new Image(fin, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Image downImg=new Image(fin2, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Image leftImg=new Image(fin3, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Image rightImg=new Image(fin4, player.getDimensions().getX(), player.getDimensions().getY(), false, false);
		Sprite sprite=new Sprite(upImg, position);
		Sprite spriteLeft=new Sprite(leftImg, position);
		Sprite spriteDown=new Sprite(downImg, position);
		Sprite spriteRight=new Sprite(rightImg, position);
		player.getSpriteController().addSprite(player.getInput().getDown(), spriteDown);
		player.getSpriteController().addSprite(player.getInput().getLeft(), spriteLeft);
		player.getSpriteController().addSprite(player.getInput().getRight(), spriteRight);
		player.getSpriteController().addSprite(player.getInput().getUp(), sprite);
		
		return player;
	}
	
}
