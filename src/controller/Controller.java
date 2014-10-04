package controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.*;
import grid.grid;


public class Controller {
	ArrayList<Sensor> list;
	Network net; // network used for program
	int delay;
	int angle;
	int size;
	int K;
	grid grid;
	ARA ara;
	RSRMA rsrma;
	RSRMAp rsrmap;


	//initialize variables
	public Controller(){
		size = 2;
		K = 3;
		net = new Network(size,K);
		list = net.getNet();
		delay = 50;
		ara = new ARA();
		rsrma = new RSRMA();
		rsrmap = new RSRMAp();
	}
	
	//Call the grid 
	// and draw the sensors to the view
	public void posSensors(ArrayList<Sensor> l){
		grid.drawSensor(l);
	}
	

	
	public void autoFunction(){
		Iterator<Sensor> i = list.iterator();
		function(list.get(0));
		posSensors(list);
		contains();
		
		//Writing data to test file
//		try{	
//			BufferedWriter contained = new BufferedWriter(new FileWriter("Contained.txt"));
//			for (Sensor s : net.getNet()){
//				if (s.getContained()){
//					contained.write("true");
//				}
//				else{
//					contained.write("false");
//				}
//				
//				contained.newLine();
//				contained.flush();
//			}	
//			contained.close();
//		} catch (FileNotFoundException e){
//			System.out.println("Error File not found");
//		} catch (EOFException e){
//			System.out.println("Error EOF");
//		} catch (IOException e){
//			System.out.println("Error IO");
//		} 
		
//		try{	
//			BufferedWriter time = new BufferedWriter(new FileWriter("Time.txt"));
//			for (Sensor s : net.getNet()){
//				time.write(""+(s.time));
//				time.newLine();
//				time.flush();
//			}	
//			time.close();
//		} catch (FileNotFoundException e){
//			System.out.println("Error File not found");
//		} catch (EOFException e){
//			System.out.println("Error EOF");
//		} catch (IOException e){
//			System.out.println("Error IO");
//		} 
//		
//			
	}
	

	//given a sensor
	//apply whichever algorithm has been selected to the sensor
	public void function(Sensor s1){
		Random random = new Random();
		if(grid.getName() == "ARA"){
				ara.doARA(net,K,(long)(100-delay), this);
				//posSensors(list);
		}else if(grid.getName()== "RSRMA"){
			rsrma.doRSRMA(net, K, 100-delay, this);
		}else{
				rsrmap.doRSRMAp(net,K,random.nextInt(50) + 10,(long)(100-delay), this);
		}
		
	}
	
	public void contains(){
		for(int i = 0; i < net.getNet().size(); i+=2){
			if (net.getNet().get(i).getContained() == true && net.getNet().get(i+1).getContained() == true){
				grid.drawContains(net.getNet().get(i),net.getNet().get(i+1));
			}
		}
	}
	
	
	
	public void update(int theta,int speed){
		K = theta;delay = speed;
	}
	

	
	public void increase(){
		if(size == 0){ size++;}
		if(size<1024)
			size = size*2;//always multiples of 2
	}
	public void decrease(){
		if(size>=0)
			size = size/2;
	}
	
	//terminate the program execution
	public void stop(){
		System.exit(0);
	};
	public void start(){
		System.out.println(delay + "  " + K);
		initialize();
		try {
			Thread.sleep((long)500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		autoFunction();
		//update();
	}
	//set the network to a new network
	//set list to the network's arrayList
	//repaint sensors
	public void initialize(){
		net = new Network(size,K);
		list = net.getNet();
		if(size>3&&list!=null)
			posSensors(list);
	}
	
	//if the reset button has ben pressed
	//reset will call clear
	public void reset(){
		clear();
	}
	
	
	//will clear view
	//reset the network to a default value
	public void clear(){
		list.clear();
		net = new Network(2,K);
		list = net.getNet();
		grid.removeAll();
		grid.repaint();
	}
	
	//if the tab is change
	//set the grid of the controller to the 
	public void setGrid(grid g){
		grid = g;
		net = new Network(size,K);
		list = net.getNet();
		initialize();
	}
	
	//return the size of the network
	public int getNetSize(){
		return list.size();
	}
	

	
	public int getSize(){return size;}
	
	

	//return the K value of the network
	public int getK(){return K;}
	
	//draw from node to node thick red line

}
