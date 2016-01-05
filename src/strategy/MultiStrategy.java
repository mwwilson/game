package strategy;

import model.Ball;
import model.IUpdateStrategy;

public class MultiStrategy implements IUpdateStrategy { 
	private IUpdateStrategy _s1; 
	private IUpdateStrategy _s2; 
	
	public MultiStrategy(IUpdateStrategy s1, IUpdateStrategy s2) { 
		_s1 = s1; _s2 = s2; 
	} 
	
	public void updateState(Ball context) { 
		_s1.updateState(context); 
		_s2.updateState(context); 
	} 
	
}