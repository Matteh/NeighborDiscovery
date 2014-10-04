package buttonPanel;

//import required packages
import controller.Controller;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")

//inherit from jpanel
public class buttonPanel extends JPanel{
	
	JButton start,stop,reset;
	Controller control;
	//constructor
	public buttonPanel(Controller c){
		super();
		setLayout(new FlowLayout());
		control = c;
		//intiliaze JButtons 
		//set size
		
		start = new JButton("Start");
		start.setSize(100,50);
		
		//add listeners to button
		//will call functions in the controller
		start.addActionListener(new ActionListener() {
	  	  public void actionPerformed(ActionEvent e){
	  		  control.start();
	      }
	    });
		add(start);
		
		
		reset = new JButton("Reset");
		reset.setSize(100,50);
		reset.setLocation(175,0);
		reset.setEnabled(true);
		
		//add listeners to button
		//will call functions in the controller
		reset.addActionListener(new ActionListener() {
	  	  public void actionPerformed(ActionEvent e)
	          {
	  		  	control.reset();	  		  	
	          }
	      });

		add(reset);
		
		//add listeners to button
		//will call functions in the controller
		stop = new JButton("CLOSE");
		stop.setSize(100,50);
		stop.setBackground(Color.red);
		stop.addActionListener(new ActionListener() {
		  	  public void actionPerformed(ActionEvent e)
	          {
	  		  	control.stop();
	          }
	      });
		add(stop);
	}
	

}

