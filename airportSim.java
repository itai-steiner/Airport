//Name: Itai Steiner
//Version: 16.06.16

import java.util.Random;

public class airportSim {
	public static final int NUM_OF_FLIGHTS = 10, NUM_OF_RUNWAYS = 3;

	public static void main(String[] args) {
		Random rand = new Random();
		Airport benGurion = new Airport("Ben Gurion International Airport", NUM_OF_RUNWAYS);
		Airport eilat = new Airport("Eilat Airport", NUM_OF_RUNWAYS);
		for (int i=0; i<NUM_OF_FLIGHTS; i++){
			if (rand.nextBoolean() == true )		//Randomly choosing routes.
				new Flight(rand.nextInt(999)+100, benGurion, eilat).start();
			else new Flight(rand.nextInt(999)+100, eilat, benGurion).start();
		}
	}
}
