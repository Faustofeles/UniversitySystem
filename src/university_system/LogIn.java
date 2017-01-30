/**
	LogIn Class searches a username and password pair in the 
	database and determines if they correspond to an existing user.
	If the pair corresponds to a user, the program extracts the user
	type and calls out the corresponding homepage for that specific 
	user type. 
 */

package university_system;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class LogIn extends SystemGUI{
	//User userType;
	private JPasswordField pw; // Field for password
	private JTextField text; // Field for text
	private JPanel p, center; // Two reusable panels
	private JButton logInButton, signUpButton; // Buttons
	private GridBagConstraints gc; // Constraints for GridBagLayout
	

	/**
	 * Constructor as of now does not receive any parameters. The intention is for the Login Page to 
	 * be created from this constructor. As the user types its username and password information, the
	 * instance of this class will search for a matching user in the database. If true, a user object 
	 * will be created with its respective fields. The User object is then passed to a HomePage class
	 * where the initial interface of the system is drawn. There the user is allowed to move freely. 
	 * @author Reynaldo
	 */
	
	public LogIn(){

		mainFrame = new JFrame("Login - The University of Texas at El Paso");
		mainFrame.setSize(600,250);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Centers to Screen Being Used
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2); 
		mainFrame.setLayout(new BorderLayout()); // Frame Layout
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes when "x" is pressed
		mainFrame.setResizable(false); // Not a resizable window

		// Create Buttons
		logInButton = new JButton("Login");
		signUpButton = new JButton("Sign Up"); 

		// Create central panel with bag constraints initialized
		center = new JPanel();
		center.setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		center.setSize(600, 250);
		titleBorder(center, "Course Registration System"); // Creates a border with title

		/* Creates upper panel content */
		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addToPanel(p, new JLabel("THE UNIVERSITY OF TEXAS AT EL PASO"));
		mainFrame.add(p, BorderLayout.NORTH);	// Adds upper panel to frame 

		/* Creates central panel content */
		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addToPanel(p, new JLabel("USERNAME"));
		text = new JTextField(15);
		addToPanel(p, text);
		gc = bagConstraints(0,0,1,1,GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1,40,0,0,0);
		center.add(p, gc);

		/* Creates password fields */
		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addToPanel(p, new JLabel("PASSWORD"));
		pw = new JPasswordField(15);
		addToPanel(p, pw);
		gc = bagConstraints(0,1,1,1,GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1,0,0,45,0);
		center.add(p, gc);

		mainFrame.add(center, BorderLayout.CENTER);	// Adds center panel to frame

		/* Creates lower panel content */
		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addToPanel(p, logInButton);
		addToPanel(p, new JLabel("		Not A Student?"));
		addToPanel(p, signUpButton);
		mainFrame.add(p, BorderLayout.SOUTH);	// Adds lower panel to frame

		mainFrame.setVisible(true); // Sets frame to visible


		/* ---------------------------------------------------------------
		 * --------- Action Handlers ------------------------------------- 
		 * ---------------------------------------------------------------
		 */

		logInButton.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Login Button Pressed");		// TODO Erase sysout
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					mainFrame.getContentPane().removeAll();
						new HomePage(mainFrame);
					}
				});
			}
		});

		signUpButton.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO Work on code here
				System.out.println("Sign Up Button Pressed");	// TODO Erase sysout
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new SignUp();
					}
				});

			}
		});
	}
	
	/**
	 * This private class handles the situation when users are not registered in 
	 * the system's data base. It allows the user in this situation to fill in a 
	 * form to then be added to the database.
	 * @author Reynaldo
	 * @version 1.3
	 *
	 */
	private class SignUp{
		
		JButton registerButton = new JButton("Resgiter");
		JButton cancelButton = new JButton("Cancel");
		
		private JTextField[] textFields = new JTextField[]{ new JTextField(15),new JTextField(15),new JTextField(15),
				new JTextField(15),new JTextField(15), new JTextField(15),new JTextField(15),new JTextField(15),
				new JPasswordField(15),new JPasswordField(15)}; 

		private JLabel[] labels = new JLabel[]{new JLabel("First Name: "),new JLabel("Last Name: "),
				new JLabel("Age: "),new JLabel("Address: "),new JLabel("State: "), new JLabel("City: "),
				new JLabel("Zip Code: "), new JLabel("Choose a Username: "), new JLabel("Choose a Password: "),
				new JLabel("Rewrite Password: ")};

		private SignUp(){
			JFrame frame = new JFrame();
			frame.setTitle("Sign Up");
			frame.setSize(350, 470);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Centers to Screen Being Used
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); 
			frame.setLayout(new BorderLayout()); // Frame Layout
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			p.add(new JLabel("Register for School"));
			frame.add(p, BorderLayout.NORTH);

			p = new JPanel(new GridBagLayout());
			gc = new GridBagConstraints();

			gc.gridx = 0;	// Position on x axis
			gc.gridwidth = 2; 
			gc.gridheight = 1;
			gc.weightx = 1;
			gc.weighty = 1;

			for(int j = 0; j < labels.length; j++){
				p.add(labels[j], gc);
				gc.gridx++; // Increment Position 
				p.add(textFields[j], gc); // Add text fields to panel
				gc.gridx--;
			}
			
			gc.gridy = 21;
			gc.gridx++;
			gc.gridwidth = 1;
			gc.insets = new Insets(15,0,0,0);
			p.add(registerButton, gc);	
			gc.gridx++;
			p.add(cancelButton, gc);

			// Encapsulate in scroll pane //
			frame.add(new JScrollPane(p), BorderLayout.CENTER);
			frame.setVisible(true);	// Set Visible

			/*
			 * Button handlers are HERE 
			 */
			registerButton.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Register Student into Database");		// TODO Erase sysout
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run(){
							
							frame.dispose();
						}
					});
				}
			});

			cancelButton.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// TODO Work on code here
					System.out.println("Cancel Button Pressed");	// TODO Erase sysout
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							frame.dispose();
						}
					});
				}
			});
		}
	}
	/*
	 * Main method independent of system. Test purposes/
	 */
	public static void main(String[] args){ 
		SwingUtilities.invokeLater(new Runnable() { // Preferred called
			@Override
			public void run() {
				new LogIn();
			}
		});
	}
}