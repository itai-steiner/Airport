//Name: Itai Steiner
//Version: 15.06.16

import java.util.Random;

public class Flight extends Thread{
	private int _flightNum;
	private Airport _departuringAP, _landingAP;
	private Random rand = new Random();

	public Flight(int flightNum, Airport departuringAP, Airport landingAP){
		_departuringAP = departuringAP;
		_landingAP = landingAP;
		_flightNum = flightNum;
	}

	public int get_flightNum() {
		return _flightNum;
	}

	public Airport getDeptFrom() {
		return _departuringAP;
	}
	
	public Airport getLandAt(){
		return _landingAP;
	}

	public void run(){
		int runway;
		try {
			runway = _departuringAP.depart(_flightNum);
			sleep((rand.nextInt(3000)+2000));
			_departuringAP.freeRunway(runway, _flightNum);
			sleep((rand.nextInt(15000)+10000));
			runway = _landingAP.land(_flightNum);
			sleep((rand.nextInt(3000)+2000));
			_landingAP.freeRunway(runway, _flightNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
