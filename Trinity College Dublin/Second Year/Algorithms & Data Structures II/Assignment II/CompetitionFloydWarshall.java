import java.io.*;
import java.util.*;
import java.math.*;
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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
	int n_vertices, n_edges;
	int speedA, speedB, speedC;
	double[][] adjacencyMatrix;
	
	void graphFile(String filename) throws FileNotFoundException {

		Scanner Input = new Scanner(new File(filename));

		if (!Input.hasNextInt()) {
			return;
		}
		
		n_vertices = Input.nextInt();
		n_edges = Input.nextInt();
		adjacencyMatrix = new double[n_vertices][n_vertices];
		
		for (int i = 0; i < n_vertices; i++) {
			for(int j = 0; j < n_vertices; j++)
				adjacencyMatrix[i][j] = 1000000000; // initalise = 1,000,000,000 instead of -1 to represent infinity because of negative weights
		}
		
		for (int i = 0; i < n_vertices; i++)
			adjacencyMatrix[i][i] = 0;
		
		while (n_edges > 0) {
			n_edges--;
			
			int u = Input.nextInt();
			int v = Input.nextInt();
			
			double length = Input.nextDouble() * 1000;
			
			adjacencyMatrix[u][v] = length;
		}
		
	}
	
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC) {

		speedA = sA;
		speedB = sB;
		speedC = sC;

		try {
			graphFile(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};

	}


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
    	
    	if (Math.min(speedA, Math.min(speedB, speedC)) < 50 || Math.max(speedA, Math.max(speedB, speedC)) > 100 || n_vertices == 0) {
            return -1; 
        }
    	
    	for (int i = 0; i < n_vertices; i++) {
    		for (int j = 0; j < n_vertices; j++) {
    			for (int k = 0; k < n_vertices; k++) {		
    				if (adjacencyMatrix[j][k] > adjacencyMatrix[j][i] + adjacencyMatrix[i][k])
    					adjacencyMatrix[j][k] = adjacencyMatrix[j][i] + adjacencyMatrix[i][k];
    			}
    		}
    	}
    	double result = -1;
    	
    	for (int i = 0; i < n_vertices; i++) {
    		for (int j = 0; j < n_vertices; j++) {
    			result = Math.max(result, adjacencyMatrix[i][j]);
    			if (adjacencyMatrix[i][j] >= 1000000000)
    				return -1;		// this means graph is not connected
    		}
    	}
    	double answer = result / Math.min(speedA, Math.min(speedB, speedC));
        return (int)Math.ceil(answer);
    }

}