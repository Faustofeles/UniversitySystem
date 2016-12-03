package university_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends SystemGUI{

	JFrame frame;
	JPanel p, center;
	JButton regButton, checkButton, manageButton, searchButton, teachButton;
	GridBagConstraints gc;

	public HomePage(JFrame frame){
		super();
		this.frame = frame;	//Passing a frame over to this class
		frame.setTitle("University Registration System");
		frame.setSize(950,550);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Buttons */
		regButton = new JButton("Registration");
		checkButton = new JButton("Check Status");
		manageButton = new JButton("Management");
		searchButton = new JButton("Search Students");
		teachButton = new JButton("Classes I Teach");
		
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
		titleBorder(p, "Register Today");
		center.add(p, gc);
		checkButton.addActionListener(new ButtonListener()); //button enabled


		/* Management Button Constraints */
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		p = new JPanel(new GridBagLayout());
		p.add(manageButton, gc);
		gc = bagConstraints(0,1,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 10,100,10,70);
		titleBorder(p, "Register Today");
		center.add(p, gc);
		manageButton.addActionListener(new ButtonListener()); //button enabled

		
		/* Search Button Constraints */
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		p = new JPanel(new GridBagLayout());
		p.add(searchButton, gc);
		gc = bagConstraints(1,1,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 10,70,10,100);
		titleBorder(p, "Register Today");
		center.add(p, gc);
		searchButton.addActionListener(new ButtonListener()); //button enabled
		
				
		/* Classes I Teach Button Constraints */
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		p = new JPanel(new GridBagLayout());
		p.add(teachButton, gc);
		gc = bagConstraints(0,2,2,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 50,325,50,325);
		titleBorder(p, "Register Today");
		center.add(p, gc);
		teachButton.addActionListener(new ButtonListener()); //button enabled


		dashBorder(center); // Adds a dashed Border to central panel
		frame.add(center, BorderLayout.CENTER); // Adds central panel to frame 
		
		/*
			-----------------------------------------------
			-----------------------------------------------
			----------------------------------------------- 
		*/	
		
		frame.setVisible(true);
	}

	/*	-----------------------------------------------
		-----------------------------------------------
		    Button Action Handler Private Class
		----------------------------------------------- 
		-----------------------------------------------
	*/	

	public class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			frame.getContentPane().removeAll();
			if(e.getSource() == regButton){
				System.out.println("Registration Button Pressed");
        		new RegPage(frame);
			}
			else if(e.getSource() == checkButton){
				System.out.println("Check Status Button Pressed");
        		new CheckPage(frame);
			}
			else if(e.getSource() == searchButton){
				System.out.println("Search Button Pressed");
        		new SearchPage(frame);
			}
			else if(e.getSource() == teachButton){
				System.out.println("Classes I Teach Button Pressed");
        		new ProfPage(frame);
			}
			else if(e.getSource() == manageButton){
				System.out.println("Management Button Pressed");
        		new ManPage(frame);
			}
		}
	}

	/*
		-----------------------------------------------
		-----------------------------------------------
		----------------------------------------------- 
		-----------------------------------------------
	*/


	public static void main(String[] args){
		JFrame frame = new JFrame();
		HomePage homepage = new HomePage(frame);
	}
}