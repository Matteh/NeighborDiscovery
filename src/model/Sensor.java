package model;

import java.awt.Color;

public class Sensor {
	
	private int name;
	private int locX;
	private int locY;
	private int radius = 142;
	private int angle;
	private int bearing;
	private boolean contained = false;
	private Color color;
	public int time = -1;
	
	
	//Constructor with specific settings.
	public Sensor(int n, int x, int y, int a, int b, Color c){
		name = n;
		locX = x;
		locY = y;
		angle = a;
		bearing = b;
		color = c;
	}
	
	public int getName(){ return name; }
	public int getX(){ return locX; }
	public int getY(){ return locY; }
	public int getRadius() { return radius; }
	public int getAngle(){ return angle; }
	public int getBearing(){ return bearing; }
	public int getK() { return (360 / angle); }
	public boolean getContained() { return contained; }
	public Color getColor(){ return color; }
	
	public void setAngle(int a){ angle = a; }
	public void setAnglebyK(int k){ angle = (360 / k); }
	public void setRadius(int r){ radius = r; }
	public void setBearing(int b){ bearing = b; }
	public void setContained(boolean c){ contained = c; }
	
	//Sensor message passing function
	public void log(String msg){ System.out.println("Sensor " + name + ":  " + msg); }
	
	public String toString(){
		return ("Sensor " + name + " at position (" + locX + "," + locY + ") with an angle of " + angle + " and with a bearing of " + bearing + " degrees from North");
	}
	
	//Method that checks if another sensor is within the sector created by another this sensor
	public boolean inRange(Sensor sen){
		//Checking if the sensor is in range		
		if ((Math.pow(Math.abs(sen.getX()) - this.getX(), 2)) + (Math.pow(Math.abs(sen.getY()) - this.getY(), 2)) > Math.pow(this.getRadius(), 2)){
			return false;
		}
		return true;
	}
	public boolean contains(Sensor sen){
		
		if (!(this.inRange(sen))){
			return false;
		}
		/*The following statements check which quadrant the sensor is in based on the position of this sensor.
		 *Using arctan, the statements calculate the angle created by the y axis (0 degrees in this case, used for bearing) and
		 *a line from this sensor to the given sensor. The method then checks if it is within the bounds of the angle of this sensor.
		 *The java atan function only return values from -90 to 90 degrees so a shift must be used.
		 *Since tan is negative when the angle is between 91 and 180 and between 271 and 360, the absolute value is used
		 *When arctan is calculated the result is in radians and then is converted to degrees.
		 */
		
		//Checks if the sensor is in the top right quadrant (bearing of 1 to 90 degrees)
		if (sen.getY() > this.getY() && sen.getX() > this.getX()){
			if ((180/Math.PI) * Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY())) < (this.getBearing() - this.getAngle()/2)){
				return false;
			}
			if ((180/Math.PI) * Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY())) > (this.getBearing() + this.getAngle()/2)){
				return false;
			}
		}
		//Checks if the sensor is in the bottom right quadrant (bearing of 91 to 180 degrees)
		else if (sen.getY() < this.getY() && sen.getX() > this.getX()){
			if ((180 - (180/Math.PI) * Math.abs(Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY())))) < (this.getBearing() - this.getAngle()/2)){
				return false;
			}
			if ((180 - (180/Math.PI) * Math.abs(Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY())))) > (this.getBearing() + this.getAngle()/2)){
				return false;
			}
		}
		//Checks if the sensor is in the bottom left quadrant (bearing of 181 to 270 degrees)
		else if (sen.getY() < this.getY() && sen.getX() < this.getX()){
			if ((180 + (180/Math.PI) * Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY()))) < (this.getBearing() - this.getAngle()/2)){
				return false;
			}
			if ((180 + (180/Math.PI) * Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY()))) > (this.getBearing() + this.getAngle()/2)){
				return false;
			}
		}
		//Checks if the sensor is in the top left quadrant (bearing of 271 to 360)
		else if (sen.getY() > this.getY() && sen.getX() < this.getX()){
			if ((360 - (180/Math.PI) * Math.abs(Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY())))) < (this.getBearing() - this.getAngle()/2)){
				return false;
			}
			if ((360 - (180/Math.PI) * Math.abs(Math.atan((sen.getX() - this.getX()) / (sen.getY() - this.getY())))) > (this.getBearing() + this.getAngle()/2)){
				return false;
			}
		}
		sen.setContained(true);
		return true;
	}
	
}


