import java.util.Scanner;

public class FrontInterface {

	static Scanner userInput = new Scanner(System.in);
	static SearchBusStops searchBusStops;
	static SearchTrips searchTrips;
	static boolean finish;

	public static void main(String[] args) {

		finish = false;

		System.out.print("Please select an option from the follow: \n");
		System.out.print("(1). Search for bus stop (by bus stop name). \n");
		System.out.print("(2). Search for trip (by arrival time). \n");
		System.out.print("(3). Find shortest path (between two bus stops). \n");
		System.out.print("To exit, please enter exit. \n");

		String userIn = userInput.next();
		String search;

		while (finish != true) {

			if (userIn.toLowerCase().contains("exit"))
				finish = true;

			else if (userIn.contains("1") || userIn.contains("2") || userIn.contains("3")) {

				int option = Integer.parseInt(userIn);
				String start, end;
				String[] busStops;

				switch (option) {
				case 1:
					System.out.print("Enter the name of the bus stop or enter exit to quit. \n");
					if (userInput.hasNext()) {
						search = userInput.next();
						if (search.toLowerCase().contains("exit")) {
							userIn = "exit";
							continue;
						}
						searchBusStops = new SearchBusStops(search);
						finish = searchBusStops.isFound;
					}
					break;
				case 2:
					System.out.print("Enter the arrival time in format HH:MM:SS or enter exit to quit. \n");
					if (userInput.hasNext()) {
						search = userInput.next();
						if (search.toLowerCase().contains("exit")) {
							userIn = "exit";
							continue;
						}
						searchTrips = new SearchTrips(search);
						finish = searchTrips.isFound;
					}
					break;
				case 3: 
					if (userInput.hasNext()) {
						System.out.print("Enter the ids of the two bus stops, separated by a comma or enter exit to quit. \n");
						String bus_stops = userInput.next();
						if (bus_stops.toLowerCase().contains("exit")) {
							userIn = "exit";
							continue;
						}

						busStops = bus_stops.split(",", 1);
						start = busStops[0];
						end = busStops[1];
						System.out.print("(3). Is not currently fully functional. Therefore the option 'exit' has been selected. ");
						userIn = "exit";
							continue;
					}
					break;
				}
				if (finish == true)
					break;
			}

			else  {
				System.out.println("You have not selected a viable option. \nPlease enter an appropriate option or enter 'exit'. ");
				userIn = userInput.next();
				continue;
			}
		}
		if (userIn.toLowerCase().contains("exit")) 
			System.out.println("You have chosen to exit. This concludes this program. \n");
	}

}

//!userIn.toLowerCase().contains("exit") || f
