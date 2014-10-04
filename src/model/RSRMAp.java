package model;
import java.util.ArrayList;
import java.util.Random;

import controller.Controller;

public class RSRMAp {
	//RSRMAp method given 2 sensors, a integer that determines the angle on the sensors and a userDelay which the user controls in the gui.
	//userDelay enables the user to control the speed of the iterations.
	//Basic implementation of RSRMAp
	//Receives d, a parameter that handles delay for the sensor
	public void mech(Sensor s1, Sensor s2, int k, int d, long userDelay, Controller cont, ArrayList<Sensor> net){
		boolean b = false;
		Random random = new Random();
		int rot = d;
		//handles rotation direction
		int r = random.nextInt(2);
		if (r == 1){
			rot = rot - 2*rot;
		}
		for (int j = 0; j < d; j++){
			for (int i = 0; i < k-1; i++){
				if ((s1.contains(s2)) && (s2.contains(s1))){
					b = true;
					System.out.println();
					System.out.println("Sensor " + s1.getName() + " connected to Sensor " + s2.getName() + " in " + (j * k + i) + " iterations");
					//used for testing purposes
					s1.time = (j * k + i);
					s2.time = (j * k + i);
					
					break;
				}
				else {
					s1.setBearing(s1.getAngle() + s1.getBearing());
					s2.setBearing(rot + s2.getBearing());
					
					//Bearing will go over 360, this will ensure the angle is reset so it stays between 0 and 360 degrees
					while (s1.getBearing() > 360){
						s1.setBearing(s1.getBearing() - 360);
					}
					while (s2.getBearing() > 360){
						s2.setBearing(s2.getBearing() - 360);
					}
					while (s1.getBearing() < 0){
						s1.setBearing(s1.getBearing() + 360);
					}
					while (s2.getBearing() < 0){
						s2.setBearing(s2.getBearing() + 360);
					}
					cont.posSensors(net);
					try {
						Thread.sleep(userDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if (b)
				break;
		}
		if (!b) {
			System.out.println();
			System.out.println("Connection Failed");
		}
		cont.posSensors(net);
	}
	
	public void doRSRMAp(Network net, int k, int d, long userDelay, Controller cont){
		Random random = new Random();
		ArrayList<Sensor> tempNet = new ArrayList<Sensor>(net.getNet());
		//Performs RSRMAp on the network of sensors
		for (int i = 0; i < tempNet.size(); i += 2){
			if (random.nextBoolean()){
				mech(tempNet.get(i), tempNet.get(i+1), k, d, userDelay, cont, net.getNet());
			}
			else{
				mech(tempNet.get(i+1), tempNet.get(i), k, d, userDelay, cont, net.getNet());
			}
		}
		cont.posSensors(net.getNet());
	}
}
