package balls;

import java.awt.Component;

import util.Randomizer;
import util.SineMaker;

/**
 * Ball that moves with sinusoidally changing velocity.
 * 
 * @author Kathy Li
 * @author Andrew Huie
 */
public class WanderBall extends ABall {
	
	private SineMaker sineVelX;
	private SineMaker sineVelY;
	private Randomizer rand;

	/**
	 * Construct a DrunkBall with ABall's constructor,
	 * adding a varying velocity vector.
	 * 
	 * @param _window 	the JPanel that contains the ball
	 */
	public WanderBall(Component _window) {
		super(_window);
		rand = Randomizer.Singleton;
		sineVelX = new SineMaker(0.0, getVel().x*0.5, rand.randomDouble(0.0, 0.3));
		sineVelY = new SineMaker(0.0, getVel().y*0.5, rand.randomDouble(0.0, 0.3));
		
		// make sure the ball isn't going too fast
		getVel().translate(-getVel().x/2, -getVel().y/2);
	}
	
	/**
	 * Change the velocity by a sinusoidal amount when moving
	 */
	public void updateState() {
		// tweak the velocity
		getVel().translate(sineVelX.getIntVal(), sineVelY.getIntVal());
		
		// move the ball to the new location
		getLoc().translate(getVel().x, getVel().y);
	}

}
