//Name: Itai Steiner
//Version: 18.06.16

import java.util.ArrayList;

public class Airport {
	private String _aName;
	private int _numOfRunway;
	private ArrayList<Integer> _freeRunways, _flightsAwait;

	public Airport(String aName, int numOfRunway){
		_aName = aName;
		_numOfRunway = numOfRunway;
		_freeRunways = new ArrayList<Integer>(_numOfRunway);
		_flightsAwait = new ArrayList<Integer>();
		for (int i=0; i<_numOfRunway; i++)
			_freeRunways.add(i+1);
	}

	public synchronized int depart(int flightNum) throws InterruptedException{
		int laneNum = departLand(flightNum);
		System.out.println("///Flight  "+flightNum+" is departing from airport "+_aName+" on runway "+laneNum+"///");
		return laneNum;
	}

	public synchronized int land(int flightNum) throws InterruptedException{
		int laneNum = departLand(flightNum);
		System.out.println("---Flight "+flightNum+" is landing at airport "+_aName+" on runway "+laneNum+"---");
		return laneNum;
	}

	public synchronized void freeRunway(int runwayNum, int flightNum){
		_freeRunways.add(runwayNum);
				System.out.println("***Runway "+runwayNum+" in Airport "+_aName+" is free***");
		notifyAll();
	}

	public String getAirportName() {
		return _aName;
	}

	public int getNumOfRunway() {
		return _numOfRunway;
	}

	private int departLand(int flightNum) throws InterruptedException{
		int vacantRunway;
		while (_freeRunways.isEmpty()){
			System.out.println("FLIGHT "+flightNum+" IS WAITING TO USE A RUNWAY.");
			if (_flightsAwait.contains(flightNum))
				wait();
			else {
				_flightsAwait.add(flightNum);
				wait();
				if(!_flightsAwait.isEmpty() && flightNum != _flightsAwait.get(0))
					wait();
			}
		}
		vacantRunway = _freeRunways.get(0);
		_freeRunways.remove(0);
		if (!_flightsAwait.isEmpty())
			_flightsAwait.remove(0);
		return vacantRunway;
	}
}