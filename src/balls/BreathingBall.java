package balls;

import java.awt.Component;

import util.SineMaker;

/**
 * Ball that "breathes" by expanding and contracting (increases/decreases radius) as it travels.
 * 
 * @author Kathy Li
 * @author Andrew Huie
 */
public class BreathingBall extends ABall {
	
	/** A utility to generate sinusoidal radius values */
	private SineMaker sineRadius;

	/**
	 * Construct a BreathingBall with ABall's constructor, 
	 * adding a SineMaker for the changing radius.
	 * 
	 * @param _window	the JPanel that contains the ball
	 */
	public BreathingBall(Component _window) {
		super(_window);
		sineRadius = new SineMaker(20.0, 60.0, 0.3);
	}

	/**
	 * Generate new radius and move ball straight ahead.
	 */
	public void updateState() {
		// get new radius
		setRad(sineRadius.getIntVal());
		
		// move the ball in a straight line
		getLoc().translate(getVel().x, getVel().y);
	}

}
