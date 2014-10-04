package model;

import java.util.ArrayList;
import java.util.Random;

import controller.Controller;

public class ARA {
	//ARA method given 2 sensors, a integer that determines the angle on the sensors and a userDelay which the user controls in the gui.
	//userDelay enables the user to control the speed of the iterations.
	//Basic implementation of ARA
	public void doARA(Sensor s1, Sensor s2, int k, long userDelay, Controller cont, ArrayList<Sensor> net){
		Random random = new Random();
		int i = 0;
		//d1 and d2 are simulated delay. Controlling speed at which the sensors rotate per iteration.
		int d1 = (random.nextInt(50) + 10);
		int d2 = (random.nextInt(50) + 10);
		while (!((s1.contains(s2)) && (s2.contains(s1)))){
			if (++i > 600)
				break;
			s1.setBearing(s1.getBearing() - d1);
			s2.setBearing(s2.getBearing() - d2);
			
			//Bearing will go over 360, this will ensure the angle is reset so it stays between 0 and 360 degrees
			if (s1.getBearing() < 0){
				s1.setBearing(s1.getBearing() + 360);
			}
			if (s2.getBearing() < 0){
				s2.setBearing(s2.getBearing() + 360);
			}
			cont.posSensors(net);
			
			try {
				Thread.sleep(userDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println();
		if (i < 500){
			System.out.println("Sensor " + s1.getName() + " connected to Sensor " + s2.getName() + " in " + i + " iterations");
			
			s1.time = i;
			s2.time = i;
		}
		else
			System.out.println("Connection Failed");
		cont.posSensors(net);
	}
	//
	public void doARA(Network net, int k, long userDelay, Controller cont){
		ArrayList<Sensor> tempNet = new ArrayList<Sensor>(net.getNet());
		for (int i = 0; i < tempNet.size(); i+=2){
			doARA(tempNet.get(i), tempNet.get(i+1), k, userDelay, cont, net.getNet());
		}
		cont.posSensors(net.getNet());
	}
}
