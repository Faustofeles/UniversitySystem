package university_system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class SearchPage extends SystemGUI{

	JFrame frame; 
	JTextField lastNameTxt, firstNameTxt, idTxt, creditLow, creditHigh;	// All important text fields
	JComboBox<?> ageLow, ageHigh, major, gradDate, college, classification; // All combo boxes used
	JButton searchButton, checkRecordButton; //Buttons
	JLabel resultsDisplay; // Displays the results when searching
	JPanel p, center; // p panel is reused over and over again and placed in center panel
	GridBagConstraints gc; // For managing GridBaLayout

	JTable searchTable; // The JTable

	/*
	 * Arrays for preliminary tests with JTable, two examples
	 */
	String[] columnTitles = new String[]{"#","Last Name","First Name","Student ID","Age","Major","Credits","Classification",
	"Exp. Graduation"};
	Object[][] dataTest = new Object[][]{
		{1, "Martinez", "Reynaldo", "80493277", 24, "Computer Science", 119, "Senior", "Dec 2018"},
		{2, "Valles", "Paulina", "80423287", 22, "Psychology", 119, "Junior", "May 2018"},
	};

	/*
	 * Column type definition for JTable
	 */
	final Class[] columnClasses = new Class[]{
			Integer.class, String.class, String.class, String.class, Integer.class,
			String.class, Integer.class, String.class, String.class
	};

	/*
	 * Arrays used for JComboBox's in this GUI
	 */
	String[] ages = createAgesBox();
	final String[] majors = {"","Mathematics","Physics","Computer Science",
			"Psychology","Criminal Justice","Economics"};
	final String[] colleges = {"","Science","Engineering", "Arts", "Business"};
	final String[] gradDates = {"","May 2017","Dec 2017","May 2018","Dec 2018"};
	final String[] classifications = {"","Freshman","Sophomore","Junior","Senior"};

	/**
	 * Constructor for the Search Page
	 * @param frame Passed as an argument to be the main frame of this GUI
	 * @author Reynaldo
	 */
	public SearchPage(JFrame frame){

		this.frame = frame; //passed frame
		frame.setTitle("University Registration System");
		frame.setSize(WIDTH,HEIGHT);
		//		frame.setResizable(false);
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/* Creating buttons */
		searchButton = new JButton("Search");
		checkRecordButton = new JButton("Check Records");

		// Button enablers //
		homeButton.addActionListener(new ButtonListener()); //button enabled
		searchButton.addActionListener(new ButtonListener()); //button enabled

		/* Creating the TextFields */
		lastNameTxt = new JTextField(10);
		firstNameTxt = new JTextField(10);
		idTxt = new JTextField(10);
		creditLow = new JTextField(5);
		creditHigh = new JTextField(5);

		/*
		 * Creating ComboBoxes
		 */
		ageLow = new JComboBox<String>(ages);
		ageHigh = new JComboBox<String>(ages);
		gradDate = new JComboBox<String>(gradDates);
		college = new JComboBox<String>(colleges);
		classification = new JComboBox<String>(classifications);
		major = new JComboBox<String>(majors);

		/*
		 * Adding JTABLE MODEL to center panel
		 */
		DefaultTableModel model = new DefaultTableModel(dataTest, columnTitles){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex)
			{
				return columnClasses[columnIndex];
			}
		};

		/*
		 * Initializing JTable with model
		 */
		searchTable = new JTable(model);
		searchTable.setPreferredScrollableViewportSize(new Dimension(500,50));
		searchTable.setFillsViewportHeight(true);

		/*
		 * ===============================================================
		 * 		    Creating the Panels and Frame Structure
		 * ===============================================================
		 */


		/* Header Panel */

		p = createHeaderPanel("Search Students"); // Creates page header
		frame.add(p, BorderLayout.NORTH);

		/* Central Panel */

		center = new JPanel(new GridBagLayout());

		/*  -----------------------------------------------
		    -----------------------------------------------
		          GridBagConstraints for Central Panel
		    -----------------------------------------------
		    ----------------------------------------------- 
		 */

		/*
		 *  Search Student By Personal Information 
		 */
		p = new JPanel(new GridBagLayout()); // Constraints Basis
		gc = bagConstraints(0,0,2,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 5,5,5,5); // Last Name Label
		p.add(new JLabel("Last Name "), gc); 					

		gc.gridx = 2; // Last Name Text Field
		p.add(lastNameTxt, gc);

		gc.gridx = 4; // First Name Label
		p.add(new JLabel("First Name "), gc);					

		gc.gridx = 6; // First Name Text Field
		p.add(firstNameTxt, gc);

		gc.gridx = 8; // Student ID Label
		p.add(new JLabel("Student ID "), gc);					

		gc.gridx = 10; // Student ID Text Field
		p.add(idTxt, gc);

		gc.gridwidth = 1;
		gc.gridx = 12;	// Age Label
		p.add(new JLabel("Age "), gc);					

		gc.gridx = 13; // ageLow ComboBox
		p.add(ageLow, gc);

		gc.gridx = 14; // Age Label
		p.add(new JLabel("To "), gc);					

		gc.insets = new Insets(5,5,5,35);
		gc.gridx = 15; // ageLow ComboBox
		p.add(ageHigh, gc);

		titleBorder(p, "By Personal Information"); // A titled Border

		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 0,0,0,0); // Adding to center panel
		center.add(p, gc);


		/*
		 *  Search Students By Group
		 */
		p = new JPanel(new GridBagLayout()); // Constraints Basis
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 5,5,5,5); // Major Label
		p.add(new JLabel("Major "), gc); 					

		gc.gridx = 1; // Major JComboBox
		p.add(major, gc); 

		gc.gridx = 2; // College Label
		p.add(new JLabel(" College "), gc); 

		gc.gridx = 3; // College JComboBox
		p.add(college, gc); 

		gc.gridx = 4; 
		gc.gridwidth = 2; // Expected Graduation Label
		p.add(new JLabel(" Expected Graduation"), gc); 

		gc.gridx = 6; // Expected Grad. JComboBox
		gc.gridwidth = 1;
		p.add(gradDate, gc); 

		gc.gridx = 7; // Credit Hours Label
		p.add(new JLabel(" Credit Hours"), gc); 

		gc.gridx = 8; // creditLow Text Field
		p.add(creditLow, gc); 

		gc.gridx = 9; 
		p.add(new JLabel("To "), gc); 

		gc.gridx = 10; // creditHigh Text Field
		p.add(creditHigh, gc); 

		gc = bagConstraints(0,1,2,0, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 5,45,5,5); // Student Classification Label
		p.add(new JLabel("Student Classification "), gc); 

		gc.insets = new Insets(5,5,5,5);
		gc.gridx = 2; // Student Class. JComboBox
		p.add(classification, gc); 

		gc.gridx = 8;
		gc.gridwidth = 3;
		gc.insets = new Insets(5,20,5,20);

		p.add(searchButton, gc); 

		titleBorder(p, "By Group");	// A titled Border
		gc = bagConstraints(0,1,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 0,0,0,0); // Adding to center panel
		center.add(p, gc);	


		titleBorder(center, "Search Students"); // Adds a dashed Border to central panel
		frame.add(center, BorderLayout.CENTER); // Adds central panel to frame 

		/*
		 * Adding JTable to center panel
		 */
		p = new JPanel(new BorderLayout()); // Re-using p panel
		p.add(new JScrollPane(searchTable), BorderLayout.CENTER);
		titleBorder(p, "Search Results"); // A titled Border

		gc = bagConstraints(0,2,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 0,0,0,0); // Last Name Label
		center.add(p, gc); // Added JTable to center panel

		/*
		 * Mouse Listener that links to the check records interface to check
		 * an individual student's record (once selected from the list).
		 */
		searchTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table =(JTable) me.getSource(); 
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if (me.getClickCount() == 2) {
					System.out.println("Doubled Clicked! Check Records");
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							JFrame f = new JFrame(); 
							new CheckPage(f, "CENTER"); // Calling CheckPage
						}
					});
				}
			}
		});

		frame.add(center, BorderLayout.CENTER); // Add center panel to frame
		frame.setVisible(true);	// Set frame visible
	}


	/*	-----------------------------------------------
		-----------------------------------------------
		    Button Action Handler Private Class
		----------------------------------------------- 
		-----------------------------------------------
	 */	
	
	/**
	 * Private class that enables interrupts when buttons are
	 * pressed in this interface.
	 * @author Reynaldo
	 *
	 */
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(e.getSource() == homeButton){
				System.out.println("Registration Button Pressed");
				frame.getContentPane().removeAll();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run(){
						new HomePage(frame);
					}
				});
			}
			else if(e.getSource() == searchButton){
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run(){
						System.out.println("Search database for students by indicated fields");
					}
				});
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
	 * Creates the temporary array of ages to test interface.
	 * @return A string array that has a list of numbers as strings
	 */
	public String[] createAgesBox(){
		String[] t = new String[100];
		for(int i = 0; i < t.length; i++)
			t[i] = ""+(16+i)+"";
		return t;
	}


	/*
	 * Main method goes HERE 
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SearchPage(new JFrame());
			}
		});
	}
}