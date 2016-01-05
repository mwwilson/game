package view;

import java.awt.Graphics;

import balls.ABall;

/**
 * The interface of the adapter from the view to the model that enables the view
 * to talk to the model.
 */
public interface IView2ModelAdapter {

	/**
	 * Tells the model to tell the Dispatcher to tell the balls to paint themselves.
	 * 
	 * @param g 	Graphics object on which to paint
	 */
	public void paint(Graphics g);
	
	/**
	 * Adds the ball to the Dispatcher.
	 * 
	 * @param ball		the new ball
	 */
	public void addBall(ABall ball);
	
	/**
	 * Removes all of the balls from the Dispatcher.
	 */
	public void deleteBalls();

	/**
	 * No-op null object implementation of IView2ModelAdapter.
	 */
	public static final IView2ModelAdapter NULL_OBJECT = new IView2ModelAdapter() {
		public void paint(Graphics g) {
		}
		
		public void addBall(ABall ball) {
		}
		
		public void deleteBalls() {
		}
	};
}
