package university_system;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HomePage extends SystemGUI{

	JFrame frame; //Main frame
	JPanel p, center; // Reusable panels
	JButton regButton, checkButton, manageButton, searchButton; // All Buttons
	GridBagConstraints gc; // Constraints for GridBagConstraints

	/**
	 * Constructor that generates the Home Page interface
	 * @param frame A frame shared by a different interface in the application
	 * @author Reynaldo
	 */
	public HomePage(JFrame frame){ // Passed from another interface
		super();
		this.frame = frame;	// Passing a frame over to this class
		frame.setTitle("University Registration System");
		frame.setSize(WIDTH,HEIGHT); // Default Size of Application 
		frame.setResizable(false);	// Not Resizable
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/* Buttons */
		regButton = new JButton("Registration");
		checkButton = new JButton("Check Status");
		manageButton = new JButton("Management");
		searchButton = new JButton("Search Students");
		
		/* North Panel*/		
		p = createHeaderPanel("Home Page"); // Creates page header 
		frame.add(p, BorderLayout.NORTH);	// Adds header to frame
		
		/* Central Panel */
		center = new JPanel(new GridBagLayout());
		
		/*	-----------------------------------------------
			-----------------------------------------------
			    GridBagConstraints for Central Panel
			-----------------------------------------------
			----------------------------------------------- 
		*/
	
		/* Registration Button Constraints */
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		p = new JPanel(new GridBagLayout());
		p.add(regButton, gc);
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 50,100,50,70);
		titleBorder(p, "Register Today");
		center.add(p, gc);
		regButton.addActionListener(new ButtonListener()); //button enabled


		/* Check Status Button Constraints */
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		p = new JPanel(new GridBagLayout());
		p.add(checkButton, gc);
		gc = bagConstraints(1,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 50,70,50,100);
		titleBorder(p, "See Records");
		center.add(p, gc);
		checkButton.addActionListener(new ButtonListener()); //button enabled


		/* Management Button Constraints */
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		p = new JPanel(new GridBagLayout());
		p.add(manageButton, gc);
		gc = bagConstraints(0,1,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 10,100,10,70);
		titleBorder(p, "Authorization Required");
		center.add(p, gc);
		manageButton.addActionListener(new ButtonListener()); //button enabled

		
		/* Search Button Constraints */
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		p = new JPanel(new GridBagLayout());
		p.add(searchButton, gc);
		gc = bagConstraints(1,1,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 10,70,10,100);
		titleBorder(p, "Student Database");
		center.add(p, gc);
		searchButton.addActionListener(new ButtonListener()); //button enabled

		dashBorder(center); // Adds a dashed Border to central panel
		frame.add(center, BorderLayout.CENTER); // Adds central panel to frame 
		
		/*
			-----------------------------------------------
			-----------------------------------------------
			----------------------------------------------- 
		*/	
		
		frame.setVisible(true); // frame to visible
	}

	/**
	 * Button Action Handler private class, manages all the button
	 * action events in this frame. 
	 * @author Reynaldo
	 */
	public class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			frame.getContentPane().removeAll();
			if(e.getSource() == regButton){
				System.out.println("Registration Button Pressed"); // regButton pressed
        		new RegPage(frame);
			}
			else if(e.getSource() == checkButton){
				System.out.println("Check Status Button Pressed"); // checkButton pressed
        		new CheckPage(frame, "NONE");
			}
			else if(e.getSource() == searchButton){
				System.out.println("Search Button Pressed");	// searchButton pressed
        		new SearchPage(frame);
			}
			else if(e.getSource() == manageButton){
				System.out.println("Management Button Pressed"); // manageButton pressed
				frame.getContentPane().removeAll();
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	new ManPage(frame);
		            }
		        });
			}
			else if(e.getSource() == homeButton){
				System.out.println("Removed Course From List"); // homeButton pressed
				System.out.println("Home Button Pressed");
        		new HomePage(frame);
			}
		}
	}

	
	/*
	 * Main method is HERE
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage(new JFrame());
            }
        });
	}
}