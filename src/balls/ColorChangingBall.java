package balls;

import java.awt.Component;

import util.Randomizer;

/**
 * Ball that changes color as it travels.
 * 
 * @author Kathy Li
 * @author Andrew Huie
 */
public class ColorChangingBall extends ABall {
	
	/** A utility to generate random values */
	private Randomizer rand;

	/**
	 * Construct a ball using ABall's constructor,
	 * adding a Randomizer for the color.
	 * 
	 * @param _window 	the JPanel that contains the ball
	 */
	public ColorChangingBall(Component _window) {
		super(_window);
		rand = Randomizer.Singleton;
	}
	
	/**
	 * Change color randomly while moving ball in a straight line.
	 */
	public void updateState() {
		// select a random color to change the ball to
		setColor(rand.randomColor());
		
		// move the ball in a straight line
		getLoc().translate(getVel().x, getVel().y);
	}

}
