/**
 * This class has two important methods that create pop-up windows 
 */

package university_system;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Popup extends SystemGUI{
	
	private JPanel panel;
	private String title = "Deleting Course"; //Title for deleting a course
	String message = "Are you sure you want to delete the " // Message for deleting an item (generic)
			+ "selected item?";
	GridBagConstraints gc; // Constraints for a GridBagLayout
	JFrame frame = new JFrame();
	
	// Buttons //
	JButton noButton = new JButton("No");
	JButton yesButton = new JButton("Yes");
	
	
	/**
	 * Constructor enables all possible JButtons used in the popup windows
	 * @author Reynaldo
	 */
	public Popup(){
		yesButton.addActionListener(new ButtonListener()); //button from Superclass
		noButton.addActionListener(new ButtonListener()); //button from Superclass
	}
	
	/**
	 * This method is called when the administrator has the desire to delete a course from the
	 * database. It displays a small warning message, and asks the administrator whether they 
	 * are convinced to continue.
	 */
	public void deletingCoursePop(){
		frame.setTitle(title);
		frame.setSize(400, 150);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Centers to Screen Being Used
 		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); 
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(new JLabel(message));
		frame.add(panel, BorderLayout.NORTH);
		
		panel = new JPanel(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0,10,10,0);
		panel.add(yesButton, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		panel.add(noButton, gc);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		

	}
	
	/**
	 * This method creates a generic pop-up window. The user is free to choose a title, a small message,
	 * and any number of JComponents they want the window to display. The JComponents passed are arranged
	 * in a downwards list. Each row in the list has two column spaces, these have to be filled before the
	 * list moves downward, i.e., 2 components will appear next to each other in the same row, but 5 components
	 * will occupy 2 rows with 2 components each and 1 row with 1 component. The order is determined from 
	 * left to right by the order in which the components are passed as parameters.
	 *  
	 * @param title Title of pop-up window
	 * @param message Message to be display in the pop-up windows
	 * @param options Any number of JComponents that are to be placed in list.
	 * 
	 * @author Reynaldo
	 */
	public void genericPop(String title, String message, JComponent...options){
		frame.setTitle(title);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Centers to Screen Being Used
 		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); 
		frame.setLayout(new BorderLayout()); // Frame Layout
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(new JLabel(message));
		frame.add(panel, BorderLayout.NORTH);
		
		panel = new JPanel(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0,10,10,0);
		for(JComponent stuff : options){
			panel.add(stuff, gc);
			if(gc.gridx % 2 == 0)
				gc.gridx++;
			else{
				gc.gridx = 0;
				gc.gridy ++;
			}
		}
		titleBorder(panel, "A Course");
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 *  Button Action Handler private class, manages all the button
	 * action events in this frame. 
	 * @author Reynaldo
	 *
	 */
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(e.getSource() == yesButton){
				System.out.println("Go to delete GUI");
				frame.dispose();
			}
			else if(e.getSource() == noButton){
				System.out.println("Dispose of window");
				frame.dispose();
			}
		}
	}
}