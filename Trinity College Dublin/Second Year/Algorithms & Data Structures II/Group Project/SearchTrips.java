import java.io.*;
import java.util.*;

public class SearchTrips {

	ArrayList<Trip> trips = new ArrayList<Trip>();
	PriorityQueue<Trip> priorityQ = new PriorityQueue<Trip>();
	boolean isValid;
	static Scanner input = new Scanner(System.in);
	static boolean isFound;

	final int MAX_HOURS = 23;
	final int MAX_MINUTES = 59;
	final int MAX_SECONDS = 59;

	SearchTrips (String searchTime) {

		String[] temp = searchTime.split(":");

		if (temp[0].startsWith("0")) {
			temp[0] = temp[0].substring(1);

			searchTime = String.join(":", temp);
		}

		try {
			int hours = Integer.parseInt(temp[0]);
			int mins = Integer.parseInt(temp[1]);
			int secs = Integer.parseInt(temp[2]);

			if (hours > MAX_HOURS || mins > MAX_MINUTES || secs > MAX_SECONDS) {
				isFound = false;
				System.out.print("The arrival time searched for is not a viable option. Please try again. \n");
			}
			else {	

				try {
					listOfTrips("stop_times.txt");
				} catch (FileNotFoundException e) {
					System.out.print("File Not Found. ");
				}

				isFound = findTrips(searchTime);
				if (isFound != true)
					System.out.print("The trips for the arrival time searched for could not be found. Please try again. \n");
			}
		} catch (NumberFormatException e) {
			System.out.print("The arrival time searched for is not a viable option. Please try again. \n");
			isFound = false;
		}

	}

	public static void main (String[] args) {

		System.out.println("Enter the arrival time. ");
		String search = input.next();
		SearchTrips searchTrips = new SearchTrips(search);

	}

	void listOfTrips(String file) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File (file));

		if (!scanner.hasNext())
			return;
		else {
			if(scanner.hasNext() == true)
				scanner.nextLine();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				Trip tryTrip = new Trip(line);
				if (tryTrip.valid == true)
					trips.add(tryTrip);
				else
					continue;
			}	
		}

		//    trip_id,	arrival_time,	departure_time,	stop_id,	stop_sequence,	stop_headsign,	pickup_type,	drop_off_type,	shape_dist_traveled
		//    9017927, 	5:25:00, 		5:25:00,		646,		1,				,				0,				0,				
		//    9017927, 	5:25:50, 		5:25:50,		378,		2,				,				0,				0,				0.3300
	}

	boolean findTrips(String time) {

		int numbTrips = 0;

		for (Trip t : trips) {
			String string = t.getArrivalTime();

			//			System.out.print(string + "\n");

			if (time.contentEquals(string)) {
				priorityQ.add(t);
				numbTrips++;
			}
		}
		//		System.out.print(numbTrips + " compared \n");

		int[] tripIDs = new int[numbTrips];
		int i = 0;
		for (Trip trip : priorityQ) {	
			tripIDs[i] = trip.getTripID();
			i++;
		}
		int[] sorted = quickSort(tripIDs, 0, tripIDs.length-1);

		for (int k = 0; k < numbTrips; k++) {

			for (Trip j : priorityQ) {

				if (j.getTripID() == sorted[k]) {

					System.out.println("Trip ID: " + j.getTripID());
					System.out.println("Arrival Time: " + j.getArrivalTime());
					System.out.println("Departure Time: " + j.getDepartureTime());
					System.out.println("Stop ID: " + j.getStopID());
					System.out.println("Stop Sequence: " + j.getStopSequence());
					System.out.println("Stop Headsign: " + j.getStopHeadsign());
					System.out.println("Pick-up Type: " + j.getPickupType());
					System.out.println("Drop-off Type: " + j.getDropoffType());
					System.out.println("Shape Distance Travelled: " + j.getShapeDistTravelled() + "\n");

				}
			}
		}
		if (numbTrips > 0) 
			return true;

		else
			return false;
	}

	public int[] quickSort(int arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);

			quickSort(arr, begin, partitionIndex-1);
			quickSort(arr, partitionIndex+1, end);
		}

		return arr;
	}

	private int partition(int arr[], int begin, int end) {
		int pivot = arr[end];
		int i = (begin-1);

		for (int j = begin; j < end; j++) {
			if (arr[j] <= pivot) {
				i++;

				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}

		int swapTemp = arr[i+1];
		arr[i+1] = arr[end];
		arr[end] = swapTemp;

		return i+1;
	}

	boolean isEmpty(int a[]) {
		boolean isEmpty = false;
		if (a.length <= 0)
			isEmpty = true;
		return isEmpty;
	}
}

class Trip implements Comparable<Trip> {

	private String tripID, arrivalTime, departureTime, stopID, stopSequence, stopHeadsign, pickupType, dropoffType, shapeDistTravelled;
	//	int hours, mins, secs;
	boolean valid;

	final int MAX_HOURS = 23;
	final int MAX_MINUTES = 59;
	final int MAX_SECONDS = 59;

	Trip (String line) {

		String[] attributes = line.split(",");

		this.tripID = attributes[0];
		this.arrivalTime = attributes[1];

		if (this.arrivalTime.startsWith(" ")) {
			this.arrivalTime = this.arrivalTime.substring(1);
		}	

		this.departureTime = attributes[2];
		this.stopID = attributes[3];
		this.stopSequence = attributes[4];
		this.stopHeadsign = attributes[5];
		
		if (this.stopHeadsign == "")
			this.stopHeadsign = "N/A";

		this.pickupType = attributes[6];
		this.dropoffType = attributes[7];

		if (attributes.length > 8)
			this.shapeDistTravelled = attributes[8];

		String[] times = arrivalTime.split(":");

//		String intValueH = times[0].replaceAll("[^0-9]", "");
//		String intValueM = times[0].replaceAll("[^0-9]", "");
//		String intValueS = times[0].replaceAll("[^0-9]", "");

		int hours = Integer.parseInt(times[0]);
		int minutes = Integer.parseInt(times[1]);
		int seconds = Integer.parseInt(times[2]);

		//		this.hours = hours;
		//		this.mins = minutes;
		//		this.secs = seconds;

		if (hours > MAX_HOURS || minutes > MAX_MINUTES || seconds > MAX_SECONDS)
			valid = false;
		else 
			valid = true;
	}

	String getArrivalTime() {
		return arrivalTime;
	}

	int getTripID () {
		return Integer.parseInt(tripID);
	}

	String getDepartureTime() {
		return departureTime;
	}
	String getStopID() {
		return stopID;
	}
	String getStopSequence() {
		return stopSequence;
	}
	String getStopHeadsign() {
		return stopHeadsign;
	}
	String getPickupType() {
		return pickupType;
	}
	String getDropoffType() {
		return dropoffType;
	}
	String getShapeDistTravelled() {
		return shapeDistTravelled;
	}

	@Override
	public int compareTo(Trip o) {
		// TODO Auto-generated method stub
		return 0;
	}
}