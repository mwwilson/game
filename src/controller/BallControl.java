package controller;

import java.awt.EventQueue;
import java.awt.Graphics;

import balls.ABall;
import view.BallGUI;
import view.IView2ModelAdapter;
import model.BallModel;
import model.IModel2ViewAdapter;

/**
 * MVC Controller for the system.
 */
public class BallControl {

	// Fields for the adapters to close over:
	/** The model in the MVC */
	private BallModel model;
	/** The view in the MVC */
	private BallGUI view;
	
	/**
	 * Constructor sets the model and view, and defines the null methods in each's respective adapter.
	 */
	public BallControl() { 
	
		// set the model field; the adapter connects 
		// the model to the view by calling the view's methods
		model = new BallModel( new IModel2ViewAdapter() {

			@Override
			public void update() {
				view.update();
			}
		}); 
		
		// set the view field; the adapter connects
		// the view to the model by calling the model's methods
		view = new BallGUI(new IView2ModelAdapter() {

			@Override
			public void paint(Graphics g) {
				model.update(g);
			}

			@Override
			public void addBall(ABall ball) {
				model.addBall(ball);
			}
			
			@Override
			public void deleteBalls() {
				model.deleteBalls();
			}
		}); 	 
	}
	
	/**
	 * Start the system
	 */
	public void start() {
		model.start();  // It is usually better to start the model first but not always.
		view.start();	
	}
	
	/**
	 * Launch the application.
	 * @param args		arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		
		// Java specs dictate that the system must be constructed on the GUI event thread.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// instantiate the system
					BallControl controller = new BallControl();
					
					// start the system
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}