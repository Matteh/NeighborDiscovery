package infobox;

import javax.swing.*;
import controller.Controller;


public class InfoBox extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Controller control;
	int size;
	
	public InfoBox(Controller c){
		super();
		control = c;
		size = control.getNetSize();
		
		setLayout(null);
		//setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

		JLabel num_sensors,num_intersecting;
		num_sensors = new JLabel("Number of nodes/sensors = " + "  " + size);
		num_sensors.setLocation(0,0);
		num_sensors.setSize(200,20);
		
		num_intersecting = new JLabel("# of intersecting sensors  = ");
		num_intersecting.setLocation(0,30);
		num_intersecting.setSize(200,20);
		add(num_sensors);
		add(num_intersecting);
		
	}
	
	
	
	
	

	
//	public static void main(String args[]){
//		JFrame frame = new JFrame();
//		frame.setLayout(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		InfoBox p = new InfoBox();
//		p.setLocation(0,0);
//		p.setSize(500,500);
//		frame.add(p);
//		
//		frame.setVisible(true);
//		frame.setSize(500,500);
//		
//	}
	
}