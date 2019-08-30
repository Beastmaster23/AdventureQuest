package application.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import application.models.utils.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;


/**
 * Controls one animation.
 * @author Interco
 */
public class AnimationSprite {
	private Image current;
	private Timeline loop;
	private ArrayList<Image> frames;
	private int i, delay;
	private Vector position, dimensions;
	private boolean running;
	public AnimationSprite(int millis) {
		frames=new ArrayList<Image>();
		position=new Vector();
		i=0;
		delay=millis;
		loop=new Timeline();
		loop.setCycleCount(Timeline.INDEFINITE);
		loop.getKeyFrames().add(new KeyFrame(Duration.millis(delay), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				i++;
				if(i>=frames.size())
					i=0;
				setCurrent(frames.get(i));
			}
		}));
		dimensions=new Vector(50, 50);
		running=false;
	}
	
	public AnimationSprite(Vector animationPosition, int millis) {
		frames=new ArrayList<Image>();
		position=animationPosition;
		i=0;
		delay=millis;
		loop=new Timeline();
		loop.setCycleCount(Timeline.INDEFINITE);
		loop.getKeyFrames().add(new KeyFrame(Duration.millis(delay), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				i++;
				if(i>=frames.size())
					i=0;
				setCurrent(frames.get(i));
			}
		}));
		dimensions=new Vector(50, 50);
		running=false;
	}
	
	public void load(String dirPath) throws FileNotFoundException {
		File dir=new File(dirPath);
		File[] files=dir.listFiles();
		
		for(File f: files) {
			
			FileInputStream fin=new FileInputStream(f);
			
			frames.add(new Image(fin, dimensions.getX(), dimensions.getY(), false, false));
		}
	}
	
	public void start() {
		loop.play();
		setRunning(true);
	}
	
	public void stop() {
		setRunning(false);
		loop.stop();
	}

	public void draw(GraphicsContext context) {
		
		context.drawImage(frames.get(i), position.getX(), position.getY());
	}

	public Image getCurrent() {
		return current;
	}

	public void setCurrent(Image current) {
		this.current = current;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Vector getDimensions() {
		return dimensions;
	}

	public void setDimensions(Vector dimensions) {
		this.dimensions = dimensions;
	}
}
