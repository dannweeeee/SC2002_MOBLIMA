package Moblima;

import java.util.ArrayList;

public class cineplexHandler {
	
	private ArrayList<Cineplex> allCineplex;
	private static int cineplexCounter=0;
	
	public cineplexHandler() {
		allCineplex = new ArrayList<Cineplex>();
		
	}
	
	public void addCineplex(String location) {
		Cineplex newCineplex = new Cineplex(location);
		allCineplex.add(newCineplex);
		cineplexCounter++;
	}
	
	public void printAllCineplex() {
		int count =0;
		for (Cineplex temp : allCineplex) {
			temp.setCineplexNo(count);
			System.out.print(temp.getCineplexNo()+": ");
			temp.printCineplex();
			count++;
		}
	}
	
	public ArrayList<Cineplex> getAllCineplex(){
		return allCineplex;
	}
	
	public int getSize() {
		return cineplexCounter;
	}
	
}