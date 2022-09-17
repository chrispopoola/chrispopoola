import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Solution2_2 {

    static final int MAX_N = 20000;
	static final int MAX_E = 80000;

	static  int  N , E ;
	static int[] U = new int[MAX_E], V = new int[MAX_E], W = new int[MAX_E], D ;
	static int Answer;
	static int[] index, index1;
	public static ArrayList<ArrayList<Integer>> edges;
	static boolean[] visited;

	public static void pdown(int[] heap, int size, int k){
		int left = 2*k+1, right = 2*k+2, bigger;
		if(right <= size){
			if(heap[left] > heap[right]) bigger = left;
			else	bigger = right;
		}
		else if(left <= size) bigger = left;
		else  return ;
		if(heap[bigger] > heap[k]){
			int temp = heap[bigger];
			heap[bigger] = heap[k];
			heap[k] = temp;

			temp = index[bigger];
			index[bigger] = index[k];
			index[k] = temp;

			index1[index[bigger]] = bigger;
			index1[index[k]] = k;
		}
		pdown(heap,size,bigger);
	} // percolate down : O(logn)
	public static void pUp(int[] heap, int size, int k){
		int parent = (k-1) / 2;
		if(k == 0)
			return;
		if(heap[parent] < heap[k]){
			int temp = heap[parent];
			heap[parent] = heap[k];
			heap[k] = temp;

			temp = index[parent];
			index[parent] = index[k];
			index[k] = temp;

			index1[index[parent]] = parent;
			index1[index[k]] = k;
			pUp(heap, size, parent);
		}
		else
			return;
	} // percolate up : O(logn)
	public static void main(String[] args) throws Exception {
		/*
		   Data is read from input2.txt in the same folder.
		   Also, the correct answer is output to output2.txt in the same folder.
		 */
		BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output2-2.txt");

		/*
		   Since we are given 2 test cases, we process each of them.
		 */
		for (int test_case = 1; test_case <= 2; test_case++) {
			/*
			   Each test case is read from standard input.
			   First, the number of vertices and the number of edges are read into N and E, respectively.
			   Then, the number of both endpoints of each i-th edge is read into U[i], V[i], and the weight of the i-th edge is read into W[i]. (0 ≤ i ≤ E-1, 1 ≤ U[i] ≤ N, 1 ≤ V[i] ≤ N)
			 */
			stk = new StringTokenizer(br.readLine());
			N  =  Integer . parseInt (stk . nextToken ()); E  =  Integer . parseInt (stk . nextToken ());
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				U[i] = Integer.parseInt(stk.nextToken());
				V[i] = Integer.parseInt(stk.nextToken());
				W[i] = Integer.parseInt(stk.nextToken());
			}
			edges  = new ArrayList<ArrayList<Integer>>();
			int[] heap = new int[N];
			index = new int[N];
			index1 = new int[N];
			D = new int[N];
			visited = new boolean[N];

			int size = N;
			for(int i=0; i<N; i++)
				edges.add(new ArrayList<Integer>());
			for (int i = 0; i < E; i++) {
				ArrayList<Integer> temp = edges.get(U[i]-1);
				temp.add(V[i]-1);
				temp.add(W[i]);
				temp = edges.get(V[i]-1);
				temp.add(U[i] -1);
				temp.add(W[i]);
			} // graph build : O(E)
			Answer = 0;
			D[0] = 0;
			heap[0] = 0;
			for(int i=1; i<N; i++)
				D[i] = 0;
			for(int i=1; i<N; i++){
				heap[i] = D[i];
				index[i] = i;
				index1[i] = i;
			}
			for(int i=0; i<N; i++){ // implementing Prim's Algorithm : O(ElogV)
				int maxindex = index[0]; // max's index
				visited[maxindex] = true;
				Answer += heap[0];
				index[0] = index[size-1];
				heap[0] = heap[size-1];
				index1[index[0]] = 0;
				pdown(heap, --size, 0);
				ArrayList<Integer> temp = edges.get(maxindex);

				for(int j=0; j<temp.size(); j+=2){
					if(!visited[temp.get(j)] && ( temp.get(j+1) > D[temp.get(j)] ) ){
						D[temp.get(j)] = temp.get(j+1);
						heap[index1[temp.get(j)]] = D[temp.get(j)];
						pUp(heap, size, index1[temp.get(j)]);
					}
				}
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			   This is where your algorithm is performed.
			   It is assumed that the answer to the problem is calculated and the value is stored in Answer.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Output the answer to output2.txt.
			pw.println("#" + test_case + " " + Answer);
			/*
			   If you do not execute the code below, when your program is forcibly terminated due to timeout,
			   The output may not actually be written to the file.
			   Therefore, be sure to perform flush() for safety.
			 */
			pw.flush();
		}

		br.close();
		pw.close();
	}

}

