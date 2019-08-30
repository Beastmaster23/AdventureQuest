package application.controller;


/**
 * Gives ViewControllers the ability to choose when to initialize or stop.
 * @author Interco
 */
public abstract class ViewController {
	public abstract void init(Object... args);
	public abstract void stop(Object... args);
}
