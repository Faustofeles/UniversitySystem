/**
 * This small class constructs the interface used when the administrator
 * chooses to create a new course in the data base. In our application, 
 * this option is found in the Management Page. 
 * 
 * @author Reynaldo
 */


package university_system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreateCourse{

	private JFrame frame;
	private JPanel p; 
	private JButton createButton = new JButton("Create");
	private JButton cancelButton = new JButton("Cancel");
	private GridBagConstraints gc;

	private JTextField[] textFields = new JTextField[]{ new JTextField(15),new JTextField(15),new JTextField(15),
			new JTextField(15),new JTextField(15), new JTextField(15)};

	private JLabel[] labels = new JLabel[]{new JLabel("Course Name: "),new JLabel("Course #: "),
			new JLabel("CRN: "),new JLabel("Days Offered: "),new JLabel("Hours: "),
			new JLabel("Instructor: ")};

	public CreateCourse(){
		frame = new JFrame();
		frame.setTitle("Creating a New Course");
		frame.setSize(350, 470);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Centers to Screen Being Used
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); 
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.add(new JLabel("Creating a New Course"));
		frame.add(p, BorderLayout.NORTH);

		p = new JPanel(new GridBagLayout());
		gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.gridheight = 1;
		gc.weightx = 1;
		gc.weighty = 1;

		for(int j = 0; j < labels.length; j++){
			p.add(labels[j], gc);
			gc.gridx++;
			p.add(textFields[j], gc);
			gc.gridx--;
		}
		gc.gridy = 13;
		gc.gridx++;
		gc.gridwidth = 1;
		p.add(createButton, gc);
		gc.gridx++;
		p.add(cancelButton, gc);

		frame.add(new JScrollPane(p), BorderLayout.CENTER);
		
		frame.setVisible(true);
		/*
		 * Button Listeners
		 */

		createButton.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("A new course is created in the data base.");
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
				System.out.println("Cancel Button Pressed.");	// TODO Erase sysout
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						frame.dispose();
					}
				});
			}
		});
	}

	/*
	 * Main method that displays CreateCourse individually 
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CreateCourse();
			}
		});
	}
}
