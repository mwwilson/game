package view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import balls.ABall;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * Ballworld GUI.
 * 
 * @author Kathy Li
 * @author Andrew Huie
 */
public class BallGUI extends JFrame {

	private static final long serialVersionUID = 6399321741862401222L;
	
	// Ensures the GUI always has a valid adapter that knows about the model.
	/** Adapter from the view to the model */
	private IView2ModelAdapter _view2ModelAdpt = IView2ModelAdapter.NULL_OBJECT;
	
	private JPanel contentPane;
	/** The main panel in which the balls will bounce around */
	private final JPanel pnlCenter = new JPanel() {
		private static final long serialVersionUID = -6952656931251224807L;

		/**
		 * Override the default paintComponent method to call paint on the view2model adapter.
		 */
		public void paintComponent(Graphics g) {
           super.paintComponent(g);  // clear the panel and redo the background
           _view2ModelAdpt.paint(g);  // call back to the model to paint the balls
        }
	};
	private final JPanel pnlTop = new JPanel();
	private final JButton btnBallMaker = new JButton("Make Ball");
	private final JTextField txtField = new JTextField();
	private final JButton btnClear = new JButton("Clear All");
	private final JButton btnSwitchMaker = new JButton("Make Switcher");
	private final JComboBox comboBox = new JComboBox();
	private final JComboBox comboBox_1 = new JComboBox();
	private final JButton btnSwitch = new JButton("Switch!");
	private final JButton btnCombiner = new JButton("Combine Balls");
	
	/**
	 * Starts the already initialized frame, making it
	 * visible and ready to interact with the user. 
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Create the frame.
	 * @param view2ModelAdpt	the view's adapter to the model
	 */
	public BallGUI(IView2ModelAdapter view2ModelAdpt) {
		setBackground(Color.LIGHT_GRAY);
		txtField.setText("Type Stuff Here");
		txtField.setToolTipText("Specify type of ball to add");
		txtField.setColumns(14);
		_view2ModelAdpt = view2ModelAdpt;
		initGUI();
	}
	
	/**
	 * Initialize the frame GUI.
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.setToolTipText("Window into Ballworld");
		
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlTop.setBackground(Color.ORANGE);
		pnlTop.setToolTipText("Top-fixed user control panel");
		
		contentPane.add(pnlTop, BorderLayout.NORTH);
		GridBagLayout gbl_pnlTop = new GridBagLayout();
		gbl_pnlTop.columnWidths = new int[]{182, 117, 0, 95, 0};
		gbl_pnlTop.rowHeights = new int[]{29, 0, 0, 29, 0};
		gbl_pnlTop.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlTop.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnlTop.setLayout(gbl_pnlTop);
		
		/**
		 * Makes a new ball of the class typed into the text field
		 */
		btnBallMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ABall ball = loadBall(txtField.getText());
				_view2ModelAdpt.addBall(ball);
			}
		});
		btnBallMaker.setToolTipText("Add ball specified in text field");
		GridBagConstraints gbc_btnBallMaker = new GridBagConstraints();
		gbc_btnBallMaker.anchor = GridBagConstraints.NORTH;
		gbc_btnBallMaker.insets = new Insets(0, 0, 5, 5);
		gbc_btnBallMaker.gridx = 1;
		gbc_btnBallMaker.gridy = 0;
		pnlTop.add(btnBallMaker, gbc_btnBallMaker);
		
		GridBagConstraints gbc_txtField = new GridBagConstraints();
		gbc_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtField.anchor = GridBagConstraints.NORTH;
		gbc_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_txtField.gridx = 0;
		gbc_txtField.gridy = 1;
		pnlTop.add(txtField, gbc_txtField);
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		comboBox.setToolTipText("Choose a ball type to make or combine");
		pnlTop.add(comboBox, gbc_comboBox);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_view2ModelAdpt.deleteBalls();
			}
		});
		
		GridBagConstraints gbc_btnSwitchMaker = new GridBagConstraints();
		gbc_btnSwitchMaker.anchor = GridBagConstraints.NORTH;
		gbc_btnSwitchMaker.insets = new Insets(0, 0, 5, 5);
		gbc_btnSwitchMaker.gridx = 2;
		gbc_btnSwitchMaker.gridy = 1;
		btnSwitchMaker.setToolTipText("Create a new switcher");
		pnlTop.add(btnSwitchMaker, gbc_btnSwitchMaker);
		btnClear.setToolTipText("Remove all balls");
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 0);
		gbc_btnClear.anchor = GridBagConstraints.NORTH;
		gbc_btnClear.gridx = 3;
		gbc_btnClear.gridy = 1;
		pnlTop.add(btnClear, gbc_btnClear);
		
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 2;
		comboBox_1.setBackground(Color.GRAY);
		comboBox_1.setToolTipText("Choose a ball type to combine");
		pnlTop.add(comboBox_1, gbc_comboBox_1);
		
		GridBagConstraints gbc_btnSwitch = new GridBagConstraints();
		gbc_btnSwitch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSwitch.gridx = 2;
		gbc_btnSwitch.gridy = 2;
		btnSwitch.setToolTipText("Switch to the selected strategy");
		pnlTop.add(btnSwitch, gbc_btnSwitch);
		
		GridBagConstraints gbc_btnCombiner = new GridBagConstraints();
		gbc_btnCombiner.fill = GridBagConstraints.VERTICAL;
		gbc_btnCombiner.insets = new Insets(0, 0, 0, 5);
		gbc_btnCombiner.gridx = 1;
		gbc_btnCombiner.gridy = 3;
		btnCombiner.setToolTipText("Combine the selected strategies");
		btnCombiner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlTop.add(btnCombiner, gbc_btnCombiner);
		
		/**
		 * Clears all balls off the screen and deletes them from the dispatcher.
		 */
	}
	
	/**
	* The following method returns an instance of an ABall, given a fully qualified class name (package.classname) of
	* a subclass of ABall.
	* The method assumes that there is only one constructor for the supplied class that has the same *number* of
	* input parameters as specified in the args array and that it conforms to a specific
	* signature, i.e. specific order and types of input parameters in the args array.
	* 
	* @param className		a string that is the fully qualified class name of the desired object
	* @return an instance of the supplied class. 
	*/
	private ABall loadBall(String className) {
	    try {
	        Object[] args = new Object[]{(Component)pnlCenter};
	        
	        // get all the constructors
	        java.lang.reflect.Constructor<?> cs[] = Class.forName(className).getConstructors();
	        java.lang.reflect.Constructor<?> c = null;
	        
	        // find the first constructor with the right number of input parameters
	        for(int i=0;i < cs.length; i++) {
	            if(args.length == (cs[i]).getParameterTypes().length) {
	                c = cs[i];
	                break;
	            }
	        }
	    return (ABall) c.newInstance(args);
	  }
	  catch(Exception ex) {
	    System.err.println("Class "+className+" failed to load. \nException = \n"+ ex);
	    ex.printStackTrace();  // print the stack trace to help in debugging.
	    return null;
	  }
	}
	
	/**
	 * Updates the view by repainting the canvas
	 */
	public void update() {
		pnlCenter.repaint();
	}
}
