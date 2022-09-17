package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Dijsktra {

	public Dijsktra() throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		File file = new File("stops.txt");
		int[] print = findVertices(file);
		//ArrayList<edge> edges = new ArrayList<edge>();
		HashMap<Integer, ArrayList<edge>> graph = new HashMap<Integer, ArrayList<edge>>();
		hashMapGraph(graph, print);

		//System.out.println(graph);

		File file2 = new File("stop_times.txt");
		Scanner sc = new Scanner(file2);
		sc.nextLine();
		String[] split = sc.nextLine().split(",");


		int comparator = Integer.parseInt(split[0]);
		String[] split2 = sc.nextLine().split(",");
		while(sc.hasNext()) {
			if(comparator != Integer.parseInt(split[0]) || comparator != Integer.parseInt(split2[0])) {
				comparator = Integer.parseInt(split[0]);
				//System.out.println(comparator);
			}
			else {
				int firstStop = Integer.parseInt(split[3]);
				split2 = sc.nextLine().split(",");
				int secondStop = Integer.parseInt(split2[3]);
				edge newEdge = new edge(secondStop, 1);
				graph.get(firstStop).add(newEdge);

				split = split2;

			}
		}

	}
	public static void Dijsktra(HashMap<Integer, ArrayList<edge>> graph, int source)
	{
		HashMap<Integer, Integer> Q = new HashMap<Integer, Integer>();
		HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
		HashMap<Integer, Double> dist2 = new HashMap<Integer, Double>();
		HashMap<Integer, Double> edgeTo = new HashMap<Integer, Double>();
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		
		for (Integer key : graph.keySet()) {
			dist.put(key, Double.MAX_VALUE);
			edgeTo.put(key, null);
			Q.put(key, key);
			visited.put(key, false);
		}
		System.out.println(Q.size());
		for (int i = 0; i < Q.size(); i++) {
			int u = smallestDist(dist);
			if(Q.containsKey(u)) {
				Q.remove(u);
				dist.remove(u);
				System.out.println(u);
			}
		}
		System.out.println(Q.size());
		System.out.println(Q.toString());
	}
	
	public static int smallestDist(HashMap<Integer, Double> dist) {
		
		double smallest = Double.POSITIVE_INFINITY;
		int outKey = -1;
		for (Integer key : dist.keySet()) {
			if(dist.get(key) < smallest) {
				smallest = dist.get(key);
				outKey = key;
			}
		}
		return outKey;
		
	}
	public static void removeAll(HashMap<Integer, ArrayList<edge>> graph, Scanner newSc) {
		for (Integer key : graph.keySet()) {
		    removeDuplicates(graph, key);
		}
		
		transfers(graph, newSc);
	}

    public static void removeDuplicates(HashMap<Integer, ArrayList<edge>> graph, int address) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i = 0; i < graph.get(address).size(); i++) {
			if(output.contains(graph.get(address).get(i).v)) {
			}
			else {
				output.add(graph.get(address).get(i).v);

			}


		}
		ArrayList<edge> revise = new ArrayList<edge>();
		for(int i = 0; i < output.size(); i++) {
			edge fromOut = new edge(output.get(i), 1);
			revise.add(fromOut);
		}
		graph.replace(address, revise);
    	
    }
    
    
    
	
	public static int[] convertIntegers(ArrayList<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	public static int[] findVertices(File input) {
		int[] output = null;
		try {
			Scanner sc = new Scanner(input);
			sc.nextLine();
			ArrayList<Integer> holder = new ArrayList<Integer>();
			while(sc.hasNext()) {
				String holding = sc.nextLine();
				String[] line = holding.split(",");
				int putting = Integer.parseInt(line[0]);
				holder.add(putting);
			}
			
			int[] stop = convertIntegers(holder);
			output = stop;
			//System.out.println(stop.length);
			insertionSort(stop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	public static void hashMapGraph(HashMap<Integer, ArrayList<edge>> graph, int[] print) {
		for(int i = 0; i < print.length; i++) {
			ArrayList<edge> edges = new ArrayList<edge>();
			graph.put(print[i], edges);
		}
	}
	
	public static void stopTimes(HashMap<Integer, ArrayList<edge>> graph, Scanner sc) {
		
		sc.nextLine();
		while(sc.hasNext()) {
			tripSpec(graph, sc);
		}
	}
	
	public static void tripSpec(HashMap<Integer, ArrayList<edge>> graph, Scanner sc) 
	{
		String[] splitUp = sc.nextLine().split(",");
		int comparator = Integer.parseInt(splitUp[0]);
		String[] split2 = sc.nextLine().split(",");
		edge first = new edge(Integer.parseInt(split2[3]), 1);
		graph.get(Integer.parseInt(splitUp[3])).add(first);
		while(Integer.parseInt(split2[0]) == comparator && sc.hasNext()) {
			
			splitUp[3] = split2[3];
			int key = Integer.parseInt(split2[3]);
			edge stopTime = new edge(key, 1);
			graph.get(Integer.parseInt(splitUp[3])).add(stopTime);

			split2 = sc.nextLine().split(",");
		}
		
	}
	
	public static void transfers(HashMap<Integer, ArrayList<edge>> graph, Scanner sc) 
	{
		sc.nextLine();
		while(sc.hasNext()) {
			String[] split = sc.nextLine().split(",");
			
			if(Integer.parseInt(split[2]) == 0) {
				edge type0 = new edge(Integer.parseInt(split[1]), 2);
				graph.get(Integer.parseInt(split[0])).add(type0);
			}
			else{
				edge type0 = new edge(Integer.parseInt(split[1]), (Double.parseDouble(split[3])/100));
				graph.get(Integer.parseInt(split[0])).add(type0);
			}
		}
	}
	
	static int [] insertionSort (int[] stop){
		int temp;
		for (int i = 1; i < stop.length; i++) {
			for(int j = i ; j > 0 ; j--){
				if(stop[j] < stop[j-1]){
					temp = stop[j];
					stop[j] = stop[j-1];
					stop[j-1] = temp;
				}
			}
		}
		return stop;

		//todo: implement the sort
	}
}
