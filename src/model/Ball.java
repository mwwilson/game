package model;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import util.Randomizer;

/**
 * Abstract implementation of a ball in Ballworld.
 * 
 * @author Kathy Li
 */
public abstract class Ball implements Observer {	
	
	/** Utility to help generate random values */
	private Randomizer rand;
	/** A ball's location is the (x,y) coordinate pair that marks its center. */
	private Point location;
	/** A ball's velocity is pixels per tick, in the x and y directions. */
	private Point velocity;
	/** A ball has a color */
	private Color color;
	/** A ball has a radius */
	private int radius;
	/** The component inside which the balls will bounce */
	private Component window;
	
	private IUpdateStrategy strategy;
	
	/**
	 * Common constructor for all balls. Randomly generates a color,
	 * radius, starting location and velocity.
	 * 
	 * @param _window	The window containing all the balls, or the fixed space in which they can move
	 */
	public Ball(Component _window, IUpdateStrategy _strategy) {
		rand = Randomizer.Singleton;
		window = _window;
		strategy = _strategy;
		radius = rand.randomInt(20, 60);
		
		// not using randomLoc() because function always includes (0,0) in the range,
		// instead of limiting it to the upper left corner of the rectangle parameter
		location = new Point(rand.randomInt(radius, window.getWidth()-radius), rand.randomInt(radius, window.getHeight()-radius));
		velocity = rand.randomVel(new Rectangle(15, 15));
		
		// Don't start with zero velocity.
		if (velocity.x == 0) {
			velocity.x += 2;
		}
		if (velocity.y == 0) {
			velocity.y += 2;
		}
		
		color = rand.randomColor();
	}
	
	/**
	 * Gets the location of the ball.
	 * 
	 * @return the Point object location
	 */
	public Point getLoc() {
		return location;
	}
	
	/**
	 * Sets the location of the ball.
	 * 
	 * @param _location		desired location of the ball
	 */
	public void setLoc(Point _location) {
		location = _location;
	}
	
	/**
	 * Gets the velocity of the ball.
	 * 
	 * @return the Point object velocity
	 */
	public Point getVel() {
		return velocity;
	}
	
	/**
	 * Sets the velocity of the ball.
	 * 
	 * @param _velocity 	desired velocity of the ball
	 */
	public void setVel(Point _velocity) {
		velocity = _velocity;
	}
	
	/**
	 * Gets the color of the ball.
	 * 
	 * @return the Color object color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sets the color of the ball.
	 * 
	 * @param _color 	desired color of the ball
	 */
	public void setColor(Color _color) {
		color = _color;
	}
	
	/**
	 * Gets the radius of the ball.
	 * 
	 * @return the int radius
	 */
	public int getRad() {
		return radius;
	}
	
	/**
	 * Sets the radius of the ball.
	 * 
	 * @param _radius 	desired radius of the ball
	 */
	public void setRad(int _radius) {
		radius = _radius;
	}
	
	/**
	 * Gets the JPanel that contains the ball.
	 *  
	 * @return the Component window
	 */
	public Component getWindow() {
		return window;
	}
	
//	public void update(Observable o, Object g) {
//		// make changes to the state of the ball and move it to its new location
//		updateState();
//		
//		// bounce if the ball is at a wall
//		bounce();
//		
//		// paint the ball in the new location
//		paint((Graphics)g);
//	}

	/**
	 * Every ball has the ability to update itself.
	 * 
	 * @param o		The dispatcher that the balls are "observing"
	 * @param g		The graphics component onto which the balls will paint themselves
	 */
	public void update(Observable o, Object g) {
		strategy.updateState(this); // update this ball's state using the strategy 
		location.translate (velocity.x, velocity.y); // move the ball 
		bounce(); // bounce the ball off the wall if necessary 
		paint((Graphics) g); // paint the ball onto the container
	}
	
	/**
	 * Every ball has the ability to update its state, such as by translating its location.
	 */
	public abstract void updateState();
	
	/**
	 * Bounce the ball off a wall if it hits one.
	 * 
	 * The strategy in all the if statements is as follows:
	 * 	1. Assume the ball has already been moved to the new location, but not drawn yet.
	 * 	2. Check if it has passed the window boundaries on a particular side.
	 * 	3. Translate it back by twice the distance between the new location
	 * 		and the contact location, along the offending axis.
	 * 	4. Negate the velocity vector along the offending axis.
	 */
	protected void bounce() {
		int maxX = window.getWidth();
		int maxY = window.getHeight();
		
		// center point of a ball just touching the right or bottom wall
		Point rightBottomCenter = new Point(maxX - radius, maxY - radius);
		
		// center point of a ball just touching the left or top wall
		Point leftTopCenter = new Point(radius, radius);
		
		// ball past right wall
		if (location.x + radius >= maxX) {
			location.translate(-2*(location.x - rightBottomCenter.x), 0);
			velocity.setLocation(-velocity.x, velocity.y);
			
		// ball past left wall
		} else if (location.x - radius <= 0) {
			location.translate(2*(leftTopCenter.x - location.x), 0);
			velocity.setLocation(-velocity.x, velocity.y);
		}
		
		// ball past bottom wall
		if (location.y + radius >= maxY) {
			location.translate(0, -2*(location.y - rightBottomCenter.y));
			velocity.setLocation(velocity.x, -velocity.y);
			
		// ball past top wall
		} else if (location.y - radius <= 0) {
			location.translate(0, 2*(leftTopCenter.y - location.y));
			velocity.setLocation(velocity.x, -velocity.y);
		}
	}
	
	/**
	 * Every ball has the ability to paint itself.
	 * 
	 * @param g		The graphics component onto which the balls will paint themselves
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(location.x-radius, location.y-radius, 2*radius, 2*radius);
	}
	
}
