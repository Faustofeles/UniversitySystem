package university_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

abstract class SystemGUI{
	
	protected JButton backButton = new JButton("BACK");
	protected JButton homeButton = new JButton("HOME");
	
	final protected int WIDTH = 1000; // Default screen width
	final protected int HEIGHT = 500; // Default screen Height
	
	protected JFrame mainFrame; // Frame shared by most interfaces
	protected User user; // User to be passed to System **** May be moved to Login Page ****
	JCalendar calendar = new JCalendar(false); // shhh... don't mind me
	
	/**
	 * Adds JComponent using a GridBagConstraint to a JPanel using GridBagLayout 
	 * @param p JPanel to add to
	 * @param c JComponent being added
	 * @param constraints GridBagConstraints for GridBagLayout
	 * @author Reynaldo 
	 * 
	 */
 	public void addToPanel(JPanel p, JComponent c, Object constraints){
 		p.add(c, constraints);
 	}
 	
 	/**
 	 * Adds JComponent to JPanel, the main purpose is readability
 	 * @param p JPanel to add to
 	 * @param c JComponent being added
 	 * @author Reynaldo 
 	 */
 	public void addToPanel(JPanel p, JComponent c){
 		p.add(c);
 	}
 	
 	/**
 	 * Sets the location of where a JComponent is to be added to another JComponent,
 	 * typically a JPanel type.
 	 * @param p JComponent to be added to
 	 * @param component JComponent being added
 	 * @param x Position in the horizontal
 	 * @param y Position in the vertical
 	 * @author Reynaldo 
 	 */
 	public void setComponent(JComponent p, JComponent component, int x, int y){
		component.setLocation(x,y);
		System.out.println("Added to center");
		p.add(component);
	}
 	
 	/**
 	 * Sets the location of where a JComponent is to be added to a JFrame,
 	 * typically a JPanel type.
 	 * @param f JFrame to be added to
 	 * @param component JComponent being added
 	 * @param x Position in the horizontal
 	 * @param y Position in the vertical
 	 * @author Reynaldo 
 	 */
 	public void setComponent(JFrame f, JComponent component, int x, int y){
		component.setLocation(x,y);
		f.add(component);
	}
 	
 	/**
 	 * Creates a dashed border around a JComponent
 	 * @param c JComponent 
 	 * @author Reynaldo
 	 */
 	public void dashBorder(JComponent c){
 		Border dash = BorderFactory.createDashedBorder(Color.BLACK);
 		c.setBorder(dash);
 	}
 	
 	/**
 	 * Creates a dashed border with a title around a JComponent
 	 * @param c JComponent
 	 * @param str title to be placed along border
 	 */
 	public void titleBorder(JComponent c, String str){
 		Border dash = BorderFactory.createDashedBorder(Color.BLACK);
 		Border title = BorderFactory.createTitledBorder(dash, str);
 		c.setBorder(title);
 	}
	
 	/**
 	 * This method receives parameters that are passed to a GridBagConstraints
 	 * object. Its purpose its to facilitate the writing and cleaningless of the
 	 * code as the GridBagLayout is used extensively across our interface.
 	 * 
 	 * @param gridx From left-right what's the component's grid position in the horizontal
 	 * @param gridy From left-right what's the component's grid position in the vertical
 	 * @param gridwidth How many grid spaces to occupy in the horizontal
 	 * @param gridheight How many grid spaces to occupy in the vertical
 	 * @param fill It determines whether to resize the component, if so,how.
 	 * @param anchor Where, within display area, to place the component.
 	 * @param weightx Specifies how to distribute extra horizontal space.
 	 * @param weighty Specifies how to distribute extra vertical space.
 	 * @param insets1 Upper external padding of the component  
 	 * @param insets2 Left external padding of the component
 	 * @param insets3 Lower external padding of the component
 	 * @param insets4 Right external padding of the component
 	 * 
 	 * @return A GridBagConstraints Object
 	 * @author Reynaldo
 	 * @see GridBagConstraint
 	 */
 	public GridBagConstraints bagConstraints(int gridx, int gridy, int
		gridwidth,int gridheight, int fill, int anchor, double weightx, double
		weighty,int insets1, int insets2, int insets3, int insets4){

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.fill = fill;
		constraints.anchor = anchor;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.insets = new Insets(insets1, insets2, insets3,insets4);
		
		return constraints;
	}

 	/**
 	 * Creates the header panel that most interfaces used in this application.
 	 * 
 	 * @param title The title of the header panel
 	 * @return A JPanel containing the header 
 	 * @author Reynaldo
 	 */
	public JPanel createHeaderPanel(String title){
		
		GridBagConstraints gc = new GridBagConstraints(); // bag constraints
		JPanel bag = new JPanel(new GridBagLayout()); // grid bag layout
		
		/* University Name */
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addToPanel(p, new JLabel("The University of Texas at El Paso"));
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER, 1,1,0,0,0,0);
		bag.add(p, gc);
		
		/* Page Title */
		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addToPanel(p, new JLabel(title));
		gc = bagConstraints(1,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER, 1,1,0,0,0,0);
		bag.add(p, gc);
	
		/* Student info and navigation buttons */
		p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addToPanel(p, new JLabel("John Smith")); // Test name, subject to change
		addToPanel(p, new JLabel("ID: 80493277")); // Test name, subject to change
		addToPanel(p, this.backButton); 
		addToPanel(p, homeButton); 
		gc = bagConstraints(2,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER, 1,1,0,0,0,0);
		bag.add(p, gc);

		return bag;
	}
	
	/**
	 * This method builds the transcripts of a student in our application.
	 * In the future, it will receive a User type parameter that will specify
	 * how this method should behave.
	 * @return Returns the JPanel containing the student transcript
	 */
	public JPanel transcripts(){		
		JPanel bag = new JPanel(new GridBagLayout());
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(4,0));
		GridBagConstraints gc = new GridBagConstraints(); // bag constraints
		
		gc = bagConstraints(0,0,0,0, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 0,0,0,0);
		bag.add(new JLabel("UNOFFICIAL TRANSCRIPT"), gc);
		titleBorder(bag, "General Information");
		mainPanel.add(new JLabel("UNOFFICIAL TRANSCRIPT"), BorderLayout.NORTH);
		
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		bag = new JPanel(new GridBagLayout());
		bag.add(new JLabel("School: University of Texas at El Paso"), gc);
		gc.gridy++;
		bag.add(new JLabel("Address: 500 W University Ave, El Paso, TX 79902"), gc);
		p.add(bag);
		
		bag = new JPanel(new GridBagLayout());
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		bag.add(new JLabel("Student:_______?________"), gc);
		gc.gridy++;
		bag.add(new JLabel("Classification:_______?________"), gc);
		gc.gridy++;
		bag.add(new JLabel("Expected Graduation:_______?________"), gc);
		titleBorder(bag, "Pesonal Info");
		p.add(bag);
	
		bag = new JPanel(new GridBagLayout());
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5);
		bag.add(new JLabel("Courses Taken"), gc);
		gc.gridy++;
		bag.add(new JLabel("Course:_______?________"), gc);
		gc.gridx++;
		bag.add(new JLabel("Passed:_______?________"), gc);
		gc.gridx--;
		gc.gridy++;
		bag.add(new JLabel("Course:_______?________"), gc);
		gc.gridx++;
		bag.add(new JLabel("Passed:_______?________"), gc);

		titleBorder(bag, "Academic info Info");
		p.add(bag);
		mainPanel.add(new JScrollPane(p), BorderLayout.CENTER);
		
		return mainPanel;
	}
}