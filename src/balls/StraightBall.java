package balls;

import java.awt.*;

/**
 * Ball that travels in a straight line until it bounces off a wall.
 * 
 * @author Kathy Li
 * @author Andrew Huie
 */
public class StraightBall extends ABall {
	
	/**
	 * Construct a ball with ABall's constructor.
	 * 
	 * @param _window 	the JPanel that contains the ball
	 */
	public StraightBall(Component _window) {
		super(_window);
	}

	/**
	 * Move the ball in a straight line.
	 */
	public void updateState() {
		getLoc().translate(getVel().x, getVel().y);	
	}

}
