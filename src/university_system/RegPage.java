package university_system;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class RegPage extends SystemGUI{

	JFrame frame;
	JPanel p, center;	// Reusable panels
	JList<Course> deptList, courseList, selectList; // Three lists used
	DefaultListModel<Course> deptModel, courseModel, selectModel; // Models for Lists
	JButton addButton, rmButton, selectButton; // Buttons
	JComboBox schoolYear, department; // JComboBox's
	GridBagConstraints gc; // Constraints for a GridBagLayout

	/* A few dummy Course Departments and school years */ 
	String[] depots = {"Computer Science","Physics","Chemistry","Mathematics","Psychology","None-Important"};
	String[] years = {"Summer 2016","Fall 2016","Spring 2017"};

	/**
	 * Constructor that generates the Student Course Registration and Drop interface
	 * @param frame Passed as an argument to be the main frame of this GUI
	 * @author Reynaldo
	 */
	public RegPage(JFrame frame){
		this.frame = frame;
		frame.setTitle("University Registration System"); 
		frame.setSize(WIDTH,HEIGHT); // Default Dimensions
		frame.setResizable(false); // Not Resizable 
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		/* Creating buttons */
		addButton = new JButton("Add to schedule");
		rmButton = new JButton("Remove from list");
		selectButton = new JButton("Select");

		/* Button ActionListeners */
		selectButton.addActionListener(new ButtonListener()); //button enabled
		addButton.addActionListener(new ButtonListener()); //button enabled
		rmButton.addActionListener(new ButtonListener()); //button enabled
		homeButton.addActionListener(new ButtonListener()); //button enabled

		/* Combo Boxes */
		schoolYear = new JComboBox(years);
		department = new JComboBox(depots);

		/* Creating lists to be used (A Models manages the list's content)*/
		deptModel = new DefaultListModel<Course>();  
		deptList = new JList<Course>(deptModel); // Department List
		deptList.setVisibleRowCount(10);
		deptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		courseModel = new DefaultListModel<Course>(); // Course List
		courseList = new JList<Course>(courseModel);
		courseList.setVisibleRowCount(10);
		courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	

		selectModel = new DefaultListModel<Course>(); // Selection List
		selectList = new JList<Course>(selectModel);
		selectList.setVisibleRowCount(5);
		selectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		/* ---------------------
		 * A FEW DUMMY COURSES
		 * ---------------------
		 * */
		Course c1 = new Course("3302","Math", "today", "3", "MW", "Javier", 333);
		Course c2 = new Course("3123", "Computer Science", "today", "3", null,  "Johnathan",722);
		Course c3 = new Course("4178","Physics", "today", "3", "MW",  " Prof. Knuth", 2311);
		Course c4 = new Course("2012", "Chemistry", "today", "2", "TR", "Other professor", 2311);

		System.out.println(c4);

		courseModel.addElement(c1);
		courseModel.addElement(c2);
		courseModel.addElement(c3);
		courseModel.addElement(c4);

		for(int i = 1; i <= 20; i++){
			courseModel.addElement(new Course("Course "+i+"", "Course", null, null, null, null, 1111));
		}

		/*
		 * END OF DUMMY COURSES
		 * 
		 * */


		/* North Panel */
		p = createHeaderPanel("Course Registration"); // Creates page header
		frame.add(p, BorderLayout.NORTH);

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
				GridBagConstraints.CENTER,0,0, 0,0,0,0);
		p = new JPanel(new GridBagLayout());
		p.add(department, gc);					
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,0, 0,0,0,0); // Department List
		titleBorder(p, "Department");
		center.add(p, gc);



		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 0,0,0,0);
		p = new JPanel(new GridBagLayout());
		p.add(new JScrollPane(courseList), gc);		

		JPanel temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		temp.add(selectButton);
		gc.gridy = 1;		
		p.add(temp, gc);

		gc = bagConstraints(0,1,1,3, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,1, 0,0,0,0);	// Course List
		titleBorder(p, "Courses");
		center.add(p, gc);



		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 0,0,0,0);
		p = new JPanel(new GridBagLayout());
		temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		temp.add(new JLabel("Academic Year"));
		temp.add(schoolYear);
		p.add(temp,gc);
		gc = bagConstraints(1,0,4,1, GridBagConstraints.BOTH,	// School Year Selection
				GridBagConstraints.CENTER,4,0, 0,0,0,0);
		titleBorder(p, "Select A School Year");
		center.add(p, gc);


		p = new JPanel(new GridBagLayout());
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 0,0,20,0);
		p.add(new JScrollPane(selectList), gc);

		temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		temp.add(addButton);
		temp.add(rmButton);
		gc.gridy = 1;
		p.add(temp, gc);

		gc = bagConstraints(1,1,0,2, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,2, 0,0,0,0);	// Selection List
		titleBorder(p, "Selected Courses");
		center.add(p, gc);



		dashBorder(center); // Adds a dashed Border to central panel
		frame.add(center, BorderLayout.CENTER); // Adds central panel to frame 


		/*
			-----------------------------------------------
			-----------------------------------------------
			----------------------------------------------- 
		 */	

		frame.setVisible(true);

		/*
		 * Mouse Listeners that display a pop up window showing a course description
		 * that the user can see by double clicking in the desired course.
		 */
		courseList.addMouseListener(new MouseAdapter() {	// course, list double click listener
			public void mousePressed(MouseEvent me) {
				JList list =(JList) me.getSource();
				Point p = me.getPoint();
				if (me.getClickCount() == 2) {
					System.out.println("Doubled Clicked! Check Course Description");
					Popup pop = new Popup();
					JTextArea description = new JTextArea(""					// Hypothetical course description
							+ "This course is really cool. You get to experience pretty"
							+ "\nawesome stuff that don't come by usually in other courses."
							+ "\nSo come along! Just remember to bring your sacrifice"
							+ "\non Tuesday on First Week of class. - Mr. Evil.");
					pop.genericPop("COURSE DESCRIPTION", "Course Description", 
							new JScrollPane(description));
				}
			}
		});
		/*
		 * Mouse Listeners that display a pop up window showing a course description
		 * that the user can see by double clicking in the desired course.
		 */
		selectList.addMouseListener(new MouseAdapter() { 	// select list, double click listener
			public void mousePressed(MouseEvent me) {
				JList list =(JList) me.getSource();
				Point p = me.getPoint();
				if (me.getClickCount() == 2) {
					System.out.println("Doubled Clicked! Check Course Description");
					Popup pop = new Popup();
					JTextArea description = new JTextArea(""					// Hypothetical course description
							+ "This course is really cool. You get to experience pretty"
							+ "\nawesome stuff that don't come by usually in other courses."
							+ "\nSo come along! Just remember to bring your sacrifice"
							+ "\non Tuesday on First Week of class. - Mr. Evil.");
					pop.genericPop("COURSE DESCRIPTION", "Course Description", new JScrollPane(description));
				}
			}
		});
	}

	/*	-----------------------------------------------
		-----------------------------------------------
		    Button Action Handler Private Class
		----------------------------------------------- 
		------------------ -----------------------------
	 */	

	/**
	 * Private class that enables interrupts when buttons are
	 * pressed in this interface.
	 * 
	 * @author Reynaldo
	 *
	 */
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(e.getSource() == selectButton){
				System.out.println("Selected Course");
				selectedCourses();
			}
			else if(e.getSource() == addButton){
				System.out.println("Added Course To Schedule");
				removeSelected();
			}
			else if(e.getSource() == rmButton){
				System.out.println("Removed Course From List");
				removeSelected();
			}
			else if(e.getSource() == homeButton){
				System.out.println("Home Button Pressed");
				frame.getContentPane().removeAll();
				new HomePage(frame);
			}
		}
	}

	/*
		-----------------------------------------------
		-----------------------------------------------
		----------------------------------------------- 
		-----------------------------------------------
	 */

	/**
	 * Adds the element currently selected from the course list to the
	 * selected list.
	 * @author Reynaldo
	 */
	public void selectedCourses(){
		this.selectModel.addElement(this.courseList.getSelectedValue());
	}

	/**
	 * Removes element currently selected in the selected list.
	 * @author Reynaldo
	 */
	public void removeSelected(){
		selectModel.removeElement(this.selectList.getSelectedValue());
	}

	/*
	 * NOTE: Other methods will be added to fetch the course info from the database 
	 */


	/*
	 * Main method goes HERE 
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new RegPage(new JFrame());
			}
		});
	}
}