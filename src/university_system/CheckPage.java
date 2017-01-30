package university_system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class CheckPage extends SystemGUI{

	JFrame frame;
	JPanel p, center, degreeEvalPanel; // Panels
	JButton evaluateButton, calendarButton; // Buttons
	JComboBox major, firstMinor, secondMinor; // JComboBoxes
	GridBagConstraints gc; // Constraints for a GridBagLayout
	JTabbedPane tabbedPane; // Pane to create multiple tabs in one display
	JTable evalTable; //JTable that displays a student evaluation

	/*
	 * Arrays used for JComboBox's in this GUI
	 */
	final String[] majors = {"","Mathematics","Physics","Computer Science",
			"Psychology","Criminal Justice","Economics"};

	/*
	 * Arrays for preliminary tests with JTable, two examples
	 */
	String[] columnTitles = new String[]{"Course #","Courses Required","Credit Hours","Status"};
	Object[][] dataTest = new Object[][]{
		{"3331", "Advanced Object Oriented", 3, "Passed"},
		{"3350", "Automata", 3, "Missing"}
	};

	/*
	 * Column type definition for JTable
	 */
	final Class[] columnClasses = new Class[]{
			String.class, String.class, Integer.class, String.class
	};

	/**
	 * Constructor that generates the Check Records interface for Students. In this interface 
	 * they can see their schedule in a calendar, their transcripts, and work out an automated
	 * degree evaluation that tells them how many courses they still need to take.  
	 * 
	 * @param frame frame Passed as an argument to be the main frame of this GUI
	 * @param center Sets the location of the interface to be either at the center of the screen (if
	 * 		  "CENTER" is passed), or the same position as its caller (anything else is passed)
	 * @author Reynaldo
	 */
	public CheckPage(JFrame frame,String center){
		this.frame = frame;
		frame.setTitle("University Registration System");
		frame.setSize(WIDTH,HEIGHT);
		frame.setLayout(new BorderLayout()); // Frame Layout 
		if(center.equals("CENTER")){
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Centers to Screen Being Used
	 		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		}
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		/* Buttons */
		evaluateButton = new JButton("Evaluate");
		calendarButton = new JButton("Press for Calendar");
		/*
		 *  Button enablers //
		 */
		homeButton.addActionListener(new ButtonListener()); //button enabled
		calendarButton.addActionListener(new ButtonListener()); //button enabled
		evaluateButton.addActionListener(new ButtonListener()); //button enabled
		
		/*
		 * Creating the JComboBox's
		 */
		major = new JComboBox<String>(majors);
		firstMinor = new JComboBox<String>(majors);
		secondMinor = new JComboBox<String>(majors);

		/* North Panel*/		
		p = createHeaderPanel("Records"); // Creates page header 
		frame.add(p, BorderLayout.NORTH);	// Adds header to frame

		tabbedPane = new JTabbedPane();
		tabbedPane.add("Degree Evaluation", createDegreeEvalGUI()); // Create Tab 1
		tabbedPane.add("Class Schedule", createCalendarGUI()); // Create Tab 2
		tabbedPane.add("Transcript", createTranscriptsGUI()); // Create Tab 3

		frame.add(tabbedPane);
		frame.setVisible(true);
		
		/*
		 * Mouse Listener for JTable when double clicking a table element 
		 */
		evalTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table =(JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if (me.getClickCount() == 2) {
					System.out.println("Doubled Clicked! Check Course Description");
					Popup pop = new Popup();
					JTextArea description = new JTextArea(""
							+ "This course is really cool. You get to experience pretty"
							+ "\nawesome stuff that don't come by usually in other courses."
							+ "\nSo come along! Just remember to bring your sacrifice"
							+ "\non Tuesday on First Week of class. - Mr. Evil.");
					pop.genericPop("COURSE DESCRIPTION", "Course Description", description);
				}
			}
		});
	}

	/**
	 * Private class that enables interrupts when buttons are
	 * pressed in this interface.
	 * 
	 * @author Reynaldo
	 */
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(e.getSource() == homeButton){
				System.out.println("Registration Button Pressed");
				frame.getContentPane().removeAll();
				new HomePage(frame);
			}
			else if(e.getSource() == calendarButton){
				calendar = new JCalendar(true); // from SystemGUI superclass
			}
			else if(e.getSource() == evaluateButton){
				System.out.println("Evaluate Degree");
			}
		}
	}

	/**
	 * This method creates and returns the panel used in the first tab for
	 * a JTabbedPane: evaluation degree tab.
	 * @return JPanel that displays an interface for degree evaluation 
	 * @author Reynaldo
	 */
	private JPanel createDegreeEvalGUI(){

		/*
		 * Adding JTABLE MODEL to center panel
		 */
		DefaultTableModel model = new DefaultTableModel(dataTest, columnTitles){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
			@Override
			public Class<?> getColumnClass(int columnIndex){
				return columnClasses[columnIndex];
			}
		};

		/*
		 * Initializing JTable with model
		 */
		evalTable = new JTable(model);
		evalTable.setPreferredScrollableViewportSize(new Dimension(500,50));
		evalTable.setFillsViewportHeight(true);

		/* Central Panel */
		center = new JPanel(new GridBagLayout());

		/*	
			GridBagConstraints for Central Panel
		 */
		JPanel temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p = new JPanel(new GridBagLayout()); // Constraints Basis
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5); // Major Selection
		temp.add(new JLabel("Majors: "));
		temp.add(major);
		p.add(temp, gc);
		
		temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		gc = bagConstraints(0,1,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5); // Minor Selection				
		temp.add(new JLabel("Minor: "));
		temp.add(firstMinor);
		p.add(temp, gc);
		
		temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		gc = bagConstraints(0,3,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 5,5,5,5); // Evaluate Button				
		temp.add(evaluateButton);

		p.add(temp, gc);
		
		titleBorder(p, "My Degree Evaluation"); // A titled Border
		gc = bagConstraints(0,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,0,0, 0,0,0,0); // Adding to center panel
		center.add(p, gc);

		/*
		 * Adding the JTable to center panel
		 */
		p = new JPanel(new BorderLayout()); // Re-using p panel
		p.add(new JScrollPane(evalTable), BorderLayout.CENTER);
		titleBorder(p, "Search Results"); // A titled Border

		gc = bagConstraints(0,1,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER,1,1, 0,0,0,0); // Last Name Label
		center.add(p, gc); // Added JTable to center panel
		frame.add(center, BorderLayout.CENTER);

		return center; // return center panel
	}
	
	
	/**
	 * This method creates and returns the panel used in the second tab for 
	 * a JTabbedPane: schedule/calendar tab.
	 * 
	 * @return JPanel that has a calendar displaying a school schedule
	 * @author Reynaldo
	 */
	private JPanel createCalendarGUI(){
		p = new JPanel(new GridBagLayout());
		p.add(new JLabel("This is just a test for a future calendar: Student Calendar "));
		p.add(calendarButton);
		return p;
	}

	/**
	 * This method creates and returns the panel used in the third tab for 
	 * a JTabbedPane: transcript tab.
	 * 
	 * @return JPanel that displays an interface a student transcript
	 * @author Reynaldo
	 */
	private JPanel createTranscriptsGUI(){
		p = new JPanel((new GridBagLayout()));
		p = transcripts(); // From SystemGUI.java
		return p;
	}

	/*
	 * Main method goes HERE
	 */
	public static void main(String[] args){
		JFrame frame = new JFrame();
		CheckPage mypage = new CheckPage(frame, "NONE");
	}
}