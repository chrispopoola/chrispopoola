import java.io.*;
import java.util.*;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra2 {

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */

	int speedA, speedB, speedC, n_vertices, n_edges;
	Edge[][] adjEdgeList; 
	int [] noEdges;
	PriorityQueue<Node> priorityqueue = new PriorityQueue<Node>();
	double[] distances;

	void graphFile(String filename) throws FileNotFoundException {

		Scanner Input = new Scanner(new File(filename));

		if (!Input.hasNextInt()) {
			Input.close();
			return;
		}

		n_vertices = Input.nextInt();
		n_edges = Input.nextInt();

		distances = new double[n_vertices];
		int n = 1;
		adjEdgeList = new Edge[n_vertices][n];
		noEdges = new int[n_vertices];
		
		for (int i = 0; i < n_vertices; i++)
			noEdges[i] = 0;
		
		for (; n_edges > 0; n_edges--) {
			
			int u = Input.nextInt();
			int v = Input.nextInt();
			double dist = Input.nextDouble() * 1000;
			
			for (int i = 0; i < n_vertices; i++) {
				
				if (i == u) {
					adjEdgeList[u][noEdges[i]] = new Edge(u, v, dist);
					adjEdgeList = Arrays.copyOf(adjEdgeList, n);
					noEdges[i]++;
				}
			}

			

		}
		
		Input.close();

	}

	CompetitionDijkstra2 (String filename, int sA, int sB, int sC) {

		speedA = sA;
		speedB = sB;
		speedC = sC;

		try {
			graphFile(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition() {
		if (Math.min(speedA, Math.min(speedB, speedC)) < 50 || Math.max(speedA, Math.max(speedB, speedC)) > 100 || n_vertices == 0) {
            return -1; 
        }
		double maxDistance = 0;
		
		for (int i = 0; i < n_vertices; i++) {
			
			for (int j = 0; j < n_vertices; j++) 
				distances[j] = -1; // initalise all distances to -1, i.e. infinity (INF)
			
			priorityqueue.add(new Node(i, 0));	// add vertex to priority queue & initialise distance = 0
			distances[i] = 0;
			
			while (priorityqueue.isEmpty() == false) {
				
				Node firstVertex = priorityqueue.poll(); // pop the first/top vertex off priority queue
				int vertex = firstVertex.v;
				double distance = firstVertex.distance;
				
				if (distances[vertex] < distance)
					continue;
				
				
				for (Edge edge : adjEdgeList[vertex]) {
					double newDistance = distance + edge.length;
					
					if (newDistance < distances[edge.vertexV] || distances[edge.vertexV] == -1) {
						distances[edge.vertexV] = newDistance;
						priorityqueue.add(new Node(edge.vertexV, newDistance));
					}
				
			}
			}
			for (int k = 0; k < n_vertices; k++) {
				maxDistance = Math.max(maxDistance, distances[k]);
				if(distances[i] < 0)
					return -1;		// this means the graph is not connected
			}
			
		}
		double time = maxDistance / (Math.min(speedA, (Math.min(speedB, speedC))));
		return (int) Math.ceil(time);
	}

}

class Edge {
	
	int vertexU, vertexV;
	double length;
	
	public Edge (int u, int v, double length) {
		
		this.vertexU = u;
		this.vertexV = v;
		this.length = length;
	}

}

class Node implements Comparable<Node>{
	int v;
	double distance;
	
	public Node (int v, double distance) {
		this.v = v;
		this.distance = distance;
	}


	public int compareTo(Node vertex) {
		
		if (distance < vertex.distance)
			return -1;
		if (distance > vertex.distance)
			return 1;
		
		return 0;
	}
}


