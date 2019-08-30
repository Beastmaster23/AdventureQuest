package application.viewModel;

import application.models.player.AnimationLoop;
import application.models.player.AnimationLoopListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TextAnimationController {
	private Label text;
	private String string;
	private String current;
	private int i;
	private AnimationLoop loop;
	private boolean finished;
	public TextAnimationController(Label text) {
		this.text=text;
		current="";
		string="Greetings player!";
		i=0;
		finished=false;
		loop=new AnimationLoop(100);
		loop.getLoop().setCycleCount(string.length());
		loop.addListener(new AnimationLoopListener() {

			@Override
			public void OnAction() {
				// TODO Auto-generated method stub
				i++;
				if(i>string.length()) {
					i=1;
					
				}
				if(i>=string.length())
					finished=true;
				else {
					
					finished=false;
				}
				String s=string.substring(0, i);
				current=s;
				text.setText(current);
			}
			
		});
	}
	


	public boolean isFinished() {
		
		return finished;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
		loop.getLoop().setCycleCount(string.length());
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public AnimationLoop getLoop() {
		return loop;
	}
	public void setLoop(AnimationLoop loop) {
		this.loop = loop;
	}
	public Label getText() {
		return text;
	}
	public void setText(Label text) {
		this.text = text;
	}
}
