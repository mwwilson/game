package model;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import util.Dispatcher;
import balls.ABall;

/**
 * Model for Ballworld that drives the view, BallGUI.
 * 
 * @author Kathy Li
 * @author Andrew Huie
 */
public class BallModel {

	// Null adapter ensures the model always has a valid adapter that knows about the view.
	/** Adapter from the model to the view */
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT;
	/** An Observable that notifies its observers to update when it does */
	private Dispatcher modelDispatcher = new Dispatcher();
	/** The amount of time between "ticks" */
	private int timeSlice = 50;  // update every 50 ms
	/** Timer that tells the MVC to update */
	private Timer timer = new Timer(timeSlice, new ActionListener() {
	    /**
	     * The timer "ticks" by calling this method every _timeslice milliseconds
	     */
	    public void actionPerformed (ActionEvent e) {
	        _model2ViewAdpt.update();
	    }
	});

	/**
	 * Construct a ball model with its corresponding view adapter.
	 * 
	 * @param model2ViewAdpt 	the adapter to the view
	 */
	public BallModel(IModel2ViewAdapter model2ViewAdpt ) {
		_model2ViewAdpt  = model2ViewAdpt;
	}

	/**
	 * This is the method that is called by the view's adapter to the model, 
	 * i.e. is called by IView2ModelAdapter.paint().
	 * This method will update the balls' painted locations by painting all the balls
	 * onto the given Graphics object.
	 * 
	 * @param g 	the Graphics object from the view's paintComponent() call.
	 */
	public void update(Graphics g) {
		modelDispatcher.notifyAll(g);
	}
	
	/**
	 * Add a ball to the dispatcher's set of observers
	 * @param ball	the new ball
	 */
	public void addBall(ABall ball) {
		modelDispatcher.addObserver(ball);
	}
	
	/**
	 * Delete all observers from the dispatcher and from the screen
	 */
	public void deleteBalls() {
		modelDispatcher.deleteObservers();
	}
	
	/**
	 * Start the ball model.
	 */
	public void start() {
		timer.start();
	}
	
}
