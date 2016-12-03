package university_system;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

abstract class SystemGUI{
	
	protected JButton backButton = new JButton("BACK");;
	protected JButton homeButton = new JButton("HOME");;
	protected JFrame mainFrame;
	protected User user;
	
//	public SystemGUI(){
//		JFrame mainFrame = new JFrame();
//		User user;
//
//	}


 	public void addToPanel(JPanel p, JComponent c, Object constraints){
 		p.add(c, constraints);
 	}
 	
 	public void addToPanel(JPanel p, JComponent c){
 		p.add(c);
 	}
 	
 	public void setComponent(JComponent p, JComponent component, int x, int y){
		component.setLocation(x,y);
		System.out.println("Added to center");
		p.add(component);
	}
 	
 	public void setComponent(JFrame f, JComponent component, int x, int y){
		component.setLocation(x,y);
		f.add(component);
	}
 	
 	public void dashBorder(JComponent c){
 		Border dash = BorderFactory.createDashedBorder(Color.BLACK);
 		c.setBorder(dash);
 	}
 	
 	public void titleBorder(JComponent c, String str){
 		Border dash = BorderFactory.createDashedBorder(Color.BLACK);
 		Border title = BorderFactory.createTitledBorder(dash, str);
 		c.setBorder(title);
 	}
	
 	/**
 	 * This method returns the GridBagConstraints for a GridBagLayout
 	 * */
	public GridBagConstraints bagConstraints(int gridx, int gridy, int
		gridwidth,int gridheight, int fill, int anchor, int weightx, int
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
		addToPanel(p, new JLabel("John Smith"));
		addToPanel(p, new JLabel("ID: 80493277"));
		addToPanel(p, this.backButton);
		addToPanel(p, homeButton);
		gc = bagConstraints(2,0,1,1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER, 1,1,0,0,0,0);
		bag.add(p, gc);

		return bag;
	}
	
}