package model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Network {
	//List that holds sensors
	private ArrayList<Sensor> network = new ArrayList<Sensor>();
	
	//Constructor: Creates n sensors and sets their position randomly. 
	//Also checks and prevents multiple sensors having the same position
	public Network(int n, int k){
		Random random = new Random();
		int numSensors = Math.abs(n);
		int end = 0;
		int i = 1;
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLACK);
		colors.add(Color.green);
		colors.add(Color.blue);
		colors.add(Color.magenta);
		while (end != numSensors) {
			int x = (random.nextInt(101));
			int y = (random.nextInt(101));
			if (!(network.isEmpty()))
				for (Sensor s : network){
					if ((s.getX() == x) && (s.getY() == y))
						continue;
				}
			
			network.add(new Sensor(i++, x, y, (360 / k), random.nextInt(361), colors.get(random.nextInt(4))));
			end++;
		}
		System.out.println("Network initiated with " + numSensors + " sensors:");
		
		//Test data writing to files
//		try{
//			BufferedWriter name = new BufferedWriter(new FileWriter("Name.txt"));
//			BufferedWriter bearing = new BufferedWriter(new FileWriter("Bearing.txt"));
//			BufferedWriter x = new BufferedWriter(new FileWriter("Xloc.txt"));
//			BufferedWriter y = new BufferedWriter(new FileWriter("Yloc.txt"));
//			BufferedWriter kval = new BufferedWriter(new FileWriter("kval.txt"));
		for (Sensor s : network){
			System.out.println(s);
//				name.write("" + s.getName());
//				name.newLine();
//				name.flush();
//				bearing.write("" + s.getBearing());
//				bearing.newLine();
//				bearing.flush();
//				x.write("" + s.getX());
//				x.newLine();
//				x.flush();
//				y.write("" + s.getY());
//				y.newLine();
//				y.flush();
//				kval.write("" + s.getK());
//				kval.newLine();
//				kval.flush();
//			
		}
//			name.close();
//			bearing.close();
//			x.close();
//			y.close();
//			kval.close();
//		} catch (FileNotFoundException e){
//			System.out.println("Error File not found");
//		} catch (EOFException e){
//			System.out.println("Error EOF");
//		} catch (IOException e){
//			System.out.println("Error IO");
//		} 
		
		
	}
	
	public ArrayList<Sensor> getNet(){ return network; }
}
