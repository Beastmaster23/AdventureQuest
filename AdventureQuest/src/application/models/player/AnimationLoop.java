package application.models.player;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class AnimationLoop {

	private int delay;

	private Timeline loop;

	private ArrayList<AnimationLoopListener> listeners;

	public AnimationLoop(int delay) {
		listeners = new ArrayList<AnimationLoopListener>();
		this.delay = delay;
		loop = new Timeline();
		loop.setCycleCount(Timeline.INDEFINITE);
		
		loop.getKeyFrames().add(new KeyFrame(Duration.millis(delay), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (AnimationLoopListener listener : listeners) {
					listener.OnAction();
				}
			}
		}));
	}

	public AnimationLoop(Timeline loop) {

	}

	public void start() {
		loop.play();
	}

	public void stop() {
		loop.stop();
	}

	public void addListener(AnimationLoopListener listener) {
		listeners.add(listener);
	}
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
		loop.getKeyFrames().add(new KeyFrame(Duration.millis(delay), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (AnimationLoopListener listener : listeners) {
					listener.OnAction();
				}
			}
		}));
		loop.getKeyFrames().remove(0);
	}

	public Timeline getLoop() {
		return loop;
	}

	public void setLoop(Timeline loop) {
		this.loop = loop;
	}
}
