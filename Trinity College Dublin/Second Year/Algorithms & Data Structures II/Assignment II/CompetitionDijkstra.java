import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 * 
 * @author Adriana Hrabowych
 */

public class CompetitionDijkstra 
{

	int worstSpeed;
	int numIntersection;
	DirectedEdge[][] graph;
	boolean impossible; //if the competition using rhe graph is impossible, this is true

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */
	CompetitionDijkstra (String filename, int sA, int sB, int sC)
	{
		//if any of the input speeds are out of bounds then we dont need to read in the file as its impossible
		if(sA < 50 || sB < 50 || sC < 50 || sA > 100 || sB > 100 || sC > 100)
		{
			impossible = true;
		}
		else 
		{
			//since we only care about the worst time possible, we only need to save the lowest speed
			if(sA <= sB && sA <= sC)
				worstSpeed = sA;
			else if(sB <= sC)
				worstSpeed = sB;
			else
				worstSpeed = sC;

			impossible = false;

			createGraph(filename); //initiliazes both numIntersection and graph

			//check if any intersections have no streets going from them - aka its impossible to host the competition
			for(int i = 0; i < graph.length; i++)
			{
				
				if(graph[i].length == 0)
					impossible = true;
				
			}

		}
	}

	/**
	 * 
	 * @param name: name of the file where the graph is read from
	 */
	private void createGraph(String name)
	{
		// opens a Scanner object to read from the file
		@SuppressWarnings("resource")
		Scanner sc =new Scanner("");
		File inputFile = new File(name);

		try {sc = new Scanner(inputFile);}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		//set numintersection = to first line in file
		if(sc.hasNextInt())
		{
			numIntersection = sc.nextInt();
		}

		//read in numstreets 
		int numStreets = -1;
		if(sc.hasNextInt())
		{
			numStreets = sc.nextInt();
		}

		graph = new DirectedEdge[numIntersection][0];

		for(int i = 0; i < numStreets; i++)
		{
			//Create edge from file information
			DirectedEdge e = new DirectedEdge();
			if(sc.hasNextInt())
			{
				e.sI = sc.nextInt();
			}
			else
				break;

			if(sc.hasNextInt())
			{
				e.eI = sc.nextInt();
			}
			else
				break;

			if(sc.hasNextDouble())
			{
				e.distance = sc.nextDouble();
			}
			else
				break;

			//add edge to graph
			DirectedEdge[] temp = Arrays.copyOf(graph[e.sI], graph[e.sI].length + 1);
			temp[temp.length - 1] = e;
			graph[e.sI] = temp;

		}
	}
	/**
	 * 
	 * @param startIntersection: the index of the starting point for dijkstra
	 * @return the distance to the furthest node from the start, -1 if any nodes went unvisited
	 */
	private double runDijkstra(int startIntersection)
	{
		//initialize distto array, everything but the start intersection should be infinity
		double[] distTo = new double[graph.length];
		Arrays.fill(distTo,Double.POSITIVE_INFINITY);
		distTo[startIntersection] = 0;

		//initialize visitied array, everything but startinersection should be false
		boolean[] visit = new boolean[graph.length];
		Arrays.fill(visit,false);
		visit[startIntersection] = true;
		//now i dont have an edgeto array because it seems to only be important for then travering the graph
		//which is something we don't need to do

		//initialize queue for visiting
		ArrayList<Integer> toVisit = new ArrayList<Integer>();
		toVisit.add(startIntersection);

		while(toVisit.size() > 0)
		{
			//returns index of intersection in toVisit with least distance from start
			int toGet = findMinPriority(toVisit, distTo);
			int currentIntersection = toVisit.get(toGet);
			
			for(int i = 0; i < graph[currentIntersection].length; i++)
			{
				//if node has not been visited or isnt on the list already, add it to the queue
				if(visit[graph[currentIntersection][i].eI] != true)
				{
					toVisit.add(graph[currentIntersection][i].eI);
					visit[graph[currentIntersection][i].eI] = true;
				}

				//relax - if the new proposed distance is less than whats in the distTo array, replace it
				if(distTo[graph[currentIntersection][i].eI] > (distTo[currentIntersection] + graph[currentIntersection][i].distance))
				{
					distTo[graph[currentIntersection][i].eI] = distTo[currentIntersection] + graph[currentIntersection][i].distance;
				}
			}

			//remove current intersection
			toVisit.remove(toGet);
		}

		//go through distTo array and find the greatest distance
		double greatestDist = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < distTo.length; i++)
		{
			if(distTo[i] > greatestDist)
				greatestDist = distTo[i];

		}

		return greatestDist;
	}
	/**
	 * 
	 * @param intersections : arraylist of intersections that are up for visiting
	 * @param distances : array containing the current smallest distance from each intersection from the start
	 * @param si
	 * @return
	 */
	private int findMinPriority(ArrayList<Integer> intersections, double[] distances)
	{
		//instantiate mindistance and mindid as intersection at position 0 in intersections
		double minDistance = distances[intersections.get(0)];
		int minID = 0;

		for(int i = 1; i < intersections.size(); i++)
		{
			if(distances[intersections.get(i)] < minDistance)
			{
				minDistance = distances[intersections.get(i)];
				minID = i;
			}
		}

		return minID;
	}
	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet, -1 if impossible
	 */
	public int timeRequiredforCompetition()
	{
		//if the graph was previously set to impossible, return -1
		if(impossible == true)
			return -1;

		double maxTime = Double.NEGATIVE_INFINITY;
		//go through each intersection, running Dijkstra on each and take the distance from the furthest intersection from the start
		//and see if its worse than any previous dijkstra runs
		for(int i = 0; i < graph.length; i++)
		{
			double maxDistance = runDijkstra(i);

			if((maxDistance * 1000) / worstSpeed > maxTime)
			{
				double calc = (maxDistance * 1000) / worstSpeed; //* 1000 since distance is in km and speed is m/s
				//if the number is not a whole number we need to add one since casting rounds down
				if(calc % 1 != 0)
					maxTime = (int) calc + 1;
				else
					maxTime = (int) calc;

			}
		}

		if(maxTime != Double.NEGATIVE_INFINITY)
			return (int) maxTime;
		else
			return -1;
	}

}

//Class that stores the information relating to one 'directed' edge
class DirectedEdge
{
	//all variables are public just to make my life easier
	public int sI; //start intersection
	public int eI;  //ending intersection
	public double distance; //distance b/w intersections

	DirectedEdge()
	{
		sI = -1;
		eI = -1;
		distance = -1;
	}
}