package strategy;

import java.awt.*; 

import model.Ball;
import model.IUpdateStrategy;

/**
 * Strategy that delegates its updateState method to the strategy that it holds
 * @author Kathy Li
 * @author Marshall Wilson
 * @author Andres Cedeno
 */
public class SwitcherStrategy implements IUpdateStrategy { 
	private IUpdateStrategy strategy = new StraightStrategy(); 
	
	public void updateState(Ball context) { 
		strategy.updateState(context); 
	} 
	
	public void setStrategy(IUpdateStrategy newStrategy) { 
		strategy = newStrategy; 
	} 
	
}