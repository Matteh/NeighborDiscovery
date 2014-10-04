
package controlpanel;

//import all required packages
import controller.Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ControlPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSlider theta,speed;
	JCheckBox auto;
	JButton inc,dec;
	JLabel count, warning;
	int thetaVal;
	int speedVal;
	int size, intersect;
	Controller control;
	JLabel num_sensors,num_intersecting;
	
	
	//constructor
	public ControlPanel(Controller c){
		super();
		setLayout(null);
		try {
    	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if ("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            break;
    	        }
    	    }
    	} catch (Exception e) {
    	    // If Nimbus is not available, you can set the GUI to another look and feel.
    	}
		
		//initialize values
		control = c;
		size = control.getNetSize();
		//intersect = control.getInstersect();
		speedVal = 50;
		thetaVal = 3;
		
		//dynamic label containing number of nodes on grid
		num_sensors = new JLabel("Number of nodes/sensors = " + "  " + size);
		num_sensors.setLocation(20,300);
		num_sensors.setSize(250,20);
		add(num_sensors);
		

		//create jsliders to allow user interaction
		theta = new JSlider();
	    theta.setBorder(BorderFactory.createTitledBorder("K - Sector Values"));
	    theta.setMajorTickSpacing(1);
	    theta.setMaximum(12);
	    theta.setMinimum(3);
	    theta.setValue(3);
	    theta.setPaintTicks(true);
	    theta.setPaintLabels(true);
	    theta.setLocation(0,0);
	    theta.setSize(375,100);
	    add(theta, BorderLayout.SOUTH);
	    
		speed = new JSlider();
	    speed.setBorder(BorderFactory.createTitledBorder("Speed Percentage"));
	    speed.setMajorTickSpacing(10);
	    speed.setMaximum(100);
	    speed.setMinimum(0);
	    speed.setPaintTicks(true);
	    speed.setPaintLabels(true);
	    speed.setLocation(1,100);
	    speed.setSize(375,100);
	    speed.setEnabled(true);
	    add(speed, BorderLayout.SOUTH);

	    
	    count = new JLabel("Inc/Dec Sensors :");
	    count.setSize(120,20);
	    count.setLocation(20,230);
	    add(count);
	    
	    inc = new JButton("+");
	    inc.setSize(40,25);
	    inc.setLocation(150,230);
	    add(inc);
	    
	    dec = new JButton("-");
	    dec.setSize(40,25);
	    dec.setLocation(200,230);
	    add(dec);
	    
	    //JButton Listeners
	    //once pressed corresponding functions 
	    //called from the constructor
	    inc.addActionListener(new ActionListener() {
	  	  public void actionPerformed(ActionEvent e)
	          {
	  		  			control.increase();
	  		  			size = control.getSize();
	  		  			System.out.println(size);
	  		  	update();
	          }
	      });
	    
	    dec.addActionListener(new ActionListener() {
		  	  public void actionPerformed(ActionEvent e)
	          {
		  		  	control.decrease();
		  		  	size = control.getSize();
		  		update();
	  	      }
		});
	     
	    //Chabge listener used for JSliders
	    //the value must change in order for the JSlider to call
	    //controller functions
	    ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
              JSlider source = (JSlider) changeEvent.getSource();
              int index = ((JSlider) source).getValue();
              if (!source.getValueIsAdjusting()&&source == theta){
            	  thetaVal = index;
            	  if(speedVal == 0) speedVal = 1;
            	  control.update(thetaVal, speedVal);}
              else if(!source.getValueIsAdjusting()&&source == speed){
            	  speedVal = index;
            	  if(speedVal == 0) speedVal = 1;
              	  control.update(thetaVal, speedVal);
              	  }
              //System.out.println(speedVal + " " + thetaVal);
            }
            
          };
          //add listeners
          theta.addChangeListener(changeListener);
          speed.addChangeListener(changeListener);
	    
	    
	    //ensure the JComponenents used on the interface are all functioning
	    validate();
		}
		public void update(){
	    	num_sensors.setText("Number of nodes/sensors = " + "  " + size);
	    	//num_intersecting.setText("# of intersecting sensors  =   " + intersect);
	    }

	    //return the users values of speed and theta
	    public int getTheta(){	return thetaVal; }
	    public int getSpeed(){ return speedVal; }
	    
	   
	
	
}