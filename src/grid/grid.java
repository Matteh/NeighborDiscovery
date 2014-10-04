package grid;
/* Controller split into 2 parts to delegate controlling model to actual drawing
 * grid controls the actual drawing functions
 */

//import all required packages
import java.awt.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import model.Sensor;

public class grid extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int height;
	int width;
	String name;
	ArrayList<Sensor> sensors = new ArrayList<Sensor>();

	//consctructor
	public grid(String n, int w, int h){
		name = n;
		width = w;
		height = h;
		setLayout(new BorderLayout());
		setSize(w,h);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		setVisible(true);
	}
	
	public void drawSensor(ArrayList<Sensor> l){
		sensors = l;
		paint(getGraphics());
	}
	//draw a thick red line from sensor s1 to s2
	public void drawContains(Sensor s1, Sensor s2){
		Graphics2D g2 = (Graphics2D)getGraphics();
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(s1.getX()*3+3,s1.getY()*3+3,s2.getX()*3+3,s2.getY()*3+3);
	}
	
	//return name of grid
	public String getName(){
		return name;
	}
	//clear the grid's view
	public void clear(){
		this.removeAll();
	}
	
	
	//paint all sensors in the list
	//for each sensor acquire its x,y coords
	//calculate the end points of the angle tangents.
	//adjust the values of the coordinates to make the sensors more 
	// visually appealing
	public void paint(Graphics g) {
	    super.paint(g);
	    for(Sensor s: sensors){
	    	g.setColor(s.getColor());
	    	g.fillRect(s.getX()*3,s.getY()*3, 7, 7);
	    	g.drawLine(s.getX()*3+3,s.getY()*3+3, 
	    			s.getX()*3+3 + (int)(s.getRadius()*2  * Math.sin((Math.toRadians((s.getBearing())  - s.getAngle() / 2)))),
	    			s.getY()*3+3 + (int)(s.getRadius()*2  * Math.cos((Math.toRadians((s.getBearing())  - s.getAngle() / 2))))		
	    			);
	    	g.drawLine(s.getX()*3+3,s.getY()*3+3, 
	    			s.getX()*3+3 + (int)(s.getRadius()*2  * Math.sin((Math.toRadians((s.getAngle())/2 + s.getBearing())))),
	    			s.getY()*3+3 + (int)(s.getRadius()*2  * Math.cos((Math.toRadians((s.getAngle())/2 + s.getBearing()))))		
	    			);
	    
	    }
	}
	
	



}
