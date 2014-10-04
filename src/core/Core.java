package core;

//import required pacakges

import components2.TabbedPaneInterface;
import controlpanel.ControlPanel;
import buttonPanel.buttonPanel;
import controller.Controller;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;


//main gui

public class Core extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Controller control ;
	TabbedPaneInterface censor_display;
	//InfoBox info_display ;
	ControlPanel control_panel ;
	buttonPanel buttons;
		
	public Core (){
		super();
		
		//initialize all componenets of jframe
		control = new Controller();
		censor_display = new TabbedPaneInterface(control);
		//info_display = new InfoBox(control);
		control_panel = new ControlPanel(control);
		buttons = new buttonPanel(control);
		
					
		//prep frame for components		
		setLayout(null);
		setResizable(false);
		setSize(900,670);
		setVisible(true);
				
		//create panels
		//set location and size
		
		
		censor_display.setLocation(20,10);
		censor_display.setSize(450,600);
		add(censor_display);
		

		control_panel.setSize(400,350);
		control_panel.setLocation(490,40);
		add(control_panel);
		
		buttons.setLocation(530,450);
		buttons.setSize(300,100);
		add(buttons);

		
	}
	

	//Main method of the entire program
	public static void main (String args[]){
		//run the ui with the nimb
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		    Core gui = new Core();
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
//		Core gui = new Core();
//		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
