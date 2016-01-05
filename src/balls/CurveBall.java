package balls;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

/**
 * Concrete implementation of ABall that moves in a curved path
 */
public class CurveBall extends ABall{

	/**
	 * angle of rotation per timer tick, in radians
	 */
	private double theta = 0.25;
	
	/**
	 * Create a new CurveBall object with specified color, center, radius, velocity, and enclosing panel
	 * 
	 * @param myColor   color of the ball (Color)
	 * @param center    center of the ball (Point)
	 * @param radius    radius of the ball (int)
	 * @param velocity  velocity of the ball where x and y represent the x and y velocities (Point)
	 * @param panel     panel of the ball that must have a height and width (Component)
	 */
	public CurveBall(Component _window) {
		super(_window);
	}

	/**
	 * rotates and updates the velocity vector (around angle theta)
	 */
	public void updateState() {
		Point oldV = getVel();
		int newVx = (int) Math.round(oldV.x * Math.cos(theta) - oldV.y * Math.sin(theta));
		int newVy = (int) Math.round(oldV.y * Math.cos(theta) + oldV.x * Math.sin(theta));
		setVel(new Point(newVx, newVy));
		getLoc().translate(getVel().x, getVel().y);
	}

}
