/**
 * This small class constructs the interface used when the administrator
 * chooses to Edit an existing course. In our application, this option 
 * is found in the Management Page. 
 * 
 * @author Reynaldo
 */

package university_system;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EditCourse{

	private JFrame frame;
	private JPanel p; 
	private JButton cancelButton = new JButton("Cancel");
	private JButton saveButton = new JButton("Save");
	private GridBagConstraints gc;

	private JTextField[] textFields = new JTextField[]{ new JTextField(15),new JTextField(15),new JTextField(15),
			new JTextField(15),new JTextField(15), new JTextField(15)};

	private JLabel[] labels = new JLabel[]{new JLabel("Course Name: "),new JLabel("Course #: "),
			new JLabel("CRN: "),new JLabel("Days Offered: "),new JLabel("Hours: "),
			new JLabel("Instructor: ")};

	public EditCourse(){
		frame = new JFrame();
		frame.setTitle("Editing a Course");
		frame.setSize(350, 470);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Centers to Screen Being Used
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); 
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.add(new JLabel("Editing a Course"));
		frame.add(p, BorderLayout.NORTH);

		/*
		 * Filling TextFields with information from a Course Object
		 */
		textFields[0].setText("Advanced Object Oriented Programming");
		textFields[1].setText("3331");
		textFields[2].setText("27482");
		textFields[3].setText("TR");
		textFields[4].setText("1.20");
		textFields[5].setText("Omar Baddredin");

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
		p.add(saveButton, gc);
		gc.gridx++;
		p.add(cancelButton, gc);

		frame.add(new JScrollPane(p), BorderLayout.CENTER);
		frame.setVisible(true);
		
		/*
		 * Button Listeners
		 */
		saveButton.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("The selected course has been edited");	// TODO
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
				System.out.println("Cancel Button Pressed");	// TODO 
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						frame.dispose();
					}
				});
			}
		});
	}


	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EditCourse();
			}
		});
	}
}