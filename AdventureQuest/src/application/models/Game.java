package application.models;

import java.util.ArrayList;
import java.util.PriorityQueue;

import application.models.utils.Collider2D;
import application.models.utils.GameObject;
import application.models.utils.Vector;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;


/**
 * Represents, controls, draws, and updates a game.
 * @author Interco
 */
public class Game {
	private ArrayList<GameObject> gameObjects;
	private double width, height;
	private Canvas canvas_pane;
	private AnimationTimer loop;
	private GameScreen screen;
	private boolean running;
	public static final int CONSTRICT_SCREEN=0, FULL_SCREEN=1;
	public Game(Canvas canvasPane, double widthGame, double heightGame) {
		gameObjects=new ArrayList<GameObject>();
		width=widthGame;
		height=heightGame;
		canvas_pane=canvasPane;
		screen =GameScreen.CONSTRICT_SCREEN;
		loop=new AnimationTimer(){
			float previousNano=0;
			@Override
			public void handle(long now) {
				if(previousNano==0) {
					previousNano=now;
					return ;
				}
				canvas_pane.getGraphicsContext2D().setFill(Color.BLACK);
				canvas_pane.getGraphicsContext2D().fillRect(0, 0, 800, 600);
				update();
				draw();
			}
		};
		setRunning(false);
	}
	
	//Naive approach later implement quad tree
		public void update() {
			for(int i=0;i< gameObjects.size();i++) {
				if(gameObjects.get(i).getCollider()==null)
					continue;
				for(int j=i+1;j< gameObjects.size();j++) {
					
					if(gameObjects.get(j).getCollider()==null)
						continue;
					if(gameObjects.get(i).getCollider().collide(gameObjects.get(j).getCollider())) {
						
					}
				}
			}
		}
	
	public void draw() {
		PriorityQueue<GameObject> sorted=new PriorityQueue<GameObject>(gameObjects);
		
		while(!sorted.isEmpty()) {
			GameObject obj=sorted.poll();
			obj.update();
			if(screen==GameScreen.CONSTRICT_SCREEN) {
				obj.constrict(width, height);
			}
			obj.draw(canvas_pane.getGraphicsContext2D());
		}
	}
	
	public void start() {
		loop.start();
		setRunning(true);
	}
	
	public void stop() {
		loop.stop();
		setRunning(false);
	}
	
	public GameObject getGameObjectByTag(String tag) {
		GameObject found=null;
		for(GameObject object: gameObjects) {
			if(object.getTag().compareTo(tag)==0) {
				found=object;
			}
		}
		return found;
	}
	
	public GameObject removeGameObjectByTag(String tag) {
		GameObject found=null;
		for(GameObject object: gameObjects) {
			if(object.getTag().compareTo(tag)==0) {
				found=object;
				gameObjects.remove(found);
				break;
			}
		}
		return found;
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public Canvas getCanvas_pane() {
		return canvas_pane;
	}

	public void setCanvas_pane(Canvas canvas_pane) {
		this.canvas_pane = canvas_pane;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
