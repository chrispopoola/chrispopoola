import java.io.*;
import java.util.*;

public class SearchBusStops {

	static ArrayList <busStop> busStops = new ArrayList<busStop>();
	static TST ternarySearchTrie;
	static boolean isFound;
	static Scanner searchInput = new Scanner(System.in);

	SearchBusStops (String search) {

		try {
			listOfBusStops("stops.txt");

			isFound = findBusStops(search.toUpperCase());

			if (isFound != true)
				System.out.print("The bus stop searched for could not be found. \n");

		} catch (FileNotFoundException e) {
			System.out.print("File Not Found. ");
		}
	}

	static boolean findBusStops (String search) {
		boolean find;
		ternarySearchTrie = new TST();
		int listSize = 0;

		for (int i = 0; i < busStops.size(); i++) 
			ternarySearchTrie.put(busStops.get(i).getStopName(), 0);

		Iterable<String> list = ternarySearchTrie.keysWithPrefix(search);

		for (String element : list)
			listSize++;

		if (listSize <= 0)
			find = false;
		else {
			find = true;
			for (String element : list) 
				getFullInfo(element);
			
		}
		return find;
	}

	static void getFullInfo(String element) {

		for (busStop stop : busStops) {

			if (stop.getStopName().contains(element)) {

				System.out.println("Stop Name: " + stop.getStopName());
				System.out.println("Stop ID: " + stop.getStopID());
				System.out.println("Stop Code: " + stop.getStopCode());
				System.out.println("Stop Destination: " + stop.getStopDes());
				System.out.println("Stop Latitude: " + stop.getStopLat());
				System.out.println("Stop Longitude: " + stop.getStopLon());
				System.out.println("Zone ID: " + stop.getZoneID());
				System.out.println("Stop URL: " + stop.getStopURL());
				System.out.println("Location Type: " + stop.getLocationType());
				System.out.println("Parent Station: " + stop.getParentStation()+ "\n");
			}
		}
	}

	static void listOfBusStops (String filename) throws FileNotFoundException {

		Scanner Input = new Scanner(new File(filename));

		if (!Input.hasNext())
			return;
		else {
			if(Input.hasNext() == true)
				   Input.nextLine();
			
			while (Input.hasNextLine()) {
				String line = Input.nextLine();
				busStops.add(new busStop(line));
			}

		}
	}

	public static void main(String args[]) {


		System.out.println("Enter the name of the bus stop you are searching for. ");
		String search = searchInput.next();
		SearchBusStops searchBusStops = new SearchBusStops(search);

		//		findBusStops(search.toUpperCase());

		//		busStop stop = busStops.get(3);
		//		System.out.print(stop.getStopName());
	}

}

class busStop {

	private String stopName, stopDes, zoneID, stopURL, parentStation, stopLat, stopLon, stopID, stopCode, locationType; 

	static final String prefixWB = "WB ";
	static final String prefixNB = "NB ";
	static final String prefixSB = "SB ";
	static final String prefixEB = "EB ";
	static final int prefixLength = 3;

	busStop(String line) {
		String[] attributes = line.split(",");

		this.stopID = attributes[0];
		this.stopCode = attributes[1];
		this.stopName = attributes[2];
		this.stopDes = attributes[3];
		this.stopLat = attributes[4];
		this.stopLon = attributes[5];
		this.zoneID = attributes[6];
		this.stopURL = attributes[7];
		this.locationType = attributes[8];
		
		if (attributes.length > 9)
			this.parentStation = attributes[9];

		changeName();

		//				stop_id,	stop_code,	stop_name,						stop_desc,					stop_lat,	stop_lon,		zone_id,	stop_url,	location_type,parent_station
		//				1888,		51874,		WB HASTINGS ST FS HOLDOM AVE-,	HASTINGS ST @ HOLDOM AVE,	49.280436,	-122.981419,	ZN 99, 		,			0,

	}

	String getStopID() {
		return this.stopID;
	}

	String getStopCode() {
		return this.stopCode;
	}

	String getStopName() {
		return this.stopName;
	}

	String getStopDes() {
		return this.stopDes;
	}

	String getStopLat() {
		return this.stopLat;
	}

	String getStopLon() {
		return this.stopLon;
	}

	String getZoneID() {
		return this.zoneID;
	}

	String getStopURL() {
		return this.stopURL;
	}

	String getLocationType() {
		return this.locationType;
	}
	String getParentStation() {
		return this.locationType;
	}

	void changeName() {

		if (this.stopName.startsWith(prefixNB)) {
			this.stopName = this.stopName.substring(prefixLength);
			this.stopName = this.stopName + " NB";
		}
		if (this.stopName.startsWith(prefixSB)) {
			this.stopName = this.stopName.substring(prefixLength);
			this.stopName = this.stopName + " SB";
		}
		if (this.stopName.startsWith(prefixWB)) {
			this.stopName = this.stopName.substring(prefixLength);
			this.stopName = this.stopName + " WB";
		}
		if (this.stopName.startsWith(prefixEB)) {
			this.stopName = this.stopName.substring(prefixLength);
			this.stopName = this.stopName + " EB";
		}
		else
			return;
	}

}


